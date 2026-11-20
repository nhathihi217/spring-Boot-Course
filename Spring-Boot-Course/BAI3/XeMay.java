package BAI3;

public class XeMay extends PhuongTien {
    private double congSuat; // dung tích / công suất (cc hoặc KW)

    public XeMay() {
        super();
    }

    public XeMay(String bienSo, String hangSanXuat, int namSanXuat, String mauXe, ChuXe chuXe,
                 double congSuat) {
        super(bienSo, hangSanXuat, namSanXuat, mauXe, chuXe);
        if (congSuat <= 0) {
            throw new IllegalArgumentException("Công suất xe máy phải > 0!");
        }
        this.congSuat = congSuat;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        if (congSuat <= 0) {
            throw new IllegalArgumentException("Công suất xe máy phải > 0!");
        }
        this.congSuat = congSuat;
    }

    @Override
    public String getLoaiXe() {
        return "Xe máy";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Công suất: %.1f", congSuat);
    }
}

