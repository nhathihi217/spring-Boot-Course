package BAI3;

import java.util.*;

public class QuanLyPhuongTien {
    private List<PhuongTien> danhSach;

    public QuanLyPhuongTien() {
        this.danhSach = new ArrayList<>();
    }

    // 1. Thêm phương tiện giao thông (kiểm tra không trùng biển số xe)
    public boolean themPhuongTien(PhuongTien pt) {
        if (pt == null) return false;

        // Biển số không được trùng lặp
        if (timKiemTheoBienSo(pt.getBienSo()) != null) {
            System.out.println("❌ Lỗi: Biển số [" + pt.getBienSo() + "] đã tồn tại trong hệ thống!");
            return false;
        }

        danhSach.add(pt);
        return true;
    }

    // 2. Tìm kiếm phương tiện theo biển số xe
    public PhuongTien timKiemTheoBienSo(String bienSo) {
        if (bienSo == null) return null;
        for (PhuongTien pt : danhSach) {
            if (pt.getBienSo().equalsIgnoreCase(bienSo.trim())) {
                return pt;
            }
        }
        return null;
    }

    // 3. Tìm các xe của chủ xe theo số CMND
    public List<PhuongTien> timKiemXeTheoCmnd(String soCmnd) {
        List<PhuongTien> ketQua = new ArrayList<>();
        if (soCmnd == null) return ketQua;

        for (PhuongTien pt : danhSach) {
            if (pt.getChuXe() != null && pt.getChuXe().getSoCmnd().equals(soCmnd.trim())) {
                ketQua.add(pt);
            }
        }
        return ketQua;
    }

    // 4. Xóa tất cả các xe của một hãng sản xuất bất kỳ
    public int xoaTatCaXeTheoHang(String hangSanXuat) {
        if (hangSanXuat == null) return 0;
        int truocKhiXoa = danhSach.size();
        danhSach.removeIf(pt -> pt.getHangSanXuat().equalsIgnoreCase(hangSanXuat.trim()));
        return truocKhiXoa - danhSach.size();
    }

    // 5. Cho biết hãng sản xuất nào có nhiều xe nhất đang được quản lý
    public Map<String, Integer> hangSanXuatCoNhieuXeNhat() {
        if (danhSach.isEmpty()) {
            return Collections.emptyMap();
        }

        // Đếm số lượng xe của từng hãng
        Map<String, Integer> demHang = new HashMap<>();
        for (PhuongTien pt : danhSach) {
            String hang = pt.getHangSanXuat();
            demHang.put(hang, demHang.getOrDefault(hang, 0) + 1);
        }

        int maxSoXe = Collections.max(demHang.values());
        Map<String, Integer> ketQua = new HashMap<>();
        for (Map.Entry<String, Integer> entry : demHang.entrySet()) {
            if (entry.getValue() == maxSoXe) {
                ketQua.put(entry.getKey(), entry.getValue());
            }
        }
        return ketQua;
    }

    // 6. Sắp xếp các xe theo biển số xe giảm dần
    public void sapXepTheoBienSoGiamDan() {
        Collections.sort(danhSach);
    }

    // 7. Thống kê mỗi loại phương tiện có bao nhiêu xe đang được quản lý
    public void thongKeSoLuongTheoLoaiXe() {
        int soOTo = 0;
        int soXeMay = 0;
        int soXeTai = 0;

        for (PhuongTien pt : danhSach) {
            if (pt instanceof OTo) soOTo++;
            else if (pt instanceof XeMay) soXeMay++;
            else if (pt instanceof XeTai) soXeTai++;
        }

        System.out.println("\n--- BÁO CÁO THỐNG KÊ THEO LOẠI PHƯƠNG TIỆN ---");
        System.out.println("1. Ô tô   : " + soOTo + " xe");
        System.out.println("2. Xe máy : " + soXeMay + " xe");
        System.out.println("3. Xe tải : " + soXeTai + " xe");
        System.out.println("-> Tổng cộng: " + danhSach.size() + " xe đang quản lý");
    }

    // Hiển thị toàn bộ danh sách
    public void xuatDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Hiện không có phương tiện nào trong danh sách!");
            return;
        }

        System.out.println("=".repeat(110));
        System.out.printf("%-8s | %-10s | %-10s | %-6s | %-8s | %-35s | %s\n",
                "BIỂN SỐ", "LOẠI XE", "HÃNG SX", "NĂM SX", "MÀU XE", "CHỦ XE (CMND)", "THÔNG SỐ ĐẶC THÙ");
        System.out.println("-".repeat(110));
        for (PhuongTien pt : danhSach) {
            System.out.println(pt);
        }
        System.out.println("=".repeat(110));
    }

    public List<PhuongTien> getDanhSach() {
        return danhSach;
    }
}

