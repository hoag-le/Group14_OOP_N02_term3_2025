package com.example.servingwebcontent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.database.BorrowRecordDao;
import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.service.BorrowService;

@Service
public class LibraryManager {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BorrowRecordDao borrowRecordDao;

    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    @PostConstruct
    private void init() {
        borrowRecords.addAll(borrowRecordDao.findAll());
    }

    public synchronized String borrowBook(int memberId, int bookId, int days) {
        Member member = memberDao.read(memberId);
        Book book = bookDao.read(bookId);
        String msg = BorrowService.borrowBook(
                member,
                book,
                days,
                borrowRecords,
                () -> bookDao.updateAvailability(bookId, false)
        );
        if (msg.startsWith("Mượn sách thành công")) {
            BorrowRecord record = borrowRecords.get(borrowRecords.size() - 1);
            borrowRecordDao.save(record);
        }
        return msg;
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
        Member member = memberDao.read(memberId);
        Book book = bookDao.read(bookId);
        String msg = BorrowService.returnBook(
                member,
                book,
                borrowRecords,
                () -> bookDao.updateAvailability(bookId, true)
        );
        if (msg.startsWith("Trả sách thành công")) {
            for (BorrowRecord r : borrowRecords) {
                if (r.getBook().getId() == bookId && r.getMember().getId() == memberId && r.isReturned()) {
                    borrowRecordDao.update(r);
                    break;
                }
            }
        }
        return msg;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    /**
     * Get number of borrow records that are not yet returned.
     */
    public int getCurrentBorrowCount() {
        int count = 0;
        for (BorrowRecord r : borrowRecords) {
            if (!r.isReturned()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get all borrow records of a member that are not yet returned.
     */
    public List<BorrowRecord> getBorrowRecordsForMember(int memberId) {
        List<BorrowRecord> result = new ArrayList<>();
        for (BorrowRecord r : borrowRecords) {
            if (!r.isReturned() && r.getMember().getId() == memberId) {
                result.add(r);
            }
        }
        return result;
    }

    /**
     * Count how many times each book has been borrowed.
     *
     * @return map of book id to total borrow count
     */
    public Map<Integer, Integer> getBorrowCountByBook() {
        Map<Integer, Integer> result = new HashMap<>();
        for (BorrowRecord r : borrowRecords) {
            int id = r.getBook().getId();
            result.put(id, result.getOrDefault(id, 0) + 1);
        }
        return result;
    }
}