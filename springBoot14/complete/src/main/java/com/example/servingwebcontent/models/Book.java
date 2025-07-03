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
        try {
            if (isAvailable) {
                isAvailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("checkOut executed");
        }
    }

    public void returnBook() {
        try {
            if (!isAvailable) {
                isAvailable = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("returnBook executed");
        }
    }
}