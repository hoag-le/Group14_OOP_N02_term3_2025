package com.example.servingwebcontent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}