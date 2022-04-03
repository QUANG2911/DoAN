package com.example.giaodien.Database.VIECNHO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.giaodien.Database.CONGVIEC.CONGVIEC;

import java.util.ArrayList;
import java.util.List;

public class VIECNHO_ReaderSqlite extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME ="QUANLY_TG";
    public static final  String TABLE_NAME ="VIECNHO";
    public static final  String COLUMN_MAVN ="MAVN";
    public static final  String COLUMN_TENVN ="TENVN";
    public static final  String COLUMN_ND_VN ="ND_VN";
    public static final  String COLUMN_MACV ="MACV";
    public static final  String COLUMN_TINHTRANG ="TINHTRANG";


    public static final  int VERSION = 1;

    public VIECNHO_ReaderSqlite(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_VN = "CREATE TABLE " + TABLE_NAME +"(" +" "
                + COLUMN_MAVN +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MACV +" INTEGER, "
                + COLUMN_TENVN +" VARCHAR, " + COLUMN_TINHTRANG +" INTEGER,"
                + " Foreign key(" + COLUMN_MACV + ") REFERENCES CONGVIEC (" + COLUMN_MACV +"))" ;

        Log.e("sqlVIECNHO", TABLE_NAME );
        sqLiteDatabase.execSQL(CREATE_TABLE_VN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNV(VIECNHO vn)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MAVN, vn.getMAVN());
        contentValues.put(COLUMN_TENVN, vn.getTENVN());
        contentValues.put(COLUMN_MACV, vn.getMACV());
        contentValues.put(COLUMN_TINHTRANG, vn.getTINHTRANG());

        long reuslt = db.insert(TABLE_NAME, null, contentValues);

        return  reuslt;
    }

    public  long updateNV(VIECNHO vn)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MAVN, vn.getMAVN());
        contentValues.put(COLUMN_TENVN, vn.getTENVN());
        contentValues.put(COLUMN_MACV, vn.getMACV());
        contentValues.put(COLUMN_TINHTRANG, vn.getTINHTRANG());

        long reuslt = db.update(TABLE_NAME, contentValues, COLUMN_MACV + "=?", new String[]{vn.getMACV()});

        return reuslt;
    }

    public List<VIECNHO> getAllStudents()
    {
        List<VIECNHO> listVN = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                @SuppressLint("Range") String MACV = cursor.getString(cursor.getColumnIndex(COLUMN_MACV));
                @SuppressLint("Range") String TENVN = cursor.getString(cursor.getColumnIndex(COLUMN_TENVN));
                @SuppressLint("Range") String  MAVN = cursor.getString(cursor.getColumnIndex(COLUMN_MAVN));
                @SuppressLint("Range") String TINHTRANG = cursor.getString(cursor.getColumnIndex(COLUMN_TINHTRANG));

                VIECNHO vN = new VIECNHO();
                vN.setMACV(MACV);
                vN.setTENVN(TENVN);
                vN.setMAVN(MAVN);
                vN.setTINHTRANG(vN.TINHTRANG);

                listVN.add(vN);

                cursor.moveToNext();
            }

            cursor.close();
        }
        return listVN;
    }
}
