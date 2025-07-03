package com.example.servingwebcontent.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TestReturnBook {
    @Test
    public void testReturnBookProcess() {
        Library lib = new Library(1, "CNTT Library");
        Book book = new Book(100, "OOP Java", "Nguyen Van A", LocalDate.now().plusDays(7));
        Member mem = new Member(10, "Hoang Le");

        lib.addBook(book);
        lib.addMember(mem);

        lib.borrowBook(10, 100, 3);

        BorrowRecord lastRecord = lib.getBorrowRecords().get(0);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        lastRecord.setBorrowDate(cal.getTime());
        cal.add(Calendar.DATE, 3);
        lastRecord.setDueDate(cal.getTime());

        String result1 = lib.returnBook(10, 100);
        assertTrue(result1.contains("Phí phạt"));

        String result2 = lib.returnBook(10, 100);
        assertTrue(result2.contains("Không tìm thấy"));

        String result3 = lib.returnBook(20, 100);
        assertTrue(result3.contains("Không tìm thấy"));
    }
}