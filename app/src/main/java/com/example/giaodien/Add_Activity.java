package com.example.giaodien;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.giaodien.Class.CT_CV;
import com.example.giaodien.Class.CongViec;
import com.example.giaodien.Class.VIECNHO;
import com.example.giaodien.DB.DataBaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Add_Activity extends AppCompatActivity {

    public DataBaseHelper db;

    int hour, minute;
    Button timeBtn;

    public Context context;
    public ArrayList<VIECNHO> listCheck = new ArrayList<VIECNHO>() ;
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
        db = new DataBaseHelper(Add_Activity.this);

        innerDATE();


        listJob.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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

        String ten = et_SecondJob.getText().toString().trim();
        int i = 0;
        VIECNHO vn = new VIECNHO(0,ten,i,0);
        listCheck.add(vn);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,listCheck);

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


    public void AddJob(View view) {

        SparseBooleanArray tinhTrang = listJob.getCheckedItemPositions();
        int dem = 0;
        for (int i = 0 ;i < listJob.getCount(); i++)
        {
               CheckedTextView tam =  ((CheckedTextView) listJob.getChildAt(i));

               String ten = tam.getText().toString().trim();
               int stauts = 0;
               if(tam.isChecked() == true)
               {
                   stauts = 1;
               }

               int MACV = 1;

               VIECNHO vn = new VIECNHO();
               vn.setMANV(2);
               vn.setTEN_NV(ten);
               vn.setTINHTRANG(stauts);
               vn.setMACV(MACV);

               long x = db.insertVn(vn);
               if(x > 0)
               {
                   dem++;
               }
        }
        Toast.makeText(this,"số lượng hàm nhập đươc " + dem,Toast.LENGTH_LONG).show();
    }
}

