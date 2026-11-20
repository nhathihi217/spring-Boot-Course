package BAI2;

public class HangDienMay extends HangHoa {
    private int thoiGianBaoHanh; // tính theo tháng, >= 0
    private double congSuat;       // tính theo KW, >= 0

    public HangDienMay() {
        super();
    }

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia,
                       int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia);
        if (thoiGianBaoHanh < 0) {
            throw new IllegalArgumentException("Thời gian bảo hành phải >= 0 tháng!");
        }
        if (congSuat < 0) {
            throw new IllegalArgumentException("Công suất phải >= 0 KW!");
        }
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        if (thoiGianBaoHanh < 0) {
            throw new IllegalArgumentException("Thời gian bảo hành phải >= 0 tháng!");
        }
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        if (congSuat < 0) {
            throw new IllegalArgumentException("Công suất phải >= 0 KW!");
        }
        this.congSuat = congSuat;
    }

    // VAT của hàng điện máy là 10%
    @Override
    public double tinhVAT() {
        return donGia * 0.10;
    }

    // Đánh giá mức độ tiêu thụ:
    // Nếu số lượng tồn kho < 3 -> "Bán được", còn lại -> "Không đánh giá"
    @Override
    public String danhGiaMucDoTieuThu() {
        if (soLuongTon < 3) {
            return "Bán được";
        }
        return "Không đánh giá";
    }

    @Override
    public String getLoaiHang() {
        return "Điện máy";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | BH: %2d tháng | CS: %5.1f KW", thoiGianBaoHanh, congSuat);
    }
}

