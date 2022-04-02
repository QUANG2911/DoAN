package com.example.giaodien;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Add_Activity extends AppCompatActivity {

    int hour, minute;
    Button timeBtn;

    public Context context;
    public ArrayList<String> listArray = new ArrayList<String>() ;
    public ArrayAdapter adapter;
    public ListView listJob;
    EditText et_SecondJob;


    DatePickerDialog datePickerDialog;
    Button btn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        timeBtn = (Button) findViewById(R.id.btn_Gio);
        listJob = (ListView)  findViewById(R.id.List_GhiChu);
        et_SecondJob = (EditText) findViewById(R.id.et_SecondJob);
        btn_date =  (Button) findViewById(R.id.btn_Ngay);

        innerDATE();

    }

    private void innerDATE() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                String date = d + "/" +m +"/" +y;
                btn_date.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int day =cal.get(Calendar.MONTH);
        int month =cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,onDateSetListener, year,month,day);
    }


    public void AddSecondJob(View view) {

        listArray.add(et_SecondJob.getText().toString().trim());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listArray);

        listJob.setAdapter(adapter);

        et_SecondJob.setText("");
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                hour = h;
                minute = m;
                timeBtn.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,hour,minute, true);

        timePickerDialog.setTitle("select Time");
        timePickerDialog.show();
    }

    public void popDatePicker(View view) {
        datePickerDialog.show();
    }
}