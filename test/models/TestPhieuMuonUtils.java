import java.time.LocalDate;

public class TestPhieuMuonUtils {
    public static void main(String[] args) {
        // Tạo đối tượng PhieuMuon với ngày hết hạn là 2 ngày nữa
        PhieuMuon phieu1 = new PhieuMuon(LocalDate.now().plusDays(2));
        boolean ketQua1 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu1, 3);
        System.out.println("Test 1 (Trong 3 ngày tới): " + ketQua1); // Expected: true

        // Tạo phiếu hết hạn sau 5 ngày
        PhieuMuon phieu2 = new PhieuMuon(LocalDate.now().plusDays(5));
        boolean ketQua2 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu2, 3);
        System.out.println("Test 2 (Hết hạn sau 5 ngày): " + ketQua2); // Expected: false

        // Tạo phiếu đã quá hạn 1 ngày
        PhieuMuon phieu3 = new PhieuMuon(LocalDate.now().minusDays(1));
        boolean ketQua3 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu3, 3);
        System.out.println("Test 3 (Đã quá hạn): " + ketQua3); // Expected: false
    }
}
