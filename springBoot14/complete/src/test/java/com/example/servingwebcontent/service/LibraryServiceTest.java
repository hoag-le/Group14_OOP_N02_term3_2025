package com.example.servingwebcontent.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.servingwebcontent.models.BorrowTicket;

/** Tests for {@link LibraryService}. */

public class LibraryServiceTest {

    @Test
    public void nearDueDateReturnsTrue() {
        BorrowTicket ticket = new BorrowTicket("PM1", "User1", LocalDate.now().plusDays(2));
        LibraryService service = new LibraryService();
        assertTrue(service.isBookNearDueDate(ticket, 3));
    }

    @Test
    public void farDueDateReturnsFalse() {
        BorrowTicket ticket = new BorrowTicket("PM2", "User2", LocalDate.now().plusDays(5));
        LibraryService service = new LibraryService();
        assertFalse(service.isBookNearDueDate(ticket, 3));
    }

    @Test
    public void pastDueDateReturnsFalse() {
        BorrowTicket ticket = new BorrowTicket("PM3", "User3", LocalDate.now().minusDays(1));
        LibraryService service = new LibraryService();
        assertFalse(service.isBookNearDueDate(ticket, 3));
    }
}