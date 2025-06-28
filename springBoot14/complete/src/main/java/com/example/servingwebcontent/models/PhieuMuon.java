package com.example.servingwebcontent.models;

import java.time.LocalDate;

public class PhieuMuon {
    private String maPhieu;
    private String tenNguoiMuon;
    private LocalDate ngayHetHan;

    public PhieuMuon(String maPhieu, String tenNguoiMuon, LocalDate ngayHetHan) {
        this.maPhieu = maPhieu;
        this.tenNguoiMuon = tenNguoiMuon;
        this.ngayHetHan = ngayHetHan;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public String getTenNguoiMuon() {
        return tenNguoiMuon;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }
}
