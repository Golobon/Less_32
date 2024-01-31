package com.example.less_99_service_notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    public final static String FILE_NAME = "filename";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            tv.setText(fileName);
        }
        findViewById(R.id.btn_start).setOnClickListener(v -> {
            startService(new Intent(MainActivity.this, MyService.class));

        });
        findViewById(R.id.btn_stop).setOnClickListener(v -> {
            stopService(new Intent(MainActivity.this, MyService.class));
        });
    }
}