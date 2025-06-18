package com.example.servingwebcontent.models;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate; // Thêm ngày hết hạn

    public Book(int id, String title, String author,LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.dueDate = dueDate; // Gán ngày hết hạn khi tạo sách
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
        }
    }
}
