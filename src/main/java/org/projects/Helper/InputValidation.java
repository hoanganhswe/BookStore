package org.projects.Helper;

public class InputValidation {
    private static final String TEN_DANG_NHAP_REGEX = "^[a-z0-9]{4,20}$";
    private static final String TEN_NGUOI_DUNG_REGEX = "^[a-zA-ZÀ-ỹ\\s]{3,50}$";
    private static final String TEN_HOP_LE_REGEX = "^[a-zA-ZÀ-ỹ_\\s]{3,50}$"; // có _
    private static final String MA_HOP_LE_REGEX = "\\d+";
    private static final String EMAIL_HOP_LE_REGEX = "^[A-Za-z0-9+_.-]+@gmail+\\.com$";
    private static final String SO_DIEN_THOAI_HOP_LE_REGEX = "0\\d{9}";
    private static final String DIA_CHI_HOP_LE_REGEX = "^[\\p{L}0-9.,\\-/ ]{5,100}$";


    public static boolean tenDangNhapHopLe(String ten) {
        return ten != null && ten.matches(TEN_DANG_NHAP_REGEX);
    }

    public static boolean tenNguoiDungHopLe(String ten) {
        return ten != null && ten.matches(TEN_NGUOI_DUNG_REGEX);
    }

    public static boolean tenHopLe(String ten) {
        return ten != null && ten.matches(TEN_HOP_LE_REGEX);
    }
    public static boolean diachiHople(String dc) {
        return dc != null && dc.matches(DIA_CHI_HOP_LE_REGEX);
    }
    // Regex nhan vien
    public static boolean checkRong_addPlace(String addplace, String key) {
        return key == null || key.trim().isEmpty() || key.equals(addplace);
    }
    public static boolean checkMa(String ma) {
        return ma != null && ma.matches(MA_HOP_LE_REGEX);
    }
    public static boolean checkEmail(String email) {
        return email != null && email.matches(EMAIL_HOP_LE_REGEX);
    }
    public static boolean checkSoDienThoai(String sdt) {
        return sdt != null && sdt.matches(SO_DIEN_THOAI_HOP_LE_REGEX);
    }
}
