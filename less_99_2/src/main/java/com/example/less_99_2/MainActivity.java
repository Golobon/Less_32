package com.example.less_99_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
    private static int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> notification());

        findViewById(R.id.btn_stop).setOnClickListener(v ->
                stopService(new Intent(MainActivity.this, MyService.class)));
    }

    public void notification() {
        NotificationChannel channel = new NotificationChannel(String.valueOf(NOTIFICATION_ID), "notification",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.createNotificationChannel(channel);
        final String strChannel = "channel" + NOTIFICATION_ID;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, strChannel);
                //.setSmallIcon(com.google.android.material.R.drawable.notification_bg);
        builder
                .setContentTitle("Title")
                .setContentText("This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. ")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher);

        builder.setChannelId(String.valueOf(NOTIFICATION_ID));


        notificationManager.notify(1, builder.build());
        NOTIFICATION_ID++;

    }

}