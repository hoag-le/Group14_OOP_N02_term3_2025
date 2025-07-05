package com.example.servingwebcontent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.models.BorrowTicket;
import com.example.servingwebcontent.models.BorrowTicketUtils;
import com.example.servingwebcontent.service.LibraryPrinter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibraryNotifier {

    private static final Logger logger = LoggerFactory.getLogger(LibraryNotifier.class);

    @GetMapping("/warning")
    public String showWarning(Model model) {
        try {
            List<BorrowTicket> tickets = List.of(
                new BorrowTicket("PM001", "Nguyen Van A", LocalDate.of(2025, 6, 21)),
                new BorrowTicket("PM002", "Tran Thi B", LocalDate.of(2025, 6, 25)),
                new BorrowTicket("PM003", "Le Van C", LocalDate.of(2025, 7, 5))
            );

            int warningDays = 5;

            List<BorrowTicket> toWarn = tickets.stream()
                .filter(p -> BorrowTicketUtils.isBookNearDueDate(p, warningDays))
                .collect(Collectors.toList());

            model.addAttribute("warningTickets", toWarn);
            return "canhbao";
        } catch (Exception e) {
            logger.error("Failed to show warnings", e);
            return "error";
        }
    }

    @GetMapping("/warning/user")
    public String showUserWarning(@RequestParam("name") String borrowerName, Model model) {
        try {
            List<BorrowTicket> tickets = List.of(
                new BorrowTicket("PM001", "Nguyen Van A", LocalDate.of(2025, 6, 21)),
                new BorrowTicket("PM002", "Tran Thi B", LocalDate.of(2025, 6, 25)),
                new BorrowTicket("PM003", "Le Van C", LocalDate.of(2025, 7, 5))
            );

            int warningDays = 5;

            List<BorrowTicket> toWarn = tickets.stream()
                .filter(p -> p.getBorrowerName().equalsIgnoreCase(borrowerName))
                .filter(p -> BorrowTicketUtils.isBookNearDueDate(p, warningDays))
                .collect(Collectors.toList());

            model.addAttribute("warningTickets", toWarn);
            model.addAttribute("borrowerName", borrowerName);
            return "canhbao";
        } catch (Exception e) {
            logger.error("Failed to show user warnings", e);
            return "error";
        }
    }

    @GetMapping("/borrowed")
    public String showBorrowedBooks(Model model) {
        try {
            Library library = new Library(1, "Demo Library");
            Member member = new Member(1, "Nguyen Van A");
            Book book = new Book(1, "Java OOP", "Tac gia A", LocalDate.now().plusDays(3));
            library.addBook(book);
            library.addMember(member);
            library.borrowBook(1, 1, 3);

            List<BorrowRecord> borrowed = LibraryPrinter.getBorrowedRecords(library);
            model.addAttribute("borrowed", borrowed);
            return "borrowed";
        } catch (Exception e) {
            logger.error("Failed to show borrowed books", e);
            return "error";
        }
    }

    public void notifyUser(Book book) {
        throw new UnsupportedOperationException("Unimplemented method 'notifyUser'");
    }
}