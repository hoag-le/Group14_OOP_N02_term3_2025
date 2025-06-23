package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.PhieuMuon;
import com.example.servingwebcontent.service.ThuVienService;

@Controller
public class ThuVienController {

    @Autowired
    private ThuVienService thuVienService;

    @GetMapping("/canh-bao")
    public String hienThiPhieuMuonGanHetHan(Model model) {
        try {
            List<PhieuMuon> danhSach = layDanhSachPhieuMuon(); // từ DB hoặc fake data

            int soNgayCanhBao = 3;
            List<PhieuMuon> phieuMuonGanHetHan = danhSach.stream()
                .filter(pm -> thuVienService.kiemTraSachGanDenHanTra(pm, soNgayCanhBao))
                .collect(Collectors.toList());

            model.addAttribute("danhSachCanhBao", phieuMuonGanHetHan);
            return "canhbao"; // Trả về file canhbao.html (View)
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra log
            model.addAttribute("error", "Đã xảy ra lỗi khi xử lý dữ liệu.");
            return "error"; // Trả về trang báo lỗi nếu có (có thể tạo error.html)
        } finally {
            System.out.println("Đã chạy xong hàm hienThiPhieuMuonGanHetHan"); // Dọn dẹp/logging nếu cần
        }
    }

    // Giả lập dữ liệu (hoặc gọi từ DB)
    private List<PhieuMuon> layDanhSachPhieuMuon() {
        return List.of(
            new PhieuMuon("PM001", "Vũ", LocalDate.now().plusDays(2)),
            new PhieuMuon("PM002", "An", LocalDate.now().plusDays(6))
        );
    }
}
