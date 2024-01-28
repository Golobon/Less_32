package service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.less_99_2.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService es;
    Context context;
    NotificationCompat.Builder builder;
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
                builder = new NotificationCompat.Builder(context, "Boober")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Title")
                                .setContentText("Notification text");

                Notification notification = builder.build();

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

