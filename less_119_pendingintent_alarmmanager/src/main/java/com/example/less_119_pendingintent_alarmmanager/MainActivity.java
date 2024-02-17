package com.example.less_119_pendingintent_alarmmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    private final static int NOTIFICATION_ID = 1;
    NotificationManagerCompat nm;
    AlarmManager am;
    Intent intent1;
    Intent intent2;
    PendingIntent pIntent1;
    PendingIntent pIntent2;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = NotificationManagerCompat.from(this);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn1)) {
            //intent1 = createIntent("action 1", "extra 1"); //false
            intent1 = createIntent("action", "extra 1"); //true
            pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_IMMUTABLE);

            //intent2 = createIntent("action 2", "extra 2"); //false
            intent2 = createIntent("action", "extra 2"); //true
            pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_IMMUTABLE);

            compare();
            sendNotif(1, pIntent1);
            sendNotif(2, pIntent2);
        } else if (v.equals(btn2)) {
        }
    }

    void compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + pIntent1.equals(pIntent2));
    }

    Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this, Receiver.class);
        intent.setAction(action);
        intent.putExtra("extra", extra);
        return intent;
    }

    void sendNotif(int id, PendingIntent pIntent) {
        NotificationChannel channel = new NotificationChannel(String.valueOf(NOTIFICATION_ID), "notification",
                NotificationManager.IMPORTANCE_HIGH);
        nm.createNotificationChannel(channel);
        final String strChannel = "channel" + NOTIFICATION_ID;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, strChannel);
        builder
                .setContentTitle("Title")
                .setContentText("Noyif " + id)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher).
                setAutoCancel(true).
                setContentIntent(pIntent);

        builder.setChannelId(String.valueOf(NOTIFICATION_ID));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        nm.notify(id, builder.build());
    }
}