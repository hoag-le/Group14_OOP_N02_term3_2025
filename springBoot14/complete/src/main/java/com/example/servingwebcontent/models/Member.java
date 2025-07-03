package com.example.servingwebcontent.models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        try {
            if (book.isAvailable()) {
                book.checkOut();
                borrowedBooks.add(book);
            } else {
                System.out.println("Book is not available for borrowing");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("borrowBook executed");
        }
    }

    public void returnBook(Book book) {
        try {
            if (borrowedBooks.contains(book)) {
                book.returnBook();
                borrowedBooks.remove(book);
            } else {
                System.out.println("This book was not borrowed by this member");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("returnBook executed");
        }
    }
}