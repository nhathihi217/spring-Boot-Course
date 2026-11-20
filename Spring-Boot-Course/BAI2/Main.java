package BAI2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        DanhSachHangHoa qlhh = new DanhSachHangHoa();

        // Nạp sẵn một số dữ liệu mẫu minh họa cho từng trường hợp đánh giá
        khoiTaoDuLieuMau(qlhh);

        int luaChon = -1;
        do {
            System.out.println("\n============== CHƯƠNG TRÌNH QUẢN LÝ HÀNG HÓA SIÊU THỊ ==============");
            System.out.println("1. Thêm mới hàng hóa (chọn loại: Thực phẩm / Điện máy / Sành sứ)");
            System.out.println("2. In danh sách hàng hóa (kèm VAT & đánh giá mức độ tiêu thụ)");
            System.out.println("3. Thống kê số lượng tồn kho theo từng loại hàng");
            System.out.println("4. Thống kê tổng tiền VAT theo từng loại hàng");
            System.out.println("0. Thoát chương trình");
            System.out.print("👉 Vui lòng chọn chức năng (0-4): ");

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 1:
                    menuThemHangHoa(qlhh);
                    break;
                case 2:
                    System.out.println("\n--- DANH SÁCH HÀNG HÓA HIỆN CÓ ---");
                    qlhh.xuatDanhSach();
                    break;
                case 3:
                    qlhh.thongKeSoLuongTonTheoLoai();
                    break;
                case 4:
                    qlhh.thongKeVATTheoLoai();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (luaChon != 0);
    }

    private static void menuThemHangHoa(DanhSachHangHoa qlhh) {
        System.out.println("\n--- CHỌN LOẠI HÀNG HÓA CẦN THÊM ---");
        System.out.println("1. Hàng Thực Phẩm");
        System.out.println("2. Hàng Điện Máy");
        System.out.println("3. Hàng Sành Sứ");
        System.out.print("👉 Lựa chọn: ");

        int loai;
        try {
            loai = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("❌ Lựa chọn không hợp lệ!");
            return;
        }

        try {
            System.out.print("Nhập mã hàng: ");
            String maHang = scanner.nextLine().trim();

            // Kiểm tra trùng mã trước khi nhập tiếp
            if (qlhh.timKiem(maHang) != null) {
                System.out.println("❌ Lỗi: Mã hàng [" + maHang + "] đã tồn tại trong danh sách!");
                return;
            }

            System.out.print("Nhập tên hàng: ");
            String tenHang = scanner.nextLine().trim();

            System.out.print("Nhập số lượng tồn (>= 0): ");
            int soLuongTon = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập đơn giá (>= 0): ");
            double donGia = Double.parseDouble(scanner.nextLine().trim());

            HangHoa hh = null;

            switch (loai) {
                case 1: // Hàng thực phẩm
                    System.out.print("Nhập ngày sản xuất (dd/MM/yyyy): ");
                    LocalDate nsx = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

                    System.out.print("Nhập ngày hết hạn (dd/MM/yyyy): ");
                    LocalDate nhh = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

                    System.out.print("Nhập nhà cung cấp: ");
                    String ncc = scanner.nextLine().trim();

                    hh = new HangThucPham(maHang, tenHang, soLuongTon, donGia, nsx, nhh, ncc);
                    break;

                case 2: // Hàng điện máy
                    System.out.print("Nhập thời gian bảo hành (tháng >= 0): ");
                    int bh = Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Nhập công suất (KW >= 0): ");
                    double cs = Double.parseDouble(scanner.nextLine().trim());

                    hh = new HangDienMay(maHang, tenHang, soLuongTon, donGia, bh, cs);
                    break;

                case 3: // Hàng sành sứ
                    System.out.print("Nhập nhà sản xuất: ");
                    String nsxSu = scanner.nextLine().trim();

                    System.out.print("Nhập ngày nhập kho (dd/MM/yyyy): ");
                    LocalDate nnk = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);

                    hh = new HangSanhSu(maHang, tenHang, soLuongTon, donGia, nsxSu, nnk);
                    break;

                default:
                    System.out.println("❌ Loại hàng hóa không hợp lệ!");
                    return;
            }

            if (qlhh.them(hh)) {
                System.out.println("✅ Thêm hàng hóa thành công!");
            }
        } catch (Exception ex) {
            System.out.println("❌ Lỗi nhập liệu: " + ex.getMessage());
        }
    }

    private static void khoiTaoDuLieuMau(DanhSachHangHoa qlhh) {
        // 1. Thực phẩm còn tồn và đã hết hạn -> Đánh giá: "Khó bán"
        qlhh.them(new HangThucPham("TP01", "Sữa chua Ba Vì", 15, 12000,
                LocalDate.now().minusDays(30), LocalDate.now().minusDays(5), "Ba Vì Milk"));

        // 2. Thực phẩm còn hạn -> Đánh giá: "Không đánh giá"
        qlhh.them(new HangThucPham("TP02", "Bánh mì gối", 20, 25000,
                LocalDate.now().minusDays(2), LocalDate.now().plusDays(5), "Kinh Đô"));

        // 3. Điện máy tồn kho < 3 -> Đánh giá: "Bán được"
        qlhh.them(new HangDienMay("DM01", "Tủ lạnh Panasonic", 2, 8500000, 24, 1.2));

        // 4. Điện máy tồn kho >= 3 -> Đánh giá: "Không đánh giá"
        qlhh.them(new HangDienMay("DM02", "Máy giặt Toshiba", 5, 6200000, 12, 0.8));

        // 5. Sành sứ tồn > 50 và lưu kho > 10 ngày -> Đánh giá: "Bán chậm"
        qlhh.them(new HangSanhSu("SS01", "Bộ ấm chén Bát Tràng", 60, 450000,
                "Gốm Bát Tràng", LocalDate.now().minusDays(20)));

        // 6. Sành sứ mới nhập (dưới 10 ngày) -> Đánh giá: "Không đánh giá"
        qlhh.them(new HangSanhSu("SS02", "Đĩa sứ hoa mai", 30, 35000,
                "Minh Long", LocalDate.now().minusDays(3)));
    }
}

