package BAI3;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        QuanLyPhuongTien qlpt = new QuanLyPhuongTien();

        // Khởi tạo một số dữ liệu mẫu ban đầu để kiểm thử ngay
        khoiTaoDuLieuMau(qlpt);

        int luaChon = -1;
        do {
            System.out.println("\n================ QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG (BỘ CÔNG AN) ================");
            System.out.println("1. Thêm mới phương tiện giao thông (Ô tô, Xe máy, Xe tải)");
            System.out.println("2. Tìm kiếm phương tiện theo biển số xe");
            System.out.println("3. Tìm các xe của chủ xe theo số CMND/CCCD");
            System.out.println("4. Xóa tất cả các xe của một hãng sản xuất bất kỳ");
            System.out.println("5. Cho biết hãng sản xuất nào có nhiều xe nhất đang được quản lý");
            System.out.println("6. Sắp xếp các xe theo biển số xe giảm dần");
            System.out.println("7. Thống kê số lượng từng loại phương tiện đang được quản lý");
            System.out.println("8. Hiển thị toàn bộ danh sách phương tiện");
            System.out.println("0. Thoát chương trình");
            System.out.print("👉 Vui lòng chọn chức năng (0-8): ");

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 1:
                    menuThemPhuongTien(qlpt);
                    break;
                case 2:
                    timKiemTheoBienSo(qlpt);
                    break;
                case 3:
                    timKiemXeTheoCmnd(qlpt);
                    break;
                case 4:
                    xoaXeTheoHang(qlpt);
                    break;
                case 5:
                    hangCoNhieuXeNhat(qlpt);
                    break;
                case 6:
                    qlpt.sapXepTheoBienSoGiamDan();
                    System.out.println("✅ Đã sắp xếp danh sách phương tiện theo biển số xe giảm dần:");
                    qlpt.xuatDanhSach();
                    break;
                case 7:
                    qlpt.thongKeSoLuongTheoLoaiXe();
                    break;
                case 8:
                    qlpt.xuatDanhSach();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (luaChon != 0);
    }

    private static void menuThemPhuongTien(QuanLyPhuongTien qlpt) {
        try {
            System.out.println("\n--- THÊM MỚI PHƯƠNG TIỆN ---");
            System.out.println("Chọn loại phương tiện: 1. Ô tô | 2. Xe máy | 3. Xe tải");
            System.out.print("👉 Lựa chọn: ");
            int loai = Integer.parseInt(scanner.nextLine().trim());
            if (loai < 1 || loai > 3) {
                System.out.println("❌ Loại xe không hợp lệ!");
                return;
            }

            // Nhập biển số
            System.out.print("Nhập biển số xe (đúng 5 ký tự): ");
            String bienSo = scanner.nextLine().trim().toUpperCase();
            if (!PhuongTien.isValidBienSo(bienSo)) {
                System.out.println("❌ Lỗi: Biển số xe phải có đúng 5 ký tự!");
                return;
            }
            if (qlpt.timKiemTheoBienSo(bienSo) != null) {
                System.out.println("❌ Lỗi: Biển số xe [" + bienSo + "] đã tồn tại trong hệ thống!");
                return;
            }

            // Nhập hãng sản xuất
            System.out.print("Nhập hãng sản xuất (Honda, Yamaha, Toyota, Suzuki): ");
            String hang = scanner.nextLine().trim();
            if (!PhuongTien.isValidHang(hang)) {
                System.out.println("❌ Lỗi: Hãng sản xuất chỉ được phép là Honda, Yamaha, Toyota, Suzuki!");
                return;
            }

            // Nhập năm sản xuất
            int namHienTai = Year.now().getValue();
            System.out.print("Nhập năm sản xuất (2000 < năm <= " + namHienTai + "): ");
            int nam = Integer.parseInt(scanner.nextLine().trim());
            if (!PhuongTien.isValidNamSanXuat(nam)) {
                System.out.println("❌ Lỗi: Năm sản xuất phải > 2000 và <= " + namHienTai + "!");
                return;
            }

            System.out.print("Nhập màu xe: ");
            String mauXe = scanner.nextLine().trim();

            // Nhập thông tin chủ xe
            System.out.println("--- Thông tin chủ xe ---");
            System.out.print("Nhập số CMND/CCCD (đúng 12 số): ");
            String cmnd = scanner.nextLine().trim();
            if (!ChuXe.isValidCmnd(cmnd)) {
                System.out.println("❌ Lỗi: CMND/CCCD phải có đúng 12 chữ số!");
                return;
            }

            System.out.print("Nhập họ tên chủ xe: ");
            String hoTen = scanner.nextLine().trim();

            System.out.print("Nhập email chủ xe: ");
            String email = scanner.nextLine().trim();
            if (!ChuXe.isValidEmail(email)) {
                System.out.println("❌ Lỗi: Email không đúng định dạng!");
                return;
            }

            ChuXe chuXe = new ChuXe(cmnd, hoTen, email);
            PhuongTien pt = null;

            if (loai == 1) { // Ô tô
                System.out.print("Nhập số chỗ ngồi (> 0): ");
                int soCho = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Nhập kiểu động cơ: ");
                String dongCo = scanner.nextLine().trim();
                pt = new OTo(bienSo, hang, nam, mauXe, chuXe, soCho, dongCo);
            } else if (loai == 2) { // Xe máy
                System.out.print("Nhập công suất (> 0): ");
                double congSuat = Double.parseDouble(scanner.nextLine().trim());
                pt = new XeMay(bienSo, hang, nam, mauXe, chuXe, congSuat);
            } else { // Xe tải
                System.out.print("Nhập trọng tải (tấn > 0): ");
                double trongTai = Double.parseDouble(scanner.nextLine().trim());
                pt = new XeTai(bienSo, hang, nam, mauXe, chuXe, trongTai);
            }

            if (qlpt.themPhuongTien(pt)) {
                System.out.println("✅ Thêm phương tiện thành công!");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi nhập liệu: " + e.getMessage());
        }
    }

    private static void timKiemTheoBienSo(QuanLyPhuongTien qlpt) {
        System.out.print("Nhập biển số xe cần tìm: ");
        String bienSo = scanner.nextLine().trim();
        PhuongTien pt = qlpt.timKiemTheoBienSo(bienSo);
        if (pt != null) {
            System.out.println("✅ Đã tìm thấy phương tiện:");
            System.out.println(pt);
            if (pt.getChuXe() != null) {
                System.out.println("   -> Chi tiết " + pt.getChuXe());
            }
        } else {
            System.out.println("❌ Không tìm thấy xe với biển số: " + bienSo);
        }
    }

    private static void timKiemXeTheoCmnd(QuanLyPhuongTien qlpt) {
        System.out.print("Nhập số CMND/CCCD chủ xe cần tìm: ");
        String cmnd = scanner.nextLine().trim();
        List<PhuongTien> ds = qlpt.timKiemXeTheoCmnd(cmnd);
        if (ds.isEmpty()) {
            System.out.println("❌ Không tìm thấy phương tiện nào thuộc chủ xe có CMND: " + cmnd);
        } else {
            System.out.println("✅ Tìm thấy " + ds.size() + " phương tiện:");
            for (PhuongTien pt : ds) {
                System.out.println(" - " + pt);
            }
        }
    }

    private static void xoaXeTheoHang(QuanLyPhuongTien qlpt) {
        System.out.print("Nhập tên hãng sản xuất cần xóa (Honda, Yamaha, Toyota, Suzuki): ");
        String hang = scanner.nextLine().trim();
        int soLuongXoa = qlpt.xoaTatCaXeTheoHang(hang);
        if (soLuongXoa > 0) {
            System.out.println("✅ Đã xóa thành công " + soLuongXoa + " phương tiện của hãng " + hang);
        } else {
            System.out.println("⚠️ Không có phương tiện nào của hãng " + hang + " để xóa!");
        }
    }

    private static void hangCoNhieuXeNhat(QuanLyPhuongTien qlpt) {
        Map<String, Integer> topHangs = qlpt.hangSanXuatCoNhieuXeNhat();
        if (topHangs.isEmpty()) {
            System.out.println("Danh sách hiện đang trống!");
            return;
        }
        System.out.println("🏆 Hãng sản xuất có nhiều xe nhất đang được quản lý:");
        for (Map.Entry<String, Integer> entry : topHangs.entrySet()) {
            System.out.println(" - Hãng: " + entry.getKey() + " với " + entry.getValue() + " xe");
        }
    }

    private static void khoiTaoDuLieuMau(QuanLyPhuongTien qlpt) {
        ChuXe cx1 = new ChuXe("012345678901", "Nguyễn Văn An", "an.nguyen@gmail.com");
        ChuXe cx2 = new ChuXe("098765432109", "Trần Thị Bình", "binh.tran@yahoo.com");
        ChuXe cx3 = new ChuXe("011223344556", "Lê Hoàng Long", "long.le@gmail.com");

        qlpt.themPhuongTien(new OTo("29A12", "Toyota", 2021, "Đen", cx1, 5, "V6"));
        qlpt.themPhuongTien(new OTo("30B45", "Honda", 2022, "Trắng", cx2, 7, "I4"));
        qlpt.themPhuongTien(new XeMay("59C78", "Honda", 2020, "Đỏ", cx1, 150.0));
        qlpt.themPhuongTien(new XeMay("43D99", "Yamaha", 2019, "Xanh", cx3, 125.0));
        qlpt.themPhuongTien(new XeTai("88E11", "Suzuki", 2018, "Bạc", cx2, 2.5));
    }
}

