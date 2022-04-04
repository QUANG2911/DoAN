package com.example.giaodien;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giaodien.Fragment.fragmentCongViec;
import com.example.giaodien.Fragment.fragmentLich;
import com.example.giaodien.Fragment.fragmentThongKe;


public class MainActivity extends FragmentActivity {

    private Button btnLich;
    private Button btnThongKe;
    private Button btnNote;
    private Button btnCongViec;
    private Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innerFragment();
    }


    public void innerFragment()
    {
        fragmentLich firstFragment =  new fragmentLich();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.Frame_Layout,firstFragment);
        ft.commit();
    }

    public void addFragment(Fragment fragment)
    {
        FragmentManager fgt = getSupportFragmentManager();
        FragmentTransaction ft = fgt.beginTransaction();
        ft.add(R.id.Frame_Layout, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();

    }
    public void oneClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.btn_Lich:
               addFragment( new fragmentLich());
                break;
            case R.id.btn_note:
                addFragment(new fragmentCongViec());
                break;
            case R.id.btn_ThongKe:
                addFragment(new fragmentThongKe());
                break;
            case R.id.btn_person:
                intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.addImg:
                intent = new Intent(MainActivity.this, Add_Activity.class);
                startActivity(intent);
                break;
        }
    }

}