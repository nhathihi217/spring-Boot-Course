package BAI3;

public class OTo extends PhuongTien {
    private int soChoNgoi;
    private String kieuDongCo;

    public OTo() {
        super();
    }

    public OTo(String bienSo, String hangSanXuat, int namSanXuat, String mauXe, ChuXe chuXe,
               int soChoNgoi, String kieuDongCo) {
        super(bienSo, hangSanXuat, namSanXuat, mauXe, chuXe);
        if (soChoNgoi <= 0) {
            throw new IllegalArgumentException("Số chỗ ngồi phải > 0!");
        }
        this.soChoNgoi = soChoNgoi;
        this.kieuDongCo = kieuDongCo;
    }

    public int getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        if (soChoNgoi <= 0) {
            throw new IllegalArgumentException("Số chỗ ngồi phải > 0!");
        }
        this.soChoNgoi = soChoNgoi;
    }

    public String getKieuDongCo() {
        return kieuDongCo;
    }

    public void setKieuDongCo(String kieuDongCo) {
        this.kieuDongCo = kieuDongCo;
    }

    @Override
    public String getLoaiXe() {
        return "Ô tô";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %d chỗ | Động cơ: %s", soChoNgoi, kieuDongCo);
    }
}

