package test.models;
import models.Book;
import models.Member;
import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {
    @Test
    public void testBorrowBook() {
        Member member = new Member(1, "Vu");
        Book book = new Book(4, "Java Concurrency", "Brian Goetz");
        
        member.borrowBook(book);
        assertEquals(1, member.getBorrowedBooks().size());
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Member member = new Member(2, "Quan");
        Book book = new Book(5, "Refactoring", "Martin Fowler");
        
        member.borrowBook(book);
        member.returnBook(book);
        assertEquals(0, member.getBorrowedBooks().size());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testMultipleBookOperations() {
        Member member = new Member(5, "Test Member");
        Book book1 = new Book(8, "Book 1", "Author 1");
        Book book2 = new Book(9, "Book 2", "Author 2");
        
        member.borrowBook(book1);
        member.borrowBook(book2);
        assertEquals(2, member.getBorrowedBooks().size());
        
        member.returnBook(book1);
        assertEquals(1, member.getBorrowedBooks().size());
    }
}