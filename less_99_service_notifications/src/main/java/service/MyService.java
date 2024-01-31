package service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.less_99_service_notifications.MainActivity;
import com.example.less_99_service_notifications.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService es;
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        es = Executors.newFixedThreadPool(2);
        context = getBaseContext();
    }
    ;    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        es.execute(new MyRun());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }
    class MyRun implements Runnable {
        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun");
            try {
                TimeUnit.SECONDS.sleep(5);
                sendIntent();
                showNityficashion();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sendIntent() {
            Intent intent = new Intent(MyService.this, MainActivity.class);
            intent.putExtra(MainActivity.FILE_NAME, "someFile ");
            PendingIntent pIntent = PendingIntent.getActivity(
                    MyService.this,
                    0,
                    intent ,
                    PendingIntent.FLAG_IMMUTABLE);
            try {
                Log.d(LOG_TAG, "pIntent.send()");
                pIntent.send();
            } catch (PendingIntent.CanceledException e) {
                throw new RuntimeException(e);
            }

        }

        private void showNityficashion() {
            NotificationChannel channel = new NotificationChannel("NOTIFICATION_ID", "notification",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(channel);
            final String strChannel = "channel";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), strChannel);
            builder
                    .setContentTitle("Title")
                    .setContentText("This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. This is content text. ")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_12);

            builder.setChannelId("NOTIFICATION_ID");

            notificationManager.notify(1, builder.build());

        }
    }
}

