package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand, name = " +
                intent.getStringExtra("name"));
        readFlags(flags);
        MyRun mr = new MyRun(startId);
        new Thread(mr).start();
        return START_REDELIVER_INTENT;
    }
    private void readFlags(int flags) {
        Log.d(LOG_TAG, "MyService readFlags");
        if ((flags & START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) {
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY");
        }
        if ((flags & START_FLAG_RETRY) == START_FLAG_RETRY) {
            Log.d(LOG_TAG, "START_FLAG_RETRY");
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }
    class MyRun implements Runnable {
        int startId;
        public MyRun(int startId) {
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun " + startId + " create");
        }
        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun " + startId + " start");
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(10);
                    Log.d(LOG_TAG, "BOOOOO");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }
        private void stop() {
            Log.d(LOG_TAG, "MyRun " + startId +
                    " end, stopSelfResult(" + startId + ") = " +
                    stopSelfResult(startId));
        }
    }
}
