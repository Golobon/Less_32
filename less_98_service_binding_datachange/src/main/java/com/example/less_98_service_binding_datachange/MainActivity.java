package com.example.less_98_service_binding_datachange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    MyService myService;
    TextView tvInterval;
    long interval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInterval = findViewById(R.id.tv_interval);
        intent = new Intent(this, MyService.class);
        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                myService = ((MyService.MyBinder)service).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };

        findViewById(R.id.btn_start).setOnClickListener(v -> startService(intent));

        findViewById(R.id.btn_up).setOnClickListener(v -> {
            if (!bound) return;
            interval = myService.upInterval(500);
            tvInterval.setText("interval = " + interval);
        });

        findViewById(R.id.btn_down).setOnClickListener(v -> {
            if (!bound) return;
            interval = myService.downInterval(500);
            tvInterval.setText("interval = " + interval);
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, sConn, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }
}