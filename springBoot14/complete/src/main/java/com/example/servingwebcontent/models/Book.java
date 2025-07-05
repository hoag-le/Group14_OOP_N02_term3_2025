package com.example.servingwebcontent.models;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book {

    private static final Logger logger = LoggerFactory.getLogger(Book.class);
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

    public Book(int id, String title, String author) {
        this(id, title, author, LocalDate.now());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
            logger.debug("checkOut executed");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            logger.debug("returnBook executed");
        }
    }
}