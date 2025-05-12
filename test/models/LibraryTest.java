package test.models;
import models.Book;
import models.Library;
import models.Member;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;
    private Book book;
    private Member member;

    @Before
    public void setUp() {
        library = new Library();
        book = new Book("B006", "Design Patterns", "GoF");
        member = new Member("M003", "Hoang");
        
        library.addBook(book);
        library.addMember(member);
    }

    @Test
    public void testAddBook() {
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testFindBook() {
        Book found = library.findBookById("B006");
        assertNotNull(found);
        assertEquals("Design Patterns", found.getTitle());
    }

    @Test
    public void testLibraryOperations() {
        member.borrowBook(book);
        assertFalse(book.isAvailable());
    }
}