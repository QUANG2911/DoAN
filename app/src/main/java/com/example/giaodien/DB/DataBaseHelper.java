package com.example.giaodien.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Output;
import android.os.Build;
import android.util.Log;

import com.example.giaodien.Class.CongViec;
import com.example.giaodien.Class.NhanVien;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHelper";

    //trỏ tới vị trí của data trên thiết bị
    private static String DB_PATH;
    private static  String DB_NAME = "quanLyTG.db";
    private SQLiteDatabase mDataBase;
    private Context context;


    //    TABLE NHANVIEN
    public static final String NV_TABLE = "NHANVIEN";
    public static final String NV_ID = "MANV";
    public static final String NV_TEN = "HOTEN";
    public static final String NV_CHUCVU = "CHUCVU";
    public static final String NV_DATE = "DATE";
    public static final String NV_MAIL = "EMAIL";
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
    public static final String  CT_FIRST_TIME= "GIO_BD";
    public static final String CT_LAST_DATE = "NGAYKT";
    public static final String  CT_LAST_TIME= "GIO_KT";


    //  TABLE VIECNHO
    public static final String VN_TABLE = "VIECNHO";
    public static final String VN_ID = "MANV";
    public static final String VN_TEN = "TENNV";
    public static final String VN_CV_ID = "MACV";
    public static final String VN_STAUS = "TINHTRANG";

    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, 1);

        if(Build.VERSION.SDK_INT >= 17)
        {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }

    public void CreateDataBase()
    {
        boolean mDataBaseExist = checkDataBase();
        if( !mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
                Log.e(TAG,"hàm CreateDataBase đã tạo database");
            }catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);

        Log.v("Filedb", dbFile + " " + dbFile.exists());

        return dbFile.exists();
    }

//    lấy dữ liệu từ file data trong Assets
    private void copyDataBase() throws IOException{
        InputStream minput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2048];
        int mLength;
        while((mLength = minput.read(mBuffer)) > 0)
        {
            mOutput.write(mBuffer, 0,mLength);
        }
        mOutput.flush();
        mOutput.close();
        minput.close();
    }

    // mở database để tiến hành truy vấn

    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;

        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        return  mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<CongViec> getCongViecChuaHoanThanhs()
    {
        List<CongViec> congViecs = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String Select = "Select * From " + CV_TABLE + " WHERE " + CV_STAUS + " = 0";

        Cursor cursor = sqLiteDatabase.rawQuery(Select,null);

        if(cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                while(cursor.moveToNext())
                {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(CV_ID));
                    @SuppressLint("Range") String ten = cursor.getString(cursor.getColumnIndex(CV_TEN));
                    @SuppressLint("Range") String nd = cursor.getString(cursor.getColumnIndex(CV_ND));
                    @SuppressLint("Range") int tt = cursor.getInt(cursor.getColumnIndex(CV_STAUS));

                    CongViec cv = new CongViec();
                    cv.setMACV(id);
                    cv.setND_CV(nd);
                    cv.setTENCV(ten);
                    cv.setTINHTRANG(tt);
                    congViecs.add(cv);
                }
            }
        }
        return congViecs;
    }

    public List<NhanVien> getNhanVien()
    {
        List<NhanVien> nhanViens = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String Select = "Select * From " + NV_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(Select,null);

        if(cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                while(cursor.moveToNext())
                {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(NV_ID));
                    @SuppressLint("Range") String ten = cursor.getString(cursor.getColumnIndex(NV_TEN));
                    @SuppressLint("Range") String chucVu = cursor.getString(cursor.getColumnIndex(NV_CHUCVU));
                    @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(NV_DATE));
                    @SuppressLint("Range") String dc = cursor.getString(cursor.getColumnIndex(NV_DC));
                    @SuppressLint("Range") String mail = cursor.getString(cursor.getColumnIndex(NV_MAIL));
                    @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex(NV_MK));

                    Log.e("id", id +"");
                    Log.e("ten", ten +"");
                    Log.e("chucVu", chucVu +"");
                    Log.e("date", date +"");
                    Log.e("dc", dc +"");
                    Log.e("mail", mail +"");
                    Log.e("pass", pass +"");

                    NhanVien nv = new NhanVien(id,ten,chucVu,date,dc,mail,pass);

                    nhanViens.add(nv);
                }
            }
        }
        return nhanViens;
    }

    public long insertCv(CongViec cv)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CV_ID, cv.getMACV());
        contentValues.put(CV_TEN, cv.getTENCV());
        contentValues.put(CV_ND, cv.getND_CV());
        contentValues.put(CV_STAUS, cv.getTINHTRANG());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long rowId = sqLiteDatabase.insert(CV_TABLE, null, contentValues);

        return rowId;
    }


}
