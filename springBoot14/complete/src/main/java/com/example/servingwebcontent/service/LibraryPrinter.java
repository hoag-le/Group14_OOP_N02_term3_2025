package com.example.servingwebcontent.service;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;

import com.example.servingwebcontent.BorrowRecord;
import java.util.ArrayList;
import java.util.List;

public class LibraryPrinter {
    public static void printBorrowedBooks(Library library) {
        try {
            System.out.println("Borrowed Books:");
            for (BorrowRecord record : library.getBorrowRecords()) {
                if (!record.isReturned()) {
                    Book b = record.getBook();
                    Member m = record.getMember();
                    System.out.println(b.getId() + " - " + b.getTitle()
                            + " (borrowed by " + m.getName() + ")");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static List<BorrowRecord> getBorrowedRecords(Library library) {
        List<BorrowRecord> result = new ArrayList<>();
        try {
            for (BorrowRecord record : library.getBorrowRecords()) {
                if (!record.isReturned()) {
                    result.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }
}