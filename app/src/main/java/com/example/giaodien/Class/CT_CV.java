package com.example.giaodien.Class;

public class CT_CV {
    private int Manv;
    private int Macv;

    public CT_CV(int manv, int macv, String ngayBD, String ngayKT) {
        Manv = manv;
        Macv = macv;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
    }

    private String NgayBD;
    private String NgayKT;

    public int getManv() {
        return Manv;
    }

    public void setManv(int manv) {
        Manv = manv;
    }

    public int getMacv() {
        return Macv;
    }

    public void setMacv(int macv) {
        Macv = macv;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String ngayBD) {
        NgayBD = ngayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String ngayKT) {
        NgayKT = ngayKT;
    }



}

