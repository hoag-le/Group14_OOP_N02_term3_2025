package com.example.servingwebcontent.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    private final Map<Integer, Library> libraries = new HashMap<>();
    private final int defaultLibraryId = 1;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MemberDao memberDao;

    @PostConstruct
    public void init() {
        Library defaultLib = new Library(defaultLibraryId, "Main Library");
        libraries.put(defaultLibraryId, defaultLib);

        List<Book> books = bookDao.findAll();
        for (Book b : books) {
            defaultLib.addBook(b);
        }
        List<Member> members = memberDao.findAll();
        for (Member m : members) {
            defaultLib.addMember(m);
        }
    }

    public synchronized String borrowBook(int memberId, int bookId, int days) {
        return borrowBook(defaultLibraryId, memberId, bookId, days);
    }

    public synchronized String borrowBook(int libraryId, int memberId, int bookId, int days) {
        Library lib = libraries.get(libraryId);
        if (lib == null) {
            return "Library not found";
        }
        return lib.borrowBook(memberId, bookId, days);
    }

    public synchronized String returnBook(int memberId, int bookId) {
        return returnBook(defaultLibraryId, memberId, bookId);
    }

    public synchronized String returnBook(int libraryId, int memberId, int bookId) {
        Library lib = libraries.get(libraryId);
        if (lib == null) {
            return "Library not found";
        }
        return lib.returnBook(memberId, bookId);
    }

    public List<BorrowRecord> getBorrowRecords() {
        Library lib = libraries.get(defaultLibraryId);
        return lib == null ? List.of() : lib.getBorrowRecords();
    }
}