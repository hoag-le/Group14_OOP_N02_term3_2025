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

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookDao.save(book);
        return "ok";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        boolean updated = bookDao.update(book);
        if (updated) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean deleted = bookDao.delete(id);
        if (deleted) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }
}