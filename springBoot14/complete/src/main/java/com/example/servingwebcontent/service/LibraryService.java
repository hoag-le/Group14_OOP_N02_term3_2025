package com.example.servingwebcontent.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.servingwebcontent.models.BorrowTicket;

/**
 * Service utilities for library related operations.
 */
@Service
public class LibraryService {

    /**
     * Determine if a borrow ticket is nearing its due date.
     *
     * @param ticket      the ticket to check
     * @param warningDays number of days before the due date considered "near"
     * @return {@code true} if the ticket's due date is within the warning window
     */
    public boolean isBookNearDueDate(BorrowTicket ticket, int warningDays) {
        LocalDate today = LocalDate.now();
        LocalDate dueDate = ticket.getDueDate();
        long daysBetween = ChronoUnit.DAYS.between(today, dueDate);
        return daysBetween > 0 && daysBetween <= warningDays;
    }
}