package com.example.less_85_handler_runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;
    final String LOG_TAG = "myLogs";
    final int max = 100;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        pbCount = findViewById(R.id.pb_count);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = findViewById(R.id.tv_info);

        chbInfo = findViewById(R.id.chb_info);
        chbInfo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvInfo.setVisibility(View.VISIBLE);
                handler.post(showInfo);
            } else {
                tvInfo.setVisibility(View.GONE);
                handler.removeCallbacks(showInfo);
            }
        });

        new Thread(() -> {
            try {
                for (cnt = 1; cnt < max; cnt++) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    handler.post(updateProgress);
                }
            } catch (Exception ignore) { }
        }).start();
    }

    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);
            handler.postDelayed(showInfo, 1000);
        }
    };
}