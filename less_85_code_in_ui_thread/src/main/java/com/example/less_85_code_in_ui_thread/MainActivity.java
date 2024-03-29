package com.example.less_85_code_in_ui_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tv_info);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                runOnUiThread(runn1);
                TimeUnit.SECONDS.sleep(1);
                tvInfo.postDelayed(runn3, 2000);
                tvInfo.post(runn2);
            } catch (Exception ignore) {
            }
        }).start();
    }
    Runnable runn1 = () -> tvInfo.setText("runn1");
    Runnable runn2 = () -> tvInfo.setText("runn2");
    Runnable runn3 = () -> tvInfo.setText("runn3");
}