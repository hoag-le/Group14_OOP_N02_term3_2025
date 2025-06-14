package com.example.servingwebcontent.models;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int id;
    private String name;
    private List<Book> books;
    private List<Member> members;

    public Library(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public void addMember(Member member) {
        members.add(member);
    }
    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return this.books;
    }
    public List<Member> getMembers() {
        return this.members;
    }
    
    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}