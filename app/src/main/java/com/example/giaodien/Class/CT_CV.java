package com.example.giaodien.Class;

public class CT_CV {
    private int Manv;
    private int Macv;
    private String NgayBD;
    private String NgayKT;
    private String Gio_BD;
    private String Gio_KT;

    public CT_CV(int manv, int macv, String ngayBD, String ngayKT, String gio_BD, String gio_KT) {
        Manv = manv;
        Macv = macv;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        Gio_BD = gio_BD;
        Gio_KT = gio_KT;
    }



    public CT_CV() {
    }

    public String getGio_BD() {
        return Gio_BD;
    }

    public void setGio_BD(String gio_BD) {
        Gio_BD = gio_BD;
    }

    public String getGio_KT() {
        return Gio_KT;
    }

    public void setGio_KT(String gio_KT) {
        Gio_KT = gio_KT;
    }

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

