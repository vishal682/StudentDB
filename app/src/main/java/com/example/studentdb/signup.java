package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class signup extends AppCompatActivity {
    ArrayList<String> ar;
    Spinner sp;
    EditText edname,edroll,eddob,edphone;
    String stname,stroll,stdob,stphone,stdept;
    ArrayAdapter<String> adapter;
    DBH dbhobj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sp=findViewById(R.id.spinner);
        edname=findViewById(R.id.name);
        edroll=findViewById(R.id.rollno);
        eddob=findViewById(R.id.dob);
        edphone=findViewById(R.id.phone);
        ar=new ArrayList<String>();
        dbhobj=new DBH(this);
        eddob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp=new DatePickerDialog(signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        eddob.setText(dayOfMonth+"/"+(1+month)+"/"+year);
                    }
                },2023,0,1);
                dp.show();
            }
        });
        ar.add("BVoc");
        ar.add("BCA");
        ar.add("BBA");
        ar.add("BSe");
        ar.add("BA");
        ar.add("BCom");
        adapter=new ArrayAdapter<>(signup.this, android.R.layout.simple_spinner_dropdown_item,ar);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stdept=ar.get(position);
                Toast.makeText(signup.this, ""+stdept, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void sub(View view) {
        stname=edname.getText().toString();
        stroll=edroll.getText().toString();
        stdob=eddob.getText().toString();
        stphone=edphone.getText().toString();
        Toast.makeText(this, stname+"-"
                +stroll+"-"
                +stdob+"-"
                +stphone, Toast.LENGTH_SHORT).show();
        dbhobj.savedat(stname,stroll,stdob,stphone,stdept);

    }
}