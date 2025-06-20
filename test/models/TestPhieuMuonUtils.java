import java.time.LocalDate;

public class TestPhieuMuonUtils {
    public static void main(String[] args) {
        // Test 1: Trong 3 ngày tới
        try {
            PhieuMuon phieu1 = new PhieuMuon(LocalDate.now().plusDays(2));
            boolean ketQua1 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu1, 3);
            System.out.println("Test 1 (Trong 3 ngày tới): " + ketQua1); // Expected: true
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 1: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 1");
        }

        // Test 2: Hết hạn sau 5 ngày
        try {
            PhieuMuon phieu2 = new PhieuMuon(LocalDate.now().plusDays(5));
            boolean ketQua2 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu2, 3);
            System.out.println("Test 2 (Hết hạn sau 5 ngày): " + ketQua2); // Expected: false
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 2: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 2");
        }

        // Test 3: Đã quá hạn
        try {
            PhieuMuon phieu3 = new PhieuMuon(LocalDate.now().minusDays(1));
            boolean ketQua3 = PhieuMuonUtils.kiemTraSachGanDenHanTra(phieu3, 3);
            System.out.println("Test 3 (Đã quá hạn): " + ketQua3); // Expected: false
        } catch (Exception e) {
            System.out.println("Lỗi trong Test 3: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Hoàn thành Test 3");
        }
    }
}
