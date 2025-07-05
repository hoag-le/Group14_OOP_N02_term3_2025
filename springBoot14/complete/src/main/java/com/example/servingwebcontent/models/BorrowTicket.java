package com.example.servingwebcontent.models;

import java.time.LocalDate;

/**
 * Represents a ticket used when a member borrows a book from the library.
 */
public class BorrowTicket {

    private String ticketId;
    private String borrowerName;
    private LocalDate dueDate;

    public BorrowTicket(String ticketId, String borrowerName, LocalDate dueDate) {
        this.ticketId = ticketId;
        this.borrowerName = borrowerName;
        this.dueDate = dueDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}