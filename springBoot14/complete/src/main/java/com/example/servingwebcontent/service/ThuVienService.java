package com.example.servingwebcontent.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.example.servingwebcontent.models.PhieuMuon;

@Service
public class ThuVienService {

    public boolean kiemTraSachGanDenHanTra(PhieuMuon phieuMuon, int soNgayCanhBao) {
        try {
            LocalDate today = LocalDate.now();
            LocalDate hanCanhBao = today.plusDays(soNgayCanhBao);
            return !phieuMuon.getNgayHetHan().isBefore(today) && !phieuMuon.getNgayHetHan().isAfter(hanCanhBao);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("Đã kiểm tra hạn trả cho phiếu mượn: " + phieuMuon.getMaPhieu());
        }
    }
}
