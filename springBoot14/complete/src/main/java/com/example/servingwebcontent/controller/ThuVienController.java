package com.example.servingwebcontent.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.models.PhieuMuon;
import com.example.servingwebcontent.service.ThuVienService;

@Controller
public class ThuVienController {

    @Autowired
    private ThuVienService thuVienService;

    @GetMapping("/canh-bao")
    public String hienThiPhieuMuonGanHetHan(Model model) {
        try {
            List<PhieuMuon> danhSach = layDanhSachPhieuMuon();
            int soNgayCanhBao = 3;
            List<PhieuMuon> phieuMuonGanHetHan = danhSach.stream()
                .filter(pm -> thuVienService.kiemTraSachGanDenHanTra(pm, soNgayCanhBao))
                .collect(Collectors.toList());

            model.addAttribute("danhSachCanhBao", phieuMuonGanHetHan);
            return "canhbao";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Đã xảy ra lỗi khi xử lý dữ liệu.");
            return "error";
        } finally {
            System.out.println("Đã chạy xong hàm hienThiPhieuMuonGanHetHan");
        }
    }

    private List<PhieuMuon> layDanhSachPhieuMuon() {
        return List.of(
            new PhieuMuon("PM001", "Vũ", LocalDate.now().plusDays(2)),
            new PhieuMuon("PM002", "Hoàng", LocalDate.now().plusDays(6)),
            new PhieuMuon("PM002", "Quân", LocalDate.now().plusDays(4))
        );
    }
}
