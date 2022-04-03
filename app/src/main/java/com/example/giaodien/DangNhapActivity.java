package com.example.giaodien;

import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giaodien.Database.DBcontext;


public class DangNhapActivity extends AppCompatActivity {

    DBcontext db = new DBcontext( DangNhapActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
    }

    public void DangNhapClick(View view) {
        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);
    }
}