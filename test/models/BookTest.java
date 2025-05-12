package test.models;
import models.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBookCreation() {
        Book book = new Book("B001", "Clean Code", "Robert Martin");
        assertEquals("B001", book.getBookId());
        assertEquals("Clean Code", book.getTitle());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testCheckOut() {
        Book book = new Book("B002", "Effective Java", "Joshua Bloch");
        book.checkOut();
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("B003", "Head First Java", "Kathy Sierra");
        book.checkOut();
        book.returnBook();
        assertTrue(book.isAvailable());
    }
}