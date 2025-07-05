package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.payload.BorrowRequest;
import com.example.servingwebcontent.payload.ReturnRequest;
import com.example.servingwebcontent.service.LibraryManager;

@RestController
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    private LibraryManager libraryManager;

    @PostMapping("/borrow")
    public String borrow(@RequestBody BorrowRequest req) {
        return libraryManager.borrowBook(req.getMemberId(), req.getBookId(), req.getDays());
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody ReturnRequest req) {
        return libraryManager.returnBook(req.getMemberId(), req.getBookId());
    }
}