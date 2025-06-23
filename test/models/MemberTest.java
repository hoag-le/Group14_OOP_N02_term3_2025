

import models.Book;
import models.Member;
import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void testBorrowBook() {
        try {
            Member member = new Member(1, "Vu");
            Book book = new Book(4, "Java Concurrency", "Brian Goetz");

            member.borrowBook(book);
            assertEquals(1, member.getBorrowedBooks().size());
            assertFalse(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testBorrowBook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testBorrowBook");
        }
    }

    @Test
    public void testReturnBook() {
        try {
            Member member = new Member(2, "Quan");
            Book book = new Book(5, "Refactoring", "Martin Fowler");

            member.borrowBook(book);
            member.returnBook(book);
            assertEquals(0, member.getBorrowedBooks().size());
            assertTrue(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testReturnBook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testReturnBook");
        }
    }

    @Test
    public void testMultipleBookOperations() {
        try {
            Member member = new Member(5, "Test Member");
            Book book1 = new Book(8, "Book 1", "Author 1");
            Book book2 = new Book(9, "Book 2", "Author 2");

            member.borrowBook(book1);
            member.borrowBook(book2);
            assertEquals(2, member.getBorrowedBooks().size());

            member.returnBook(book1);
            assertEquals(1, member.getBorrowedBooks().size());
        } catch (Exception e) {
            System.out.println("Lỗi trong testMultipleBookOperations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testMultipleBookOperations");
        }
    }
}
