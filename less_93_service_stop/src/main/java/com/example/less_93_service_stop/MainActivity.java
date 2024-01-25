package com.example.less_93_service_stop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_start).setOnClickListener(v ->
                startService(new Intent(
                        MainActivity.this, MyService.class)));
        findViewById(R.id.btn_stop).setOnClickListener(v ->
                stopService(new Intent(
                        MainActivity.this, MyService.class)));
    }
}