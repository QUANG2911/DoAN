package com.example.giaodien;

import android.app.Activity;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.giaodien.DB.ListDB;

public class DangNhapActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void DangNhapClick(View view) {
        ListDB db = new ListDB(this);
        Intent intent =  new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);

    }
}