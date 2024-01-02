package com.example.less_59;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    int DIALOG_TIME = 1;
    int myYear = 2011;
    int myMonth = 11;
    int myDay = 22;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = findViewById(R.id.tv_date);
        tvDate.setOnClickListener(view -> showDialog(DIALOG_TIME));
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            return new DatePickerDialog(this, myCallBack,
                    myYear, myMonth, myDay);
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myYear = i;
            myMonth = i1;
            myDay = i2;
            tvDate.setText("Date is " +
                    myYear + " year " +
                    myMonth + " month " +
                    myDay + " day ");
        }
    };
}