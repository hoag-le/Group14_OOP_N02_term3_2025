package com.example.servingwebcontent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.database.BookDao;
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

    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    public synchronized String borrowBook(int memberId, int bookId, int days) {
        Member member = memberDao.read(memberId);
        Book book = bookDao.read(bookId);
        return BorrowService.borrowBook(
                member,
                book,
                days,
                borrowRecords,
                () -> bookDao.updateAvailability(bookId, false)
        );
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
        return BorrowService.returnBook(
                member,
                book,
                borrowRecords,
                () -> bookDao.updateAvailability(bookId, true)
        );
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}