package com.example.servingwebcontent.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Utility methods for working with {@link BorrowTicket}. */
public class BorrowTicketUtils {

    /**
     * Checks if the due date of a borrow ticket is within the configured warning window.
     *
     * @param ticket          borrow ticket to check
     * @param warningDays     number of days before the due date to issue a warning
     * @return {@code true} if the due date is within the warning window
     */
    public static boolean isBookNearDueDate(BorrowTicket ticket, int warningDays) {
        LocalDate today = LocalDate.now();
        LocalDate dueDate = ticket.getDueDate();
        long daysBetween = ChronoUnit.DAYS.between(today, dueDate);
        return daysBetween > 0 && daysBetween <= warningDays;
    }
}