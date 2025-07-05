package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.database.BookDao;
import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.service.LibraryManager;

@Controller
public class PageController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private LibraryManager libraryManager;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("bookCount", bookDao.findAll().size());
        model.addAttribute("memberCount", memberDao.findAll().size());
        model.addAttribute("borrowCount", libraryManager.getBorrowRecords().size());
        return "index";
    }

    @GetMapping("/books")
    public String books(@RequestParam(value = "success", required = false) String success,
                        Model model) {
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("message", success != null ? "Thao tác thành công" : null);
        return "books";
    }

    @PostMapping("/books/add")
    public String addBook(@RequestParam int id,
                          @RequestParam String title,
                          @RequestParam String author,
                          Model model) {
        try {
            bookDao.save(new com.example.servingwebcontent.models.Book(id, title, author));
            return "redirect:/books?success";
        } catch (Exception e) {
            model.addAttribute("books", bookDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "books";
        }
    }

    @PostMapping("/books/update")
    public String updateBook(@RequestParam int id,
                             @RequestParam String title,
                             @RequestParam String author,
                             Model model) {
        try {
            com.example.servingwebcontent.models.Book b =
                    new com.example.servingwebcontent.models.Book(id, title, author);
            bookDao.update(b);
            return "redirect:/books?success";
        } catch (Exception e) {
            model.addAttribute("books", bookDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "books";
        }
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam int id,
                             Model model) {
        try {
            bookDao.delete(id);
            return "redirect:/books?success";
        } catch (Exception e) {
            model.addAttribute("books", bookDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "books";
        }
    }

    @GetMapping("/members")
    public String members(@RequestParam(value = "success", required = false) String success,
                          Model model) {
        model.addAttribute("members", memberDao.findAll());
        model.addAttribute("message", success != null ? "Thao tác thành công" : null);
        return "members";
    }

    @PostMapping("/members/add")
    public String addMember(@RequestParam int id,
                            @RequestParam String name,
                            Model model) {
        try {
            memberDao.save(new com.example.servingwebcontent.models.Member(id, name));
            return "redirect:/members?success";
        } catch (Exception e) {
            model.addAttribute("members", memberDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "members";
        }
    }

    @PostMapping("/members/update")
    public String updateMember(@RequestParam int id,
                               @RequestParam String name,
                               Model model) {
        try {
            com.example.servingwebcontent.models.Member m =
                    new com.example.servingwebcontent.models.Member(id, name);
            memberDao.update(m);
            return "redirect:/members?success";
        } catch (Exception e) {
            model.addAttribute("members", memberDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "members";
        }
    }

    @PostMapping("/members/delete")
    public String deleteMember(@RequestParam int id,
                               Model model) {
        try {
            memberDao.delete(id);
            return "redirect:/members?success";
        } catch (Exception e) {
            model.addAttribute("members", memberDao.findAll());
            model.addAttribute("message", e.getMessage());
            return "members";
        }
    }

    @GetMapping("/borrow")
    public String borrowPage(Model model) {
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("members", memberDao.findAll());
        return "borrow";
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam int memberId,
                         @RequestParam int bookId,
                         @RequestParam int days,
                         Model model) {
        String msg = libraryManager.borrowBook(memberId, bookId, days);
        model.addAttribute("message", msg);
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("members", memberDao.findAll());
        return "borrow";
    }

    @GetMapping("/return")
    public String returnPage(Model model) {
        model.addAttribute("records", libraryManager.getBorrowRecords());
        return "return";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int memberId,
                             @RequestParam int bookId,
                             Model model) {
        String msg = libraryManager.returnBook(memberId, bookId);
        model.addAttribute("message", msg);
        model.addAttribute("records", libraryManager.getBorrowRecords());
        return "return";
    }

    @GetMapping("/borrowed")
    public String borrowedBooks(Model model) {
        model.addAttribute("borrowed", libraryManager.getBorrowRecords().stream()
                .filter(r -> !r.isReturned())
                .toList());
        return "borrowed";
    }

    @GetMapping("/warning")
    public String warningPage(@RequestParam(value = "days", required = false, defaultValue = "3") int days,
                              Model model) {
        java.time.LocalDate today = java.time.LocalDate.now();
        java.util.List<com.example.servingwebcontent.models.BorrowRecord> warning =
                libraryManager.getBorrowRecords().stream()
                        .filter(r -> !r.isReturned())
                        .filter(r -> {
                            java.time.LocalDate due = r.getDueDate().toInstant()
                                    .atZone(java.time.ZoneId.systemDefault())
                                    .toLocalDate();
                            long diff = java.time.temporal.ChronoUnit.DAYS.between(today, due);
                            return diff > 0 && diff <= days;
                        })
                        .toList();
        model.addAttribute("warningRecords", warning);
        return "canhbao";
    }
}