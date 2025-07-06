package com.example.servingwebcontent.models;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BorrowRecord {

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecord.class);
    private int id;
    private Book book;
    private Member member;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private boolean isReturned;

    public BorrowRecord(Book book, Member member, Date borrowDate, Date dueDate) {
        this(-1, book, member, borrowDate, dueDate);
    }

    public BorrowRecord(int id, Book book, Member member, Date borrowDate, Date dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public boolean isReturned() { return isReturned; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setBorrowDate(Date d) { this.borrowDate = d; }
    public void setDueDate(Date d) { this.dueDate = d; }
    
    public void setReturned(Date returnDate) {
        this.isReturned = true;
        this.returnDate = returnDate;
        logger.debug("setReturned executed");
    }
}