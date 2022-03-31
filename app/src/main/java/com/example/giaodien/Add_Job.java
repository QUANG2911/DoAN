package com.example.giaodien;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Locale;

public class Add_Job extends AppCompatActivity {

    Button timeBtn;
    int hour, minute;

    public Context context;
    public ArrayList<String> listArray = new ArrayList<String>() ;
    public ArrayAdapter adapter;
    public ListView listJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        timeBtn = findViewById(R.id.btn_Gio);
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int gio, int phut) {
                hour = gio;
                minute = phut;
                timeBtn.setText(String.format(Locale.getDefault(), "%02d:%02d",hour,minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour, minute,true);
        timePickerDialog.show();
    }

    public void AddSecondJob(View view) {
        EditText et_SecondJob = (EditText) findViewById(R.id.et_SecondJob);

        listJob = (ListView) findViewById(R.id.List_GhiChu);
        context = this;
        listArray.add(et_SecondJob.getText().toString().trim());
        adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,listArray);

        listJob.setAdapter(adapter);
    }


    public void PersonClick(View view) {
        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);
    }


    public void CongViecClick(View view) {
        Intent intent = new Intent(this, Add_Job.class);
        startActivity(intent);
    }

    public void LichClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}