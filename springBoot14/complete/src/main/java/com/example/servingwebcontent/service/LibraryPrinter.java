package com.example.servingwebcontent.service;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryPrinter {

    private static final Logger logger = LoggerFactory.getLogger(LibraryPrinter.class);

    public static void printBorrowedBooks(Library library) {
        logger.info("Borrowed Books:");
        for (BorrowRecord record : library.getBorrowRecords()) {
            if (!record.isReturned()) {
                Book b = record.getBook();
                Member m = record.getMember();
                logger.info("{} - {} (borrowed by {})", b.getId(), b.getTitle(), m.getName());
            }
        }
    }

    public static List<BorrowRecord> getBorrowedRecords(Library library) {
        List<BorrowRecord> result = new ArrayList<>();
        for (BorrowRecord record : library.getBorrowRecords()) {
            if (!record.isReturned()) {
                result.add(record);
            }
        }
        return result;
    }
}