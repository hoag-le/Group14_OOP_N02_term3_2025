package com.example.servingwebcontent.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Member;

/**
 * Utility class containing common borrow/return logic so that
 * Library and LibraryManager do not duplicate the implementation.
 */
public final class BorrowService {
    private BorrowService() {}

    public static String borrowBook(Member member, Book book, int days,
                                    List<BorrowRecord> records,
                                    Runnable afterBorrow) {
        if (days <= 0) {
            return "Số ngày mượn không hợp lệ";
        }
        if (member == null || book == null || !book.isAvailable()
                || isBookBorrowed(book.getId(), records)) {
            return "Không thể mượn sách";
        }

        Date borrowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowDate);
        cal.add(Calendar.DATE, days);
        Date dueDate = cal.getTime();

        BorrowRecord record = new BorrowRecord(book, member, borrowDate, dueDate);
        records.add(record);

        member.borrowBook(book);
        if (afterBorrow != null) {
            afterBorrow.run();
        }
        return "Mượn sách thành công! Hạn trả: " + dueDate;
    }

    public static String returnBook(Member member, Book book,
                                    List<BorrowRecord> records,
                                    Runnable afterReturn) {
        if (member == null || book == null) {
            return "Không tìm thấy giao dịch mượn sách để trả!";
        }
        for (BorrowRecord record : records) {
            if (record.getBook().getId() == book.getId()
                    && record.getMember().getId() == member.getId()
                    && !record.isReturned()) {
                record.setReturned(new Date());
                record.getMember().returnBook(record.getBook());
                if (afterReturn != null) {
                    afterReturn.run();
                }
                Date now = record.getReturnDate();
                if (now.after(record.getDueDate())) {
                    long diffMs = now.getTime() - record.getDueDate().getTime();
                    long diffDays = diffMs / (1000 * 60 * 60 * 24);
                    double fine = diffDays * 5000;
                    return "Trả sách thành công. Bạn đã trả trễ " + diffDays +
                           " ngày. Phí phạt: " + fine + " VND.";
                } else {
                    return "Trả sách thành công. Bạn không bị phạt!";
                }
            }
        }
        return "Không tìm thấy giao dịch mượn sách để trả!";
    }

    private static boolean isBookBorrowed(int bookId, List<BorrowRecord> records) {
        for (BorrowRecord r : records) {
            if (r.getBook().getId() == bookId && !r.isReturned()) {
                return true;
            }
        }
        return false;
    }
}