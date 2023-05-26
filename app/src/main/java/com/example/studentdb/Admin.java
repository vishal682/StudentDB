package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    ArrayList<String> deptname;
    ArrayAdapter<String> adapter;
    Spinner sp;
    String stdept;
    DBH dbhobj;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        sp=findViewById(R.id.spinner2);
        tv=findViewById(R.id.textView);
        dbhobj=new DBH(this);
        deptname=new ArrayList<>();
        deptname.add("BVoc");
        deptname.add("BCA");
        deptname.add("BBA");
        deptname.add("BSe");
        deptname.add("BA");
        deptname.add("BCom");
        adapter=new ArrayAdapter<>(Admin.this, android.R.layout.simple_spinner_dropdown_item,deptname);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stdept=deptname.get(position);
                Toast.makeText(Admin.this, ""+stdept, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void get(View view) {
        String res =dbhobj.getdat(stdept);
        tv.setText(res);
    }
}