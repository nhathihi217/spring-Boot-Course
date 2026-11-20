package BAI3;

public class XeTai extends PhuongTien {
    private double trongTai; // tấn

    public XeTai() {
        super();
    }

    public XeTai(String bienSo, String hangSanXuat, int namSanXuat, String mauXe, ChuXe chuXe,
                 double trongTai) {
        super(bienSo, hangSanXuat, namSanXuat, mauXe, chuXe);
        if (trongTai <= 0) {
            throw new IllegalArgumentException("Trọng tải xe tải phải > 0 tấn!");
        }
        this.trongTai = trongTai;
    }

    public double getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(double trongTai) {
        if (trongTai <= 0) {
            throw new IllegalArgumentException("Trọng tải xe tải phải > 0 tấn!");
        }
        this.trongTai = trongTai;
    }

    @Override
    public String getLoaiXe() {
        return "Xe tải";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Trọng tải: %.1f tấn", trongTai);
    }
}

