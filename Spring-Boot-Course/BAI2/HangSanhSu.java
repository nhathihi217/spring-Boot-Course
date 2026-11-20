package BAI2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HangSanhSu extends HangHoa {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HangSanhSu() {
        super();
    }

    public HangSanhSu(String maHang, String tenHang, int soLuongTon, double donGia,
                      String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(LocalDate ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    // VAT của hàng sành sứ là 10%
    @Override
    public double tinhVAT() {
        return donGia * 0.10;
    }

    // Đánh giá mức độ tiêu thụ:
    // Nếu số lượng tồn > 50 và thời gian lưu kho > 10 ngày -> "Bán chậm", còn lại -> "Không đánh giá"
    @Override
    public String danhGiaMucDoTieuThu() {
        if (ngayNhapKho != null) {
            long thoiGianLuuKho = ChronoUnit.DAYS.between(ngayNhapKho, LocalDate.now());
            if (soLuongTon > 50 && thoiGianLuuKho > 10) {
                return "Bán chậm";
            }
        }
        return "Không đánh giá";
    }

    @Override
    public String getLoaiHang() {
        return "Sành sứ";
    }

    @Override
    public String toString() {
        String nnkStr = ngayNhapKho != null ? ngayNhapKho.format(DATE_FORMATTER) : "N/A";
        long thoiGianLuuKho = ngayNhapKho != null ? ChronoUnit.DAYS.between(ngayNhapKho, LocalDate.now()) : 0;
        return super.toString() + String.format(" | NSX: %-15s | Nhập kho: %s (%d ngày)", nhaSanXuat, nnkStr, thoiGianLuuKho);
    }
}

