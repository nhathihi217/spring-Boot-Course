package BAI3;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

public abstract class PhuongTien implements Comparable<PhuongTien> {
    protected String bienSo;
    protected String hangSanXuat;
    protected int namSanXuat;
    protected String mauXe;
    protected ChuXe chuXe;

    public static final List<String> HANG_HOP_LE = Arrays.asList("HONDA", "YAMAHA", "TOYOTA", "SUZUKI");

    public PhuongTien() {
    }

    public PhuongTien(String bienSo, String hangSanXuat, int namSanXuat, String mauXe, ChuXe chuXe) {
        setBienSo(bienSo);
        setHangSanXuat(hangSanXuat);
        setNamSanXuat(namSanXuat);
        this.mauXe = mauXe;
        this.chuXe = chuXe;
    }

    public static boolean isValidBienSo(String bienSo) {
        return bienSo != null && bienSo.trim().length() == 5;
    }

    public static boolean isValidHang(String hang) {
        if (hang == null) return false;
        return HANG_HOP_LE.contains(hang.trim().toUpperCase());
    }

    public static boolean isValidNamSanXuat(int nam) {
        int namHienTai = Year.now().getValue();
        return nam > 2000 && nam <= namHienTai;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        if (!isValidBienSo(bienSo)) {
            throw new IllegalArgumentException("Biển số xe phải có đúng 5 ký tự!");
        }
        this.bienSo = bienSo.trim().toUpperCase();
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        if (!isValidHang(hangSanXuat)) {
            throw new IllegalArgumentException("Hãng sản xuất chỉ được phép là một trong các hãng: Honda, Yamaha, Toyota, Suzuki!");
        }
        // Chuẩn hóa tên hãng (VD: Toyota, Honda)
        String h = hangSanXuat.trim().toLowerCase();
        this.hangSanXuat = Character.toUpperCase(h.charAt(0)) + h.substring(1);
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        int namHienTai = Year.now().getValue();
        if (!isValidNamSanXuat(namSanXuat)) {
            throw new IllegalArgumentException("Năm sản xuất phải lớn hơn 2000 và <= năm hiện tại (" + namHienTai + ")!");
        }
        this.namSanXuat = namSanXuat;
    }

    public String getMauXe() {
        return mauXe;
    }

    public void setMauXe(String mauXe) {
        this.mauXe = mauXe;
    }

    public ChuXe getChuXe() {
        return chuXe;
    }

    public void setChuXe(ChuXe chuXe) {
        this.chuXe = chuXe;
    }

    public abstract String getLoaiXe();

    // Sắp xếp mặc định theo biển số xe giảm dần (cho Requirement 2 task 6)
    @Override
    public int compareTo(PhuongTien o) {
        return o.bienSo.compareTo(this.bienSo);
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-10s | %-10s | %-6d | %-8s | %-35s",
                bienSo, getLoaiXe(), hangSanXuat, namSanXuat, mauXe,
                (chuXe != null ? chuXe.getHoTen() + " (" + chuXe.getSoCmnd() + ")" : "Chưa có"));
    }
}

