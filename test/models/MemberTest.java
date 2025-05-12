package test.models;
import models.Book;
import models.Member;
import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {
    @Test
    public void testBorrowBook() {
        Member member = new Member("M001", "Vu");
        Book book = new Book("B004", "Java Concurrency", "Brian Goetz");
        
        member.borrowBook(book);
        assertEquals(1, member.getBorrowedBooks().size());
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Member member = new Member("M002", "Quan");
        Book book = new Book("B005", "Refactoring", "Martin Fowler");
        
        member.borrowBook(book);
        member.returnBook(book);
        assertEquals(0, member.getBorrowedBooks().size());
        assertTrue(book.isAvailable());
    }
}