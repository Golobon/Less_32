package com.example.less_99_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    public final static String FILE_NAME = "filename";
    private static final int NOTIFICATION_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            notification();
        });

        findViewById(R.id.btn_stop).setOnClickListener(v ->
                stopService(new Intent(MainActivity.this, MyService.class)));
    }

    public void notification() {
        NotificationChannel channel = new NotificationChannel("1", "notification",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);


        notificationManager.createNotificationChannel(channel);
        final String SS = "channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, SS)
                .setSmallIcon(com.google.android.material.R.drawable.notification_bg);
        builder.setContentInfo("content info")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        builder.setChannelId("1");


        notificationManager.notify(1, builder.build());

    }

}