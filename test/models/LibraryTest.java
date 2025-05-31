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
        library = new Library(1, "Central Library");
        book = new Book(6, "Design Patterns", "GoF");
        member = new Member(3, "Hoang");
        
        library.addBook(book);
        library.addMember(member);
    }

    @Test
    public void testAddBook() {
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testFindBook() {
        Book found = library.findBookById(6);
        assertNotNull(found);
        assertEquals("Design Patterns", found.getTitle());
    }

    @Test
    public void testLibraryOperations() {
        member.borrowBook(book);
        assertFalse(book.isAvailable());
    }
    
    @Test
    public void testLibraryCRUDOperations() {
        Book newBook = new Book(7, "New Book", "New Author");
        library.addBook(newBook);
        
        Book found = library.findBookById(7);
        assertNotNull(found);
        assertEquals("New Book", found.getTitle());
        
        // Test member operations
        Member newMember = new Member(4, "New Member");
        library.addMember(newMember);
        
        Member foundMember = library.findMemberById(4);
        assertNotNull(foundMember);
    }
}