package BAI2;

import java.text.DecimalFormat;

public abstract class HangHoa {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;

    protected static final DecimalFormat DF = new DecimalFormat("#,##0.00");

    public HangHoa() {
    }

    public HangHoa(String maHang, String tenHang, int soLuongTon, double donGia) {
        if (maHang == null || maHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã hàng không được để trống!");
        }
        if (soLuongTon < 0) {
            throw new IllegalArgumentException("Số lượng tồn phải >= 0!");
        }
        if (donGia < 0) {
            throw new IllegalArgumentException("Đơn giá phải >= 0!");
        }
        this.maHang = maHang.trim();
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        if (maHang == null || maHang.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã hàng không được để trống!");
        }
        this.maHang = maHang.trim();
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        if (soLuongTon < 0) {
            throw new IllegalArgumentException("Số lượng tồn phải >= 0!");
        }
        this.soLuongTon = soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Đơn giá phải >= 0!");
        }
        this.donGia = donGia;
    }

    // Phương thức trừu tượng tính VAT
    public abstract double tinhVAT();

    // Phương thức trừu tượng đánh giá mức độ tiêu thụ
    public abstract String danhGiaMucDoTieuThu();

    // Tên phân loại hàng hóa
    public abstract String getLoaiHang();

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-12s | %-10d | %-15s | %-15s | %-15s",
                maHang,
                tenHang,
                getLoaiHang(),
                soLuongTon,
                DF.format(donGia) + " VNĐ",
                DF.format(tinhVAT()) + " VNĐ",
                danhGiaMucDoTieuThu());
    }
}

