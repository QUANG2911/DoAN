package com.example.giaodien;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.example.giaodien.Class.CongViec;
import com.example.giaodien.DB.DataBaseHelper;

import java.util.List;

public class DangNhapActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        DataBaseHelper db = new DataBaseHelper(DangNhapActivity.this);
        db.CreateDataBase();
        db.openDataBase();
        db.getNhanVien();
    }

    public void DangNhapClick(View view) {
        Intent intent =  new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);

    }
}