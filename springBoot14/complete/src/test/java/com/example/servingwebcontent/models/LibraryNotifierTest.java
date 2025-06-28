package com.example.servingwebcontent.models;

import com.example.servingwebcontent.service.LibraryNotifier;
import java.time.LocalDate;

public class LibraryNotifierTest {
    public static void main(String[] args) {
        try {
            Book book = new Book(1, "Lập trình Java", "Tác giả A", LocalDate.now().plusDays(3));
            LibraryNotifier notifier = new LibraryNotifier();
            notifier.notifyUser(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}