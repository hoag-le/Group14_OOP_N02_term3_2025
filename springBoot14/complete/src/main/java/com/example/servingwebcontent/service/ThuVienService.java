package com.example.servingwebcontent.service;

import com.example.servingwebcontent.PhieuMuon;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class ThuVienService {

    public boolean kiemTraSachGanDenHanTra(PhieuMuon phieuMuon, int soNgayCanhBao) {
        try {
            LocalDate today = LocalDate.now();
            LocalDate hanCanhBao = today.plusDays(soNgayCanhBao);
            return !phieuMuon.getNgayHetHan().isBefore(today) && !phieuMuon.getNgayHetHan().isAfter(hanCanhBao);
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi nếu có
            return false; // Nếu xảy ra lỗi thì coi như không gần đến hạn
        } finally {
            System.out.println("✅ Đã kiểm tra hạn trả cho phiếu mượn: " + phieuMuon.getMaPhieu());
        }
    }
}
