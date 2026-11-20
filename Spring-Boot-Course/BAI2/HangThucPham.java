package BAI2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HangThucPham extends HangHoa {
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HangThucPham() {
        super();
    }

    public HangThucPham(String maHang, String tenHang, int soLuongTon, double donGia,
                        LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia);
        if (ngayHetHan != null && ngaySanXuat != null && ngayHetHan.isBefore(ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày hết hạn phải sau hoặc bằng ngày sản xuất!");
        }
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        if (ngayHetHan != null && ngaySanXuat != null && ngayHetHan.isBefore(ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày sản xuất không được sau ngày hết hạn!");
        }
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        if (ngaySanXuat != null && ngayHetHan != null && ngayHetHan.isBefore(ngaySanXuat)) {
            throw new IllegalArgumentException("Ngày hết hạn phải sau hoặc bằng ngày sản xuất!");
        }
        this.ngayHetHan = ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    // VAT của hàng thực phẩm là 5%
    @Override
    public double tinhVAT() {
        return donGia * 0.05;
    }

    // Đánh giá mức độ tiêu thụ:
    // Nếu còn hàng trong kho (soLuongTon > 0) và đã hết hạn -> "Khó bán", còn lại -> "Không đánh giá"
    @Override
    public String danhGiaMucDoTieuThu() {
        if (soLuongTon > 0 && ngayHetHan != null && ngayHetHan.isBefore(LocalDate.now())) {
            return "Khó bán";
        }
        return "Không đánh giá";
    }

    @Override
    public String getLoaiHang() {
        return "Thực phẩm";
    }

    @Override
    public String toString() {
        String nsxStr = ngaySanXuat != null ? ngaySanXuat.format(DATE_FORMATTER) : "N/A";
        String nhhStr = ngayHetHan != null ? ngayHetHan.format(DATE_FORMATTER) : "N/A";
        return super.toString() + String.format(" | NSX: %-10s | HSD: %-10s | NCC: %s", nsxStr, nhhStr, nhaCungCap);
    }
}

