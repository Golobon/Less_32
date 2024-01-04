package com.example.less_58;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setOnClickListener(view -> showDialog(DIALOG_TIME));
    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            return new TimePickerDialog(this, myCallBack,
                    myHour, myMinute,true);
        }
        return super.onCreateDialog(id);
    }
    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " +
                    myHour + " hours " +
                    myMinute + " minutes ");
        }
    };
}