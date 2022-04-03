package com.example.giaodien.Database.CONGVIEC;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CONGVIEC_ReaderSqlite extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME ="QUANLY_TG";
    public static final  String TABLE_NAME ="CONGVIEC";
    public static final  String COLUMN_MACV ="MACV";
    public static final  String COLUMN_TENCV ="TENCV";
    public static final  String COLUMN_ND_CV ="ND_CV";
    public static final  String COLUMN_TINHTRANG ="TINHTRANG";


    public static final  int VERSION = 1;

    public CONGVIEC_ReaderSqlite(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_CV = "CREATE TABLE " + TABLE_NAME +"(" +" "
                + COLUMN_MACV +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TENCV +" VARCHAR, " + COLUMN_ND_CV +" VARCHAR, " + COLUMN_TINHTRANG +" INTEGER)";

        Log.e("sqlCONGVIEC", TABLE_NAME );
        sqLiteDatabase.execSQL(CREATE_TABLE_CV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNV(CONGVIEC nv)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MACV, nv.getMACV());
        contentValues.put(COLUMN_TENCV, nv.getTENCV());
        contentValues.put(COLUMN_ND_CV, nv.getND_CV());
        contentValues.put(COLUMN_TINHTRANG, nv.getTINHTRANG());

        long reuslt = db.insert(TABLE_NAME, null, contentValues);

        return  reuslt;
    }

    public  long updateNV(CONGVIEC cv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MACV, cv.getMACV());
        contentValues.put(COLUMN_TENCV, cv.getTENCV());
        contentValues.put(COLUMN_ND_CV, cv.getND_CV());
        contentValues.put(COLUMN_TINHTRANG, cv.getTINHTRANG());

        long reuslt = db.update(TABLE_NAME, contentValues, COLUMN_MACV + "=?", new String[]{cv.getMACV()});

        return reuslt;
    }

    public List<CONGVIEC> getAllStudents()
    {
        List<CONGVIEC> listCv = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                @SuppressLint("Range") String MACV = cursor.getString(cursor.getColumnIndex(COLUMN_MACV));
                @SuppressLint("Range") String TENCV = cursor.getString(cursor.getColumnIndex(COLUMN_TENCV));
                @SuppressLint("Range") String  ND_CV = cursor.getString(cursor.getColumnIndex(COLUMN_ND_CV));
                @SuppressLint("Range") String TINHTRANG = cursor.getString(cursor.getColumnIndex(COLUMN_TINHTRANG));

                CONGVIEC cv = new CONGVIEC();
                cv.setMACV(MACV);
                cv.setTENCV(TENCV);
                cv.setND_CV(ND_CV);
                cv.setTINHTRANG(cv.TINHTRANG);

                listCv.add(cv);

                cursor.moveToNext();
            }

            cursor.close();
        }
        return listCv;
    }
}
