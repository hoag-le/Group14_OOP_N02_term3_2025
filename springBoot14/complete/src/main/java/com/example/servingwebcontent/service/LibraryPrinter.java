package com.example.servingwebcontent.service;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;

import com.example.servingwebcontent.BorrowRecord;

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
}