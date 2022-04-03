package com.example.giaodien.Database.NHANVIEN;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NHANVIEN_ReaderSqlite extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME ="QUANLY_TG.db";
    public static final  String TABLE_NAME ="NHANVIEN";
    public static final  String COLUMN_MANV ="MANV";
    public static final  String COLUMN_TEN ="HOTEN";
    public static final  String COLUMN_CHUCVU ="CHUCVU";
    public static final  String COLUMN_NGAYSINH ="NGAYSING";
    public static final  String COLUMN_EMAIL ="EMAIL";
    public static final  String COLUMN_SDT ="SDT";
    public static final  String COLUMN_DC ="DC";
    public static final  String COLUMN_MK ="MATKHAU";

    public static final  int VERSION = 1;

    public NHANVIEN_ReaderSqlite(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_NV = "CREATE TABLE " + TABLE_NAME +"(" +" "
                                               + COLUMN_MANV +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                + COLUMN_TEN +" VARCHAR, " + COLUMN_CHUCVU +" VARCHAR, " + COLUMN_NGAYSINH +" VARCHAR, "
                                                + COLUMN_EMAIL +" VARCHAR, " + COLUMN_SDT +" VARCHAR, " + COLUMN_DC +" VARCHAR, " + COLUMN_MK +" VARCHAR )";

        Log.e("sql", CREATE_TABLE_NV );
        sqLiteDatabase.execSQL(CREATE_TABLE_NV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNV(NHAN_VIEN nv)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MANV, nv.getMANV());
        contentValues.put(COLUMN_TEN, nv.getHOTEN());
        contentValues.put(COLUMN_CHUCVU, nv.getCHUCVU());
        contentValues.put(COLUMN_DC, nv.getDC());
        contentValues.put(COLUMN_EMAIL, nv.getEMAIL());
        contentValues.put(COLUMN_MK, nv.getMATKHAU());
        contentValues.put(COLUMN_NGAYSINH, nv.getNGAYSING());
        contentValues.put(COLUMN_SDT, nv.getSDT());

        long reuslt = db.insert(TABLE_NAME, null, contentValues);

        return  reuslt;
    }

    public  long updateNV(NHAN_VIEN nv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MANV, nv.getMANV());
        contentValues.put(COLUMN_TEN, nv.getHOTEN());
        contentValues.put(COLUMN_CHUCVU, nv.getCHUCVU());
        contentValues.put(COLUMN_DC, nv.getDC());
        contentValues.put(COLUMN_EMAIL, nv.getEMAIL());
        contentValues.put(COLUMN_MK, nv.getMATKHAU());
        contentValues.put(COLUMN_NGAYSINH, nv.getNGAYSING());
        contentValues.put(COLUMN_SDT, nv.getSDT());

        long reuslt = db.update(TABLE_NAME, contentValues, COLUMN_MANV + "=?", new String[]{nv.getMANV()});

        return reuslt;
    }

    public List<NHAN_VIEN> getAllStudents()
    {
        List<NHAN_VIEN> listNv = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                @SuppressLint("Range") String MANV = cursor.getString(cursor.getColumnIndex(COLUMN_MANV));
                @SuppressLint("Range") String HOTEN = cursor.getString(cursor.getColumnIndex(COLUMN_TEN));
                @SuppressLint("Range") String CHUCVU = cursor.getString(cursor.getColumnIndex(COLUMN_CHUCVU));
                @SuppressLint("Range") String DC = cursor.getString(cursor.getColumnIndex(COLUMN_DC));
                @SuppressLint("Range") String EMAIL = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                @SuppressLint("Range") String NGAYSINH = cursor.getString(cursor.getColumnIndex(COLUMN_NGAYSINH));
                @SuppressLint("Range") String SDT = cursor.getString(cursor.getColumnIndex(COLUMN_SDT));
                @SuppressLint("Range") String MATKHAU = cursor.getString(cursor.getColumnIndex(COLUMN_MK));

                NHAN_VIEN nv = new NHAN_VIEN();
                nv.setMANV(MANV);
                nv.setHOTEN(HOTEN);
                nv.setCHUCVU(CHUCVU);
                nv.setDC(DC);
                nv.setEMAIL(EMAIL);
                nv.setNGAYSING(NGAYSINH);
                nv.setSDT(SDT);
                nv.setMATKHAU(MATKHAU);

                listNv.add(nv);

                cursor.moveToNext();
            }

            cursor.close();
        }
        return listNv;
    }
}
