package BAI3;

import java.util.regex.Pattern;

public class ChuXe {
    private String soCmnd;
    private String hoTen;
    private String email;

    private static final Pattern CMND_PATTERN = Pattern.compile("^\\d{12}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public ChuXe() {
    }

    public ChuXe(String soCmnd, String hoTen, String email) {
        setSoCmnd(soCmnd);
        this.hoTen = hoTen;
        setEmail(email);
    }

    public static boolean isValidCmnd(String cmnd) {
        return cmnd != null && CMND_PATTERN.matcher(cmnd.trim()).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public String getSoCmnd() {
        return soCmnd;
    }

    public void setSoCmnd(String soCmnd) {
        if (!isValidCmnd(soCmnd)) {
            throw new IllegalArgumentException("Số CMND/CCCD phải gồm đúng 12 chữ số!");
        }
        this.soCmnd = soCmnd.trim();
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email không đúng định dạng (VD: example@gmail.com)!");
        }
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return String.format("Chủ xe: %s | CMND: %s | Email: %s", hoTen, soCmnd, email);
    }
}

