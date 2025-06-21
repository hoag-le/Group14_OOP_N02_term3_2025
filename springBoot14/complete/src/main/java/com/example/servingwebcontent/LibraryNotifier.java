package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibraryNotifier {

    @GetMapping("/canhbao")
    public String hienThiCanhBao(Model model) {
        // Giả sử bạn có sẵn danh sách phiếu mượn (có thể là lấy từ service hoặc hard-code)
        List<PhieuMuon> danhSach = List.of(
            new PhieuMuon("PM001", "Nguyen Van A", LocalDate.of(2025, 6, 21)),
            new PhieuMuon("PM002", "Tran Thi B", LocalDate.of(2025, 6, 25)),
            new PhieuMuon("PM003", "Le Van C", LocalDate.of(2025, 7, 5))
        );

        int soNgayCanhBao = 5;

        List<PhieuMuon> danhSachCanhBao = danhSach.stream()
            .filter(p -> PhieuMuonUtils.kiemTraSachGanDenHanTra(p, soNgayCanhBao))
            .collect(Collectors.toList());

        model.addAttribute("dsCanhBao", danhSachCanhBao);
        return "canhbao";
    }
}


