package com.example.less_93_service_stop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            startService(new Intent(this, MyService.class)
                    .putExtra("time", 7));
            startService(new Intent(this, MyService.class)
                    .putExtra("time", 2));
            startService(new Intent(this, MyService.class)
                    .putExtra("time", 4));
        });
    }
}