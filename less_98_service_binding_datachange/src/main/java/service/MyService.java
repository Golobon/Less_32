package service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";
    MyBinder binder = new MyBinder();
    Timer timer;
    TimerTask tTask;
    long interval = 1000;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        timer = new Timer();
        shedule();
    }
    private void shedule() {
        if (tTask != null) tTask.cancel();
        if (interval > 0) {
            tTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "run");
                }
            };
            timer.schedule(tTask, 1000, interval);
        }
    }
    public long upInterval (long gap) {
        interval = interval + gap;
        shedule();
        return interval;
    }
    public long downInterval (long gap) {
        interval = interval - gap;
        if (interval < 0) interval = 0;
        shedule();
        return interval;
    }
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        return binder;
    }
    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
