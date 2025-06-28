package com.example.servingwebcontent.models;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;
    private Book book;
    private Member member;

    @Before
    public void setUp() {
        try {
            library = new Library(1, "Central Library");
            book = new Book(6, "Design Patterns", "GoF");
            member = new Member(3, "Hoang");

            library.addBook(book);
            library.addMember(member);
        } catch (Exception e) {
            System.out.println("Lỗi trong setUp: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành setUp");
        }
    }

    @Test
    public void testAddBook() {
        try {
            assertEquals(1, library.getBooks().size());
        } catch (Exception e) {
            System.out.println("Lỗi trong testAddBook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testAddBook");
        }
    }

    @Test
    public void testFindBook() {
        try {
            Book found = library.findBookById(6);
            assertNotNull(found);
            assertEquals("Design Patterns", found.getTitle());
        } catch (Exception e) {
            System.out.println("Lỗi trong testFindBook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testFindBook");
        }
    }

    @Test
    public void testLibraryOperations() {
        try {
            member.borrowBook(book);
            assertFalse(book.isAvailable());
        } catch (Exception e) {
            System.out.println("Lỗi trong testLibraryOperations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testLibraryOperations");
        }
    }

    @Test
    public void testLibraryCRUDOperations() {
        try {
            Book newBook = new Book(7, "New Book", "New Author");
            library.addBook(newBook);

            Book found = library.findBookById(7);
            assertNotNull(found);
            assertEquals("New Book", found.getTitle());

            Member newMember = new Member(4, "New Member");
            library.addMember(newMember);

            Member foundMember = library.findMemberById(4);
            assertNotNull(foundMember);
        } catch (Exception e) {
            System.out.println("Lỗi trong testLibraryCRUDOperations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành testLibraryCRUDOperations");
        }
    }
}
