package BAI2;

import java.util.Arrays;

public class DanhSachHangHoa {
    private HangHoa[] danhSach;
    private int soLuong;

    // Khởi tạo danh sách với kích thước ban đầu (mặc định 10 phần tử)
    public DanhSachHangHoa() {
        this(10);
    }

    public DanhSachHangHoa(int dungLuongBanDau) {
        if (dungLuongBanDau <= 0) {
            dungLuongBanDau = 10;
        }
        this.danhSach = new HangHoa[dungLuongBanDau];
        this.soLuong = 0;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Tìm kiếm hàng hóa theo mã hàng
    public HangHoa timKiem(String maHang) {
        if (maHang == null) return null;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHang().equalsIgnoreCase(maHang.trim())) {
                return danhSach[i];
            }
        }
        return null;
    }

    // Thêm hàng hóa vào danh sách:
    // Thành công nếu không bị trùng mã hàng
    public boolean them(HangHoa hh) {
        if (hh == null) {
            return false;
        }
        // Kiểm tra trùng mã
        if (timKiem(hh.getMaHang()) != null) {
            System.out.println("❌ Lỗi: Mã hàng [" + hh.getMaHang() + "] đã tồn tại trong hệ thống!");
            return false;
        }

        // Tự động mở rộng mảng nếu đầy
        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
        }

        danhSach[soLuong++] = hh;
        return true;
    }

    // Xuất danh sách toàn bộ hàng hóa
    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sách hàng hóa hiện đang trống!");
            return;
        }

        System.out.println("=".repeat(120));
        System.out.printf("%-10s | %-20s | %-12s | %-10s | %-15s | %-15s | %-15s | %s\n",
                "MÃ HÀNG", "TÊN HÀNG", "LOẠI HÀNG", "TỒN KHO", "ĐƠN GIÁ", "TIỀN VAT", "ĐÁNH GIÁ", "THÔNG TIN CHI TIẾT");
        System.out.println("-".repeat(120));

        for (int i = 0; i < soLuong; i++) {
            System.out.println(danhSach[i]);
        }
        System.out.println("=".repeat(120));
    }

    // Thống kê số lượng tồn kho theo từng loại hàng
    public void thongKeSoLuongTonTheoLoai() {
        int tonThucPham = 0;
        int tonDienMay = 0;
        int tonSanhSu = 0;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] instanceof HangThucPham) {
                tonThucPham += danhSach[i].getSoLuongTon();
            } else if (danhSach[i] instanceof HangDienMay) {
                tonDienMay += danhSach[i].getSoLuongTon();
            } else if (danhSach[i] instanceof HangSanhSu) {
                tonSanhSu += danhSach[i].getSoLuongTon();
            }
        }

        System.out.println("\n--- BÁO CÁO TỔNG SỐ LƯỢNG TỒN KHO THEO LOẠI ---");
        System.out.println("1. Hàng Thực phẩm : " + tonThucPham + " sản phẩm");
        System.out.println("2. Hàng Điện máy   : " + tonDienMay + " sản phẩm");
        System.out.println("3. Hàng Sành sứ    : " + tonSanhSu + " sản phẩm");
        System.out.println("-> Tổng tồn kho    : " + (tonThucPham + tonDienMay + tonSanhSu) + " sản phẩm");
    }

    // Thống kê tổng tiền VAT theo từng loại hàng
    public void thongKeVATTheoLoai() {
        double vatThucPham = 0;
        double vatDienMay = 0;
        double vatSanhSu = 0;

        for (int i = 0; i < soLuong; i++) {
            // Tổng VAT = Đơn giá VAT * Số lượng tồn (hoặc theo đơn giá mỗi món)
            double vatMoiMon = danhSach[i].tinhVAT() * danhSach[i].getSoLuongTon();
            if (danhSach[i] instanceof HangThucPham) {
                vatThucPham += vatMoiMon;
            } else if (danhSach[i] instanceof HangDienMay) {
                vatDienMay += vatMoiMon;
            } else if (danhSach[i] instanceof HangSanhSu) {
                vatSanhSu += vatMoiMon;
            }
        }

        System.out.println("\n--- BÁO CÁO TỔNG TIỀN VAT THEO LÔ HÀNG TỒN KHO ---");
        System.out.printf("1. Hàng Thực phẩm (5%%)  : %s VNĐ\n", HangHoa.DF.format(vatThucPham));
        System.out.printf("2. Hàng Điện máy (10%%)  : %s VNĐ\n", HangHoa.DF.format(vatDienMay));
        System.out.printf("3. Hàng Sành sứ (10%%)   : %s VNĐ\n", HangHoa.DF.format(vatSanhSu));
        System.out.printf("-> Tổng tiền VAT         : %s VNĐ\n", HangHoa.DF.format(vatThucPham + vatDienMay + vatSanhSu));
    }
}

