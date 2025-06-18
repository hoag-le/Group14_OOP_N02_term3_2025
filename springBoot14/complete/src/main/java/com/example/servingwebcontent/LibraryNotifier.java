package com.example.servingwebcontent;

import com.example.servingwebcontent.models.Book;

public class LibraryNotifier {
    public void notifyUser(Book book) {
            System.out.println("Thông báo: Sách \"" + book.getTitle() + "\" sắp hết hạn mượn.");
        }
}

    


