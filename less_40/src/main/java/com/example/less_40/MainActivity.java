package com.example.less_40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = getLayoutInflater();

        LinearLayout linLayout = findViewById(R.id.ll);
        View view = inflater.inflate(R.layout.text, linLayout, true);
        LayoutParams lp = view.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view.getClass());
        Log.d(LOG_TAG, "Class of linLayout: " + lp.getClass());

        ConstraintLayout consLayout = findViewById(R.id.csl);
        View view2 = inflater.inflate(R.layout.text, consLayout, true);
        LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass());
        Log.d(LOG_TAG, "Class of consLayout: " + lp2.getClass());
    }
}