package models;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    public String getBookId() {
        return bookId;
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

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Book is already checked out");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            System.out.println("Book was not checked out");
        }
    }
}