package com.example.servingwebcontent.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookCreation() {
        Book book = new Book(1, "Clean Code", "Robert Martin");
        assertEquals(1, book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testCheckOut() {
        Book book = new Book(2, "Effective Java", "Joshua Bloch");
        book.checkOut();
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book(3, "Head First Java", "Kathy Sierra");
        book.checkOut();
        book.returnBook();
        assertTrue(book.isAvailable());
    }

    @Test
    public void testBookAvailability() {
        Book book = new Book(6, "Test Book", "Test Author");
        assertTrue(book.isAvailable());

        book.checkOut();
        assertFalse(book.isAvailable());

        book.returnBook();
        assertTrue(book.isAvailable());
    }
}