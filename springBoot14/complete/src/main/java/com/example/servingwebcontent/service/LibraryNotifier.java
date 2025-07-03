package com.example.servingwebcontent.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Library;
import com.example.servingwebcontent.models.Member;
import com.example.servingwebcontent.models.PhieuMuon;
import com.example.servingwebcontent.models.PhieuMuonUtils;
import com.example.servingwebcontent.service.LibraryPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.servingwebcontent.service.LibraryService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibraryNotifier {
    
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/canhbao")
    public String hienThiCanhBao(Model model) {
        try {
            List<PhieuMuon> danhSach = List.of(
                new PhieuMuon("PM001", "Nguyen Van A", LocalDate.of(2025, 6, 21)),
                new PhieuMuon("PM002", "Tran Thi B", LocalDate.of(2025, 6, 25)),
                new PhieuMuon("PM003", "Le Van C", LocalDate.of(2025, 7, 5))
            );

            int soNgayCanhBao = 5;

            List<PhieuMuon> danhSachCanhBao = danhSach.stream()
                .filter(p -> libraryService.kiemTraSachGanDenHanTra(p, soNgayCanhBao))
                .filter(p -> PhieuMuonUtils.kiemTraSachGanDenHanTra(p, soNgayCanhBao))
                .collect(Collectors.toList());

            model.addAttribute("dsCanhBao", danhSachCanhBao);
            return "canhbao";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            System.out.println("hienThiCanhBao executed");
        }
    }

    @GetMapping("/canhbao/user")
    public String hienThiCanhBaoChoNguoiMuon(@RequestParam("name") String tenNguoiMuon, Model model) {
        try {
            List<PhieuMuon> danhSach = List.of(
                new PhieuMuon("PM001", "Nguyen Van A", LocalDate.of(2025, 6, 21)),
                new PhieuMuon("PM002", "Tran Thi B", LocalDate.of(2025, 6, 25)),
                new PhieuMuon("PM003", "Le Van C", LocalDate.of(2025, 7, 5))
            );

            int soNgayCanhBao = 5;

            List<PhieuMuon> danhSachCanhBao = danhSach.stream()
                .filter(p -> libraryService.kiemTraSachGanDenHanTra(p, soNgayCanhBao))
                .filter(p -> p.getTenNguoiMuon().equalsIgnoreCase(tenNguoiMuon))
                .filter(p -> PhieuMuonUtils.kiemTraSachGanDenHanTra(p, soNgayCanhBao))
                .collect(Collectors.toList());

            model.addAttribute("dsCanhBao", danhSachCanhBao);
            model.addAttribute("tenNguoiMuon", tenNguoiMuon);
            return "canhbao";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            System.out.println("hienThiCanhBaoChoNguoiMuon executed");
        }
    }

    @GetMapping("/borrowed")
    public String hienThiSachDangMuon(Model model) {
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
            e.printStackTrace();
            return "error";
        } finally {
            System.out.println("hienThiSachDangMuon executed");
        }
    }

    public void notifyUser(Book book) {
        throw new UnsupportedOperationException("Unimplemented method 'notifyUser'");
    }
}