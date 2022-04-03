package com.example.giaodien.Class;

public class VIECNHO {
    private int MANV;
    private String TEN_NV;

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getTEN_NV() {
        return TEN_NV;
    }

    public void setTEN_NV(String TEN_NV) {
        this.TEN_NV = TEN_NV;
    }

    public int getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(int TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }

    public int getMACV() {
        return MACV;
    }

    public void setMACV(int MACV) {
        this.MACV = MACV;
    }

    private int TINHTRANG;
    private int MACV;
    public VIECNHO(int MANV, String TEN_NV, int TINHTRANG, int MACV) {
        this.MANV = MANV;
        this.TEN_NV = TEN_NV;
        this.TINHTRANG = TINHTRANG;
        this.MACV = MACV;
    }




}
