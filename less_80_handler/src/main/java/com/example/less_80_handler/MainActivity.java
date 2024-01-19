package com.example.less_80_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    Handler handler;
    TextView tvInfo;
    Button btnStart;
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tv_info);

        btnStart = findViewById(R.id.btn_start);
        btnTest = findViewById(R.id.btn_test);

        btnStart.setOnClickListener(v -> {
            new Thread(() -> {
                for (int i = 1; i < 11; i++) {
                    downloadFile();
                    tvInfo.setText("Закачано файлов: " + i);
                    Log.d(LOG_TAG, "Закачано файлов: " + i);
                }
            }).start();
        });

        btnTest.setOnClickListener(v -> Log.d(LOG_TAG, "test"));
    }

    private void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }
    }
}