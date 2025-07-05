package com.example.servingwebcontent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.models.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping
    public List<Book> allBooks() {
        return bookDao.findAll();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("q") String query) {
        return bookDao.search(query);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookDao.save(book);
            return ResponseEntity.ok("ok");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("database error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        try {
            bookDao.update(book);
            return ResponseEntity.ok("ok");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("database error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            bookDao.delete(id);
            return ResponseEntity.ok("ok");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("database error");
        }
    }
}