package com.example.less_80_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                tvInfo.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) {
                    btnStart.setEnabled(true);
                }
            }
        };

        btnStart = findViewById(R.id.btn_start);

        btnTest = findViewById(R.id.btn_test);

        btnStart.setOnClickListener(v -> {
            btnStart.setEnabled(false);
            new Thread(() -> {
                for (int i = 1; i < 11; i++) {
                    downloadFile();
                    handler.sendEmptyMessage(i);
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