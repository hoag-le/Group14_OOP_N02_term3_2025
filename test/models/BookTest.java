package test.models;

import models.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testBookCreation() {
        try {
            Book book = new Book(1, "Clean Code", "Robert Martin");
            assertEquals(1, book.getId());
            assertEquals("Clean Code", book.getTitle());
            assertTrue(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testBookCreation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testBookCreation");
        }
    }

    @Test
    public void testCheckOut() {
        try {
            Book book = new Book(2, "Effective Java", "Joshua Bloch");
            book.checkOut();
            assertFalse(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testCheckOut: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testCheckOut");
        }
    }

    @Test
    public void testReturnBook() {
        try {
            Book book = new Book(3, "Head First Java", "Kathy Sierra");
            book.checkOut();
            book.returnBook();
            assertTrue(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testReturnBook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testReturnBook");
        }
    }

    @Test
    public void testBookAvailability() {
        try {
            Book book = new Book(6, "Test Book", "Test Author");
            assertTrue(book.isAvailable());

            book.checkOut();
            assertFalse(book.isAvailable());

            book.returnBook();
            assertTrue(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testBookAvailability: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testBookAvailability");
        }
    }
}
