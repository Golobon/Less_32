package service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyService extends IntentService {
    final String LOG_TAG = "myLogs";

    public MyService() {
        super("myname");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("label");
        Log.d(LOG_TAG, "omHandleIntent start " + label);
        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "omHandleIntent end " + label);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
