package com.example.servingwebcontent.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.servingwebcontent.service.BorrowService;

public class Library {

    private static final Logger logger = LoggerFactory.getLogger(Library.class);
    private int id;
    private String name;
    /**
     * Store books and members in maps for O(1) lookup by id
     * instead of linearly searching lists.
     */
    private Map<Integer, Book> books;
    private Map<Integer, Member> members;
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    
    public Library(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String borrowBook(int memberId, int bookId, int borrowDays) {
        try {
            Member member = findMemberById(memberId);
            Book book = findBookById(bookId);
            return BorrowService.borrowBook(member, book, borrowDays, borrowRecords, null);
        } catch (Exception e) {
            logger.error("Unable to borrow book", e);
            return "Không thể mượn sách";
        }
    }

public String returnBook(int memberId, int bookId) {
    try {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);
        return BorrowService.returnBook(member, book, borrowRecords, null);
    } catch (Exception e) {
        logger.error("Unable to return book", e);
        return "Không thể trả sách";
    }
}

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }
    
    public Book findBookById(int bookId) {
        return books.get(bookId);
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public Member findMemberById(int memberId) {
        return members.get(memberId);
    }

    public boolean updateBook(Book updatedBook) {
        Book existing = findBookById(updatedBook.getId());
        if (existing != null) {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setDueDate(updatedBook.getDueDate());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int bookId) {
        Book existing = findBookById(bookId);
        if (existing != null) {
            books.remove(bookId);
            return true;
        }
        return false;
    }

    public boolean updateMember(Member updatedMember) {
        Member existing = findMemberById(updatedMember.getId());
        if (existing != null) {
            existing.setName(updatedMember.getName());
            return true;
        }
        return false;
    }

    public boolean deleteMember(int memberId) {
        Member existing = findMemberById(memberId);
        if (existing != null) {
            members.remove(memberId);
            return true;
        }
        return false;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members.values());
    }
    public List<BorrowRecord> getBorrowRecords() {
    return borrowRecords;
    }
    public void displayAvailableBooks() {
        logger.info("Available Books:");
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                logger.info("{} - {} by {}", book.getId(), book.getTitle(), book.getAuthor());
            }
        }
    }
}