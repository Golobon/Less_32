package com.example.less_81_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final int STATUS_NONE = 0;
    final int STATUS_CONNECTING = 1;
    final int STATUS_CONNECTED = 2;
    Handler handler;
    TextView tvStatus;
    ProgressBar pbConnect;
    Button btnConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);
        pbConnect = findViewById(R.id.pb_connect);
        btnConnect = findViewById(R.id.btn_connect);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        pbConnect.setVisibility(View.VISIBLE);
                        tvStatus.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        pbConnect.setVisibility(View.GONE);
                        tvStatus.setText("Connected");
                        break;
                }
            }
        };
        handler.sendEmptyMessage(STATUS_NONE);

        btnConnect.setOnClickListener(v -> new Thread(() -> {
            try {
                handler.sendEmptyMessage(STATUS_CONNECTING);
                TimeUnit.SECONDS.sleep(2);
                handler.sendEmptyMessage(STATUS_CONNECTED);
                TimeUnit.SECONDS.sleep(3);
                handler.sendEmptyMessage(STATUS_NONE);
            } catch (InterruptedException e) { }
        }).start());
    }
}