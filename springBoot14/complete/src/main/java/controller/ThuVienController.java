@Controller
public class ThuVienController {

    @Autowired
    private ThuVienService thuVienService;

    @GetMapping("/canh-bao")
    public String hienThiPhieuMuonGanHetHan(Model model) {
        List<PhieuMuon> danhSach = layDanhSachPhieuMuon(); // từ DB hoặc fake data

        int soNgayCanhBao = 3;
        List<PhieuMuon> phieuMuonGanHetHan = danhSach.stream()
            .filter(pm -> thuVienService.kiemTraSachGanDenHanTra(pm, soNgayCanhBao))
            .collect(Collectors.toList());

        model.addAttribute("danhSachCanhBao", phieuMuonGanHetHan);
        return "canhbao"; // Trả về file canhbao.html (View)
    }

    // Giả lập dữ liệu (hoặc gọi từ DB)
    private List<PhieuMuon> layDanhSachPhieuMuon() {
        return List.of(
            new PhieuMuon("Vũ", "SpringBoot", LocalDate.now().minusDays(4), LocalDate.now().plusDays(2)),
            new PhieuMuon("An", "Java Cơ Bản", LocalDate.now().minusDays(10), LocalDate.now().plusDays(6))
        );
    }
}