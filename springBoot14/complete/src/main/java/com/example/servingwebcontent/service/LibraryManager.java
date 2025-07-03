package com.example.servingwebcontent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;

import jakarta.annotation.PostConstruct;

@Service
public class LibraryManager {

    private final Library library = new Library(1, "Main Library");

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MemberDao memberDao;

    @PostConstruct
    public void init() {
        List<Book> books = bookDao.findAll();
        for (Book b : books) {
            library.addBook(b);
        }
        List<Member> members = memberDao.findAll();
        for (Member m : members) {
            library.addMember(m);
        }
    }

    public synchronized String borrowBook(int memberId, int bookId, int days) {
        return library.borrowBook(memberId, bookId, days);
    }

    public synchronized String returnBook(int memberId, int bookId) {
        return library.returnBook(memberId, bookId);
    }

    public List<BorrowRecord> getBorrowRecords() {
        return library.getBorrowRecords();
    }
}