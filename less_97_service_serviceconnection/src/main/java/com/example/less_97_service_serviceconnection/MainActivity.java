package com.example.less_97_service_serviceconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class);

        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };

        findViewById(R.id.btn_start).setOnClickListener(v ->
                startService(intent));

        findViewById(R.id.btn_stop).setOnClickListener(v ->
                stopService(intent));

        findViewById(R.id.btn_bind).setOnClickListener(v ->
                bindService(intent, sConn, BIND_AUTO_CREATE));

//        findViewById(R.id.btn_bind).setOnClickListener(v ->
//                bindService(intent, sConn, 0));

        findViewById(R.id.btn_unbind).setOnClickListener(v ->
                unBind());
    }
    public void unBind() {
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBind();
    }
}