package com.example.giaodien.Class;

public class CongViec {
    private int MACV;
    private String TENCV;

    public int getMACV() {
        return MACV;
    }

    public void setMACV(int MACV) {
        this.MACV = MACV;
    }

    public String getTENCV() {
        return TENCV;
    }

    public void setTENCV(String TENCV) {
        this.TENCV = TENCV;
    }

    public String getND_CV() {
        return ND_CV;
    }

    public void setND_CV(String ND_CV) {
        this.ND_CV = ND_CV;
    }

    public int getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(int TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }

    private String ND_CV;
    private int TINHTRANG;

    public CongViec(int MACV, String TENCV, String ND_CV, int TINHTRANG) {
        this.MACV = MACV;
        this.TENCV = TENCV;
        this.ND_CV = ND_CV;
        this.TINHTRANG = TINHTRANG;
    }
}
