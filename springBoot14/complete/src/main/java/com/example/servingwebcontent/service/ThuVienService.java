import java.time.LocalDate;

@Service
public class ThuVienService {
    public boolean kiemTraSachGanDenHanTra(PhieuMuon phieuMuon, int soNgayCanhBao) {
        LocalDate today = LocalDate.now();
        LocalDate hanCanhBao = today.plusDays(soNgayCanhBao);
        return !phieuMuon.getNgayTra().isBefore(today) && !phieuMuon.getNgayTra().isAfter(hanCanhBao);
    }
}
