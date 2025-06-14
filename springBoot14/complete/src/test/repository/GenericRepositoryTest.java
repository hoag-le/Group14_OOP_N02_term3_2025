package com.example.servingwebcontent.test.repository;

import com.example.servingwebcontent.models.Book;

import org.junit.Before;
import org.junit.Test;
import repository.GenericRepository;
import static org.junit.Assert.*;

public class GenericRepositoryTest {
    private GenericRepository<Book> bookRepository;

    @Before
    public void setUp() {
        bookRepository = new GenericRepository<>(Book::getId);
    }

    @Test
    public void testCreateAndRead() {
        Book book = new Book(1, "Clean Code", "Robert Martin");
        bookRepository.create(book);
        
        Book retrieved = bookRepository.read(1);
        assertNotNull(retrieved);
        assertEquals("Clean Code", retrieved.getTitle());
    }

    @Test
    public void testUpdate() {
        Book book = new Book(2, "Effective Java 2nd", "Joshua Bloch");
        bookRepository.create(book);
        
        Book updatedBook = new Book(2, "Effective Java 3rd", "Joshua Bloch");
        bookRepository.update(updatedBook);
        
        Book retrieved = bookRepository.read(2);
        assertEquals("Effective Java 3rd", retrieved.getTitle());
    }

    @Test
    public void testDelete() {
        Book book = new Book(3, "Head First Java", "Kathy Sierra");
        bookRepository.create(book);
        
        bookRepository.delete(3);
        assertNull(bookRepository.read(3));
    }

    @Test
    public void testListAll() {
        bookRepository.create(new Book(4, "Book A", "Author A"));
        bookRepository.create(new Book(5, "Book B", "Author B"));
        
        assertEquals(2, bookRepository.listAll().size());
    }
}