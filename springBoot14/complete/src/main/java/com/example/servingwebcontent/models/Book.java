package com.example.servingwebcontent.models;

import java.time.LocalDate;

public class Book {
    private String title;
    private String borrowerName;
    private LocalDate dueDate;

    public Book(String title, String borrowerName, LocalDate dueDate) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
