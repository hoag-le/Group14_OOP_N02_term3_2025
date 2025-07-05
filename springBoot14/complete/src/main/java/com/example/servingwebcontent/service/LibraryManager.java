package com.example.servingwebcontent.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Member;

@Service
public class LibraryManager {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MemberDao memberDao;

    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    public synchronized String borrowBook(int memberId, int bookId, int days) {
        if (days <= 0) {
            return "Số ngày mượn không hợp lệ";
        }

        Member member = memberDao.read(memberId);
        Book book = bookDao.read(bookId);
        if (member == null || book == null || isBookBorrowed(bookId)) {
            return "Không thể mượn sách";
        }

        Date borrowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowDate);
        cal.add(Calendar.DATE, days);
        Date dueDate = cal.getTime();

        BorrowRecord record = new BorrowRecord(book, member, borrowDate, dueDate);
        borrowRecords.add(record);

        member.borrowBook(book);
        return "Mượn sách thành công! Hạn trả: " + dueDate;
    }

    private boolean isBookBorrowed(int bookId) {
        for (BorrowRecord r : borrowRecords) {
            if (r.getBook().getId() == bookId && !r.isReturned()) {
                return true;
            }
        }
        return false;
    }

    public synchronized String returnBook(int memberId, int bookId) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBook().getId() == bookId &&
                record.getMember().getId() == memberId &&
                !record.isReturned()) {

                record.setReturned(new Date());
                record.getMember().returnBook(record.getBook());

                Date now = record.getReturnDate();
                if (now.after(record.getDueDate())) {
                    long diffMs = now.getTime() - record.getDueDate().getTime();
                    long diffDays = diffMs / (1000 * 60 * 60 * 24);
                    double fine = diffDays * 5000;
                    return "Trả sách thành công. Bạn đã trả trễ " + diffDays + " ngày. Phí phạt: " + fine + " VND.";
                } else {
                    return "Trả sách thành công. Bạn không bị phạt!";
                }
            }
        }
        return "Không tìm thấy giao dịch mượn sách để trả!";
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}