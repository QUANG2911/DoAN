package com.example.giaodien.Class;

public class NhanVien {
    private int MANV;
    private String HOTEN;
    private String CHUCVU;
    private String NGAYSINH;
    private String EMAIL;
    private String DC;

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(String CHUCVU) {
        this.CHUCVU = CHUCVU;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDC() {
        return DC;
    }

    public void setDC(String DC) {
        this.DC = DC;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    private String MATKHAU;

    public NhanVien(int MANV, String HOTEN, String CHUCVU, String NGAYSINH, String EMAIL, String DC, String MATKHAU) {
        this.MANV = MANV;
        this.HOTEN = HOTEN;
        this.CHUCVU = CHUCVU;
        this.NGAYSINH = NGAYSINH;
        this.EMAIL = EMAIL;
        this.DC = DC;
        this.MATKHAU = MATKHAU;
    }


}
