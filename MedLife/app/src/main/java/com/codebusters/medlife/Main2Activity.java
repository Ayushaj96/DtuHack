package com.codebusters.medlife;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;



public class Main2Activity extends AppCompatActivity {
    private Button bstart,bstop,tim,btn;
    EditText ed1;
    private int yy,mm,dd;
    private int mMinute,mHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bstart= (Button) findViewById(R.id.datebutton);
        bstop= (Button) findViewById(R.id.datebutton2);
        ed1=(EditText)findViewById(R.id.editText);
        tim=(Button)findViewById(R.id.timebutton);

        final Calendar cal=Calendar.getInstance();
        yy=cal.get(Calendar.YEAR);
        mm=cal.get(Calendar.MONTH);
        dd=cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog=new DatePickerDialog(Main2Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                bstart.setText(String.format("Start Date : %d/%d/%d",dayOfMonth,month+1,year));
                            }
                        },yy,mm,dd);
                dialog.show();
            }
        });

        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog=new DatePickerDialog(Main2Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                bstop.setText(String.format("Stop Date : %d/%d/%d",dayOfMonth,month+1,year));
                            }
                        },yy,mm,dd);
                dialog.show();
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog=new TimePickerDialog(Main2Activity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tim.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                dialog.show();
            }
        });


        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn=new Intent(Main2Activity.this,MainActivity.class);
                itn.putExtra("key",ed1.getText().toString());
                startActivity(itn);
            }
        });
        String name= ed1.getText().toString();

    }
}