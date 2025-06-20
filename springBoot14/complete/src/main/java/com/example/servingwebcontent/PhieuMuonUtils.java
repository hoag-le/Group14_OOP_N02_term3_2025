package com.example.servingwebcontent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PhieuMuonUtils {
    public static boolean kiemTraSachGanDenHanTra(PhieuMuon phieuMuon, int soNgayCanhBao) {
        try {
            LocalDate ngayHienTai = LocalDate.now();
            LocalDate ngayHetHan = phieuMuon.getNgayHetHan();
            long khoangCach = ChronoUnit.DAYS.between(ngayHienTai, ngayHetHan);
            return khoangCach > 0 && khoangCach <= soNgayCanhBao;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xử lý an toàn: nếu lỗi thì coi như không gần đến hạn
        } finally {
            System.out.println("Đã kiểm tra hạn trả sách.");
        }
    }
}
