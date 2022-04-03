package com.example.giaodien.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.giaodien.Database.NHANVIEN.NHAN_VIEN;

import java.util.ArrayList;
import java.util.List;

public class DBcontext extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME ="QUANLY_TG.db";
    public static final  int VERSION = 1;
// TABLE NHAN VIEN
    public static final  String TABLE_NV_NAME ="NHANVIEN";
    public static final  String COLUMN_NV_MANV ="MANV";
    public static final  String COLUMN_TEN ="HOTEN";
    public static final  String COLUMN_CHUCVU ="CHUCVU";
    public static final  String COLUMN_NGAYSINH ="NGAYSING";
    public static final  String COLUMN_EMAIL ="EMAIL";
    public static final  String COLUMN_SDT ="SDT";
    public static final  String COLUMN_DC ="DC";
    public static final  String COLUMN_MK ="MATKHAU";
//
// TABLE CONG VIEC
    public static final  String TABLE_CV_NAME ="CONGVIEC";
    public static final  String COLUMN_CV_MACV ="MACV";
    public static final  String COLUMN_TENCV ="TENCV";
    public static final  String COLUMN_ND_CV ="ND_CV";
    public static final  String COLUMN_TINHTRANG ="TINHTRANG";
//
//    TABLE CTCV
    public static final  String TABLE_CT_CV_NAME ="CT_CV";
    public static final  String COLUMN_CT_CV_MACV ="MACV";
    public static final  String COLUMN_CT_CV_MANV ="MANV";
    public static final  String COLUMN_NGAY_BD ="NGAY_BD";
    public static final  String COLUMN_NGAY_KT ="NGAY_KT";
    public static final  String COLUMN_THOIGIAN_BD ="THOIGIAN_BD";
    public static final  String COLUMN_THOIGIAN_KT ="THOIGIAN_KT";
//
//    TABLE VIECNHO
public static final  String TABLE_NAME ="VIECNHO";
    public static final  String COLUMN_MAVN ="MAVN";
    public static final  String COLUMN_TENVN ="TENVN";
    public static final  String COLUMN_ND_VN ="ND_VN";
    public static final  String COLUMN_VN_MACV ="MACV";
    public static final  String COLUMN_TINHTRANG_VN ="TINHTRANG";


    public DBcontext(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_NV = "CREATE TABLE " + TABLE_NV_NAME +"(" +" "
                + COLUMN_NV_MANV +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TEN +" VARCHAR, " + COLUMN_CHUCVU +" VARCHAR, " + COLUMN_NGAYSINH +" VARCHAR, "
                + COLUMN_EMAIL +" VARCHAR, " + COLUMN_SDT +" VARCHAR, " + COLUMN_DC +" VARCHAR, " + COLUMN_MK +" VARCHAR )";


        String CREATE_TABLE_CV = "CREATE TABLE " + TABLE_CV_NAME +"(" +" "
                + COLUMN_CV_MACV +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TENCV +" VARCHAR, " + COLUMN_ND_CV +" VARCHAR, " + COLUMN_TINHTRANG +" INTEGER)";

        String CREATE_TABLE_VN = "CREATE TABLE " + TABLE_NAME +"(" +" "
                + COLUMN_MAVN +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_VN_MACV +" INTEGER, "
                + COLUMN_TENVN +" VARCHAR, " + COLUMN_TINHTRANG_VN +" INTEGER,"
                + " Foreign key(" + COLUMN_CT_CV_MACV + ") REFERENCES "+ TABLE_CV_NAME +"(" + COLUMN_VN_MACV +"))" ;

        String CREATE_TABLE_CT_CV = "CREATE TABLE " + TABLE_NAME +"(" +" "
                + COLUMN_CT_CV_MACV +" INTEGER, "
                + COLUMN_CT_CV_MANV +" INTEGER, " + COLUMN_NGAY_BD +" DATE, " + COLUMN_THOIGIAN_BD + " TIME,"
                + COLUMN_NGAY_KT +" DATE, " + COLUMN_THOIGIAN_KT + " TIME,"
                + "PRIMARY KEY (" + COLUMN_CT_CV_MANV +"," +COLUMN_CT_CV_MACV + "),"
                + "FOREIGN KEY (" + COLUMN_CT_CV_MANV +  " )REFERENCES "+ TABLE_NV_NAME +"( " + COLUMN_CT_CV_MANV +"),"
                + "FOREIGN KEY (" + COLUMN_CT_CV_MACV +  " )REFERENCES "+ TABLE_CV_NAME +"( " + COLUMN_CT_CV_MACV + "))";

        Log.e("sql", CREATE_TABLE_NV );
        sqLiteDatabase.execSQL(CREATE_TABLE_NV);
        sqLiteDatabase.execSQL(CREATE_TABLE_CT_CV);
        sqLiteDatabase.execSQL(CREATE_TABLE_CV);
        sqLiteDatabase.execSQL(CREATE_TABLE_VN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

