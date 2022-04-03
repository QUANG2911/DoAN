package com.example.giaodien.Database.CT_CV;

import static com.example.giaodien.Database.CONGVIEC.CONGVIEC_ReaderSqlite.COLUMN_ND_CV;
import static com.example.giaodien.Database.CONGVIEC.CONGVIEC_ReaderSqlite.COLUMN_TINHTRANG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.giaodien.Database.CONGVIEC.CONGVIEC;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class CT_CV_ReaderSqlite extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME ="QUANLY_TG.db";
    public static final  String TABLE_NAME ="CT_CV";
    public static final  String COLUMN_MACV ="MACV";
    public static final  String COLUMN_MANV ="MANV";
    public static final  String COLUMN_NGAY_BD ="NGAY_BD";
    public static final  String COLUMN_NGAY_KT ="NGAY_KT";
    public static final  String COLUMN_THOIGIAN_BD ="THOIGIAN_BD";
    public static final  String COLUMN_THOIGIAN_KT ="THOIGIAN_KT";


    public static final  int VERSION = 1;

    public CT_CV_ReaderSqlite(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_CT_CV = "CREATE TABLE " + TABLE_NAME +"(" +" "
                + COLUMN_MACV +" INTEGER, "
                + COLUMN_MANV +" INTEGER, " + COLUMN_NGAY_BD +" DATE, " + COLUMN_THOIGIAN_BD + " TIME,"
                + COLUMN_NGAY_KT +" DATE, " + COLUMN_THOIGIAN_KT + " TIME,"
                + "PRIMARY KEY (" + COLUMN_MANV +"," +COLUMN_MACV + "),"
                + "FOREIGN KEY (" + COLUMN_MANV +  " )REFERENCES NHANVIEN( " + COLUMN_MANV +"),"
                + "FOREIGN KEY (" + COLUMN_MACV +  " )REFERENCES CONGVIEC( " + COLUMN_MACV + "))";

        Log.e("sqlCT_CV", TABLE_NAME );
        sqLiteDatabase.execSQL(CREATE_TABLE_CT_CV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNV(CT_CV ct)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MACV, ct.getMACV());
        contentValues.put(COLUMN_MANV, ct.getMANV());
        contentValues.put(COLUMN_NGAY_BD, ct.getNGAY_DB());
        contentValues.put(COLUMN_THOIGIAN_BD, ct.getTG_DB());
        contentValues.put(COLUMN_NGAY_KT, ct.getNGAY_KT());
        contentValues.put(COLUMN_THOIGIAN_KT, ct.getTG_KT());


        long reuslt = db.insert(TABLE_NAME, null, contentValues);

        return  reuslt;
    }

    public  long updateNV(CT_CV cT)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MACV, cT.getMACV());
        contentValues.put(COLUMN_MANV, cT.getMANV());
        contentValues.put(COLUMN_NGAY_BD, cT.getNGAY_DB());
        contentValues.put(COLUMN_THOIGIAN_BD, cT.getTG_DB());
        contentValues.put(COLUMN_NGAY_KT, cT.getNGAY_KT());
        contentValues.put(COLUMN_THOIGIAN_KT, cT.getTG_KT());

        long reuslt = db.update(TABLE_NAME, contentValues, COLUMN_MACV + "=?" + COLUMN_MANV + "?=" , new String[]{cT.getMACV(), cT.getMANV()});

        return reuslt;
    }

    public List<CT_CV> getAllStudents()
    {
        List<CT_CV> listct = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                @SuppressLint("Range") String MACV = cursor.getString(cursor.getColumnIndex(COLUMN_MACV));
                @SuppressLint("Range") String MANV = cursor.getString(cursor.getColumnIndex(COLUMN_MANV));
                @SuppressLint("Range") String NGAY_BD = cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_BD));
                @SuppressLint("Range") String TG_BD = cursor.getString(cursor.getColumnIndex(COLUMN_THOIGIAN_BD));
                @SuppressLint("Range") String NGAY_KT = cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_KT));
                @SuppressLint("Range") String TG_KT = cursor.getString(cursor.getColumnIndex(COLUMN_THOIGIAN_KT));

                CT_CV cT = new CT_CV();
                cT.setMACV(MACV);
                cT.setMANV(MANV);
                cT.setNGAY_DB(NGAY_BD);
                cT.setTG_DB(TG_BD);
                cT.setNGAY_KT(NGAY_KT);
                cT.setTG_KT(TG_KT);

                listct.add(cT);

                cursor.moveToNext();
            }

            cursor.close();
        }
        return listct;
    }
}
