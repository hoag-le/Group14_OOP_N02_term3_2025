package com.example.servingwebcontent;

import com.example.servingwebcontent.models.Book;

import java.time.LocalDate;

public class LibraryNotifier {
    public static void notifyUser(Book book) {
        if (checkDueDate(book)) {
            System.out.println("Thông báo: Sách \"" + book.getTitle() + "\" sắp hết hạn mượn.");
        }
    }

    public static boolean checkDueDate(Book book) {
        LocalDate now = LocalDate.now();
        return !book.isAvailable() && (book.getDueDate().minusDays(3).isBefore(now) || book.getDueDate().minusDays(3).isEqual(now));
    }
}

