package com.example.servingwebcontent.models;

import java.util.Date;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordTest {

    @Test
    public void testSetReturned() {
        Book book = new Book(1, "Title", "Author");
        Member member = new Member(1, "Member");
        Date now = new Date();
        BorrowRecord record = new BorrowRecord(book, member, now, now);
        assertFalse(record.isReturned());

        record.setReturned(new Date());
        assertTrue(record.isReturned());
        assertNotNull(record.getReturnDate());
    }
}