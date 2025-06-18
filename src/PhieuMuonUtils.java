import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PhieuMuonUtils {
    public static boolean kiemTraSachGanDenHanTra(PhieuMuon phieuMuon, int soNgayCanhBao) {
        LocalDate ngayHienTai = LocalDate.now();
        LocalDate ngayHetHan = phieuMuon.getNgayHetHan();

        long khoangCach = ChronoUnit.DAYS.between(ngayHienTai, ngayHetHan);

        return khoangCach > 0 && khoangCach <= soNgayCanhBao;
    }
}
