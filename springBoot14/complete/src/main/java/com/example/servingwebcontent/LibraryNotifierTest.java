package com.example.servingwebcontent;

import com.example.servingwebcontent.models.Book;

public class LibraryNotifierTest {
    public static void main(String[] args) {
        Book book = new Book(1, "Lập trình Java", "Tác giả A");
        LibraryNotifier notifier = new LibraryNotifier();
        notifier.notifyUser(book);
    }
}
