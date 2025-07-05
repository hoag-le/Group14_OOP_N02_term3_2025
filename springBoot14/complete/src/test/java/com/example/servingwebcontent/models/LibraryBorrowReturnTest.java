package com.example.servingwebcontent.models;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryBorrowReturnTest {
    private Library library;
    private Book book;
    private Member member;

    @BeforeEach
    public void setup() {
        library = new Library(1, "Test Library");
        book = new Book(1, "Title", "Author");
        member = new Member(1, "Member");
        library.addBook(book);
        library.addMember(member);
    }

    @Test
    public void borrowBookSuccess() {
        String msg = library.borrowBook(1, 1, 3);
        assertTrue(msg.startsWith("Mượn sách thành công"));
        assertFalse(book.isAvailable());
        assertEquals(1, library.getBorrowRecords().size());
    }

    @Test
    public void borrowBookInvalidDays() {
        String msg = library.borrowBook(1, 1, 0);
        assertEquals("Số ngày mượn không hợp lệ", msg);
        assertTrue(book.isAvailable());
    }

    @Test
    public void borrowBookNotFound() {
        assertEquals("Không thể mượn sách", library.borrowBook(2, 1, 3));
        assertEquals("Không thể mượn sách", library.borrowBook(1, 99, 3));
        book.checkOut();
        assertEquals("Không thể mượn sách", library.borrowBook(1, 1, 3));
    }

    @Test
    public void returnBookOnTime() {
        library.borrowBook(1, 1, 1);
        String msg = library.returnBook(1, 1);
        assertTrue(msg.contains("không bị phạt"));
    }

    @Test
    public void returnBookLate() {
        library.borrowBook(1, 1, 3);
        BorrowRecord record = library.getBorrowRecords().get(0);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        record.setBorrowDate(cal.getTime());
        cal.add(Calendar.DATE, 3);
        record.setDueDate(cal.getTime());
        String msg = library.returnBook(1, 1);
        assertTrue(msg.contains("Phí phạt"));
    }

    @Test
    public void returnBookNotFound() {
        String msg = library.returnBook(1, 1);
        assertTrue(msg.contains("Không tìm thấy"));
    }
}