package com.example.servingwebcontent.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();

        // Tạo dữ liệu mẫu
        books.add(new Book("Lập trình Java", "Nguyễn Văn A", LocalDate.now().plusDays(2)));
        books.add(new Book("Cấu trúc dữ liệu", "Trần Thị B", LocalDate.now().plusDays(5)));
        books.add(new Book("Hệ điều hành", "Lê Văn C", LocalDate.now().plusDays(1)));
    }

    public void notifyBooksDueSoon() {
        LocalDate today = LocalDate.now();
        for (Book book : books) {
            if (book.getDueDate() != null) {
                long daysLeft = ChronoUnit.DAYS.between(today, book.getDueDate());
                if (daysLeft <= 3 && daysLeft >= 0) {
                    System.out.println(" Sách \"" + book.getTitle() + "\" mượn bởi \"" + book.getBorrowerName() +
                        "\" sắp đến hạn trả vào ngày " + book.getDueDate());
                }
            }
        }
    }
}
