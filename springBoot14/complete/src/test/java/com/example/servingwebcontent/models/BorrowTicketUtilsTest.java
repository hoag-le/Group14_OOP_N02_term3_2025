package com.example.servingwebcontent.models;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowTicketUtilsTest {

    @Test
    public void nearDueDateReturnsTrue() {
        BorrowTicket ticket = new BorrowTicket("PM1", "User1", LocalDate.now().plusDays(2));
        assertTrue(BorrowTicketUtils.isBookNearDueDate(ticket, 3));
    }

    @Test
    public void farDueDateReturnsFalse() {
        BorrowTicket ticket = new BorrowTicket("PM2", "User2", LocalDate.now().plusDays(5));
        assertFalse(BorrowTicketUtils.isBookNearDueDate(ticket, 3));
    }

    @Test
    public void pastDueDateReturnsFalse() {
        BorrowTicket ticket = new BorrowTicket("PM3", "User3", LocalDate.now().minusDays(1));
        assertFalse(BorrowTicketUtils.isBookNearDueDate(ticket, 3));
    }
}