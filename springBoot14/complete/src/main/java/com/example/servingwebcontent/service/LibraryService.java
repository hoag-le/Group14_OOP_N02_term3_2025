package com.example.servingwebcontent.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.models.BorrowTicket;

@Service
public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);

    /**
     * Determine whether a borrow ticket is near its due date.
     *
     * @param ticket      the borrow ticket
     * @param warningDays number of days before due date to warn
     * @return {@code true} if the ticket's due date is within the warning window
     */
    public boolean isBookNearDueDate(BorrowTicket ticket, int warningDays) {
        LocalDate today = LocalDate.now();
        LocalDate warningDate = today.plusDays(warningDays);
        boolean result = !ticket.getDueDate().isBefore(today) && !ticket.getDueDate().isAfter(warningDate);
        logger.debug("Checked due date for ticket {}: {}", ticket.getTicketId(), result);
        return result;
    }
}