package com.example.giaodien.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.giaodien.Class.CongViec;

import java.util.ArrayList;
import java.util.List;

public class ListDB {

//    DATABASE
    public static final String DB_NAME = "QUANLY_TG.db";
    public static final int DB_VERSION = 1;

//    TABLE NHANVIEN
    public static final String NV_TABLE = "NHANVIEN";
    public static final String NV_ID = "MANV";
    public static final String NV_TEN = "HOTEN";
    public static final String NV_CHUCVU = "CHUCVU";
    public static final String NV_DATE = "DATE";
    public static final String NV_MAIL = "EMAIL";
    public static final String NV_SDT = "SDT";
    public static final String NV_DC = "DC";
    public static final String NV_MK = "MATKHAU";

//    TABLE CONGVIEC
    public static final String CV_TABLE = "CONGVIEC";
    public static final String CV_ID = "MACV";
    public static final String CV_TEN = "TENCV";
    public static final String CV_ND = "ND_CV";
    public static final String CV_STAUS = "TINHTRANG";

//    TABLE CTCV
    public static final String CT_CV_TABLE = "CT_CV";
    public static final String CT_NV_ID = "MANV";
    public static final String CT_CV_ID = "MACV";
    public static final String  CT_FIRST_DATE= "NGAYBD";
    public static final String CT_LAST_DATE = "NGAYKT";

//  TABLE VIECNHO
    public static final String VN_TABLE = "VIECNHO";
    public static final String VN_ID = "MANV";
    public static final String VN_TEN = "TENNV";
    public static final String VN_CV_ID = "MACV";
    public static final String VN_STAUS = "TINHTRANG";

//    CREATE TABLE
    public static final String CREATE_NV_TABLE = " CREATE TABLE  " + NV_TABLE + " ("
                                                    + NV_ID +" Integer Primary Key AutoIncrement, "
                                                    + NV_TEN + " NVARCHAR(30) NOT NULL, "
                                                    + NV_CHUCVU + " NVARCHAR(15),"
                                                    + NV_DATE + " TEXT,"
                                                    + NV_MAIL + " CHAR(20) NOT NULL UNIQUE,"
                                                    + NV_SDT + " TEXT,"
                                                    + NV_DC + " NVARCHAR(50),"
                                                    + NV_MK + " TEXT);";

    public static final String CREATE_CV_TABLE = " CREATE TABLE  " + CV_TABLE + " ("
                                                                    + CV_ID +" Integer Primary Key AutoIncrement, "
                                                                    + CV_TEN + " NVARCHAR(30) NOT NULL, "
                                                                    + CV_ND + " NVARCHAR(100),"
                                                                    + CV_STAUS + " INTEGER );";

    public static final String CREATE_CT_CV_TABLE = " CREATE TABLE  " + CT_CV_TABLE + " ("
                                                                    + CT_NV_ID +" Integer Primary Key, "
                                                                    + CT_CV_ID +" Integer Primary Key, "
                                                                    + CT_FIRST_DATE + " VARCHAR(40) NOT NULL, "
                                                                    + CT_LAST_DATE + " VARCHAR(40)," +
                                                                        " FOREIGN KEY("+ CT_CV_ID + ") REFERENCES " + CV_TABLE +"( "+ CT_CV_ID + ")," +
                                                                        " FOREIGN KEY("+ CT_NV_ID + ") REFERENCES " + NV_TABLE +"( "+ CT_NV_ID + "));";

    public static final String CREATE_VN_TABLE = " CREATE TABLE  " + VN_TABLE + " ("
                                                                    + NV_ID +" Integer Primary Key AutoIncrement, "
                                                                    + VN_TEN + " NVARCHAR(30) NOT NULL, "
                                                                    + VN_CV_ID + " INTEGER NOT NULL,"
                                                                    + CV_STAUS + " INTEGER," +
                                                                     " FOREIGN KEY("+ VN_CV_ID + ") REFERENCES " + CV_TABLE +"( "+ VN_CV_ID + "));" ;


    // DROP TABLE
    public static final String DROP_VN_TABLE = " DROP TABLE IF EXISTS " + VN_TABLE;

    public static final String DROP_CT_CV_TABLE = " DROP TABLE IF EXISTS " + CT_CV_TABLE;

    public static final String DROP_CV_TABLE = " DROP TABLE IF EXISTS " + CV_TABLE;

    public static final String DROP_NV_TABLE = " DROP TABLE IF EXISTS " + NV_TABLE;


    public static class DBHepler extends SQLiteOpenHelper{

        public DBHepler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CV_TABLE);
            db.execSQL(CREATE_NV_TABLE);
            db.execSQL(CREATE_CT_CV_TABLE);
            db.execSQL(CREATE_VN_TABLE);

//            Insert into CV_TABLE
            db.execSQL("Insert into " + CV_TABLE + " VALUES('LÀM ĐỒ ÁN', 'QUANG LÀM', 0)");
            db.execSQL("Insert into " + CV_TABLE + " VALUES('LÀM DỰ ÁN', 'QUÝ LÀM', 0)");

//            INSERT INTO NV_TABLE
            db.execSQL("Insert into " + NV_TABLE + " VALUES('BÙI MINH QUANG', 'NHÂN VIÊN', '29/11/2001', 'minhquang5671@gmail.com', '0767263050', '86/4', '12345678')");
            db.execSQL("Insert into " + NV_TABLE + " VALUES('NGUYỄN HỮU QUÝ', 'GIÁM ĐỐC', '25/08/2001', 'quyhuu123@gmail.com', '0987003050', '92/5', '87654321')");

            // INSET INTO CTCV
            db.execSQL("Insert into " + CT_CV_TABLE + " VALUES(1, 1, '20/5/2022', '22/5/2022')");

            //INSERT INTO VIECNHO
            db.execSQL("Insert into " + VN_TABLE + " VALUES('LÀM GIAO DIỆN', 1, 0)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(ListDB.DROP_VN_TABLE);
            db.execSQL(ListDB.DROP_CT_CV_TABLE);
            db.execSQL(ListDB.DROP_NV_TABLE);
            db.execSQL(ListDB.DROP_CV_TABLE);
            onCreate(db);
        }
    }

    //        DATEBASE
    private SQLiteDatabase db;
    private DBHepler dbHepler;

    public ListDB(Context context)
    {
        dbHepler = new DBHepler(context, DB_NAME, null, DB_VERSION);
    }

    private void openReadableDB()
    {
        db = dbHepler.getReadableDatabase();
    }

    private void openWriteableDB()
    {
        db = dbHepler.getWritableDatabase();
    }

    private void closeDB()
    {
        if(db != null)
        {
            db.close();
        }
    }

    private ArrayList<CongViec> getCongViec()
    {
        ArrayList<CongViec> congViecs = new ArrayList<>();
        openReadableDB();
        Cursor cursor = db.query(CV_TABLE,null,null,null,null,null, null);
        while(cursor.moveToNext())
        {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(CV_ID));
            @SuppressLint("Range") String ten = cursor.getString(cursor.getColumnIndex(CV_TEN));
            @SuppressLint("Range") String nd = cursor.getString(cursor.getColumnIndex(CV_ND));
            @SuppressLint("Range") int tt = cursor.getInt(cursor.getColumnIndex(CV_STAUS));

            CongViec cv = new CongViec(id,ten,nd,tt);

            congViecs.add(cv);
        }
        if(cursor != null)
            cursor.close();
        closeDB();

        return congViecs;
    }



}


