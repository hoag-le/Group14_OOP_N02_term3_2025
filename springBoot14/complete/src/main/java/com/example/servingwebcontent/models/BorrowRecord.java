package com.example.servingwebcontent.models;
import java.util.Date;

public class BorrowRecord {
    private Book book;
    private Member member;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private boolean isReturned;

    public BorrowRecord(Book book, Member member, Date borrowDate, Date dueDate) {
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
    public void setBorrowDate(Date d) { this.borrowDate = d; }
    public void setDueDate(Date d) { this.dueDate = d; }
    
    public void setReturned(Date returnDate) {
        try {
            this.isReturned = true;
            this.returnDate = returnDate;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("setReturned executed");
        }
    }
}
