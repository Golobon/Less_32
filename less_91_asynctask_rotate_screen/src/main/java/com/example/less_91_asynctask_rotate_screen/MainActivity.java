package com.example.less_91_asynctask_rotate_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    MyTask mt;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "create MainActivity: " + this.hashCode());

        tvInfo = findViewById(R.id.tv);

        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        Log.d(LOG_TAG, "create MyTask: " + mt.hashCode());
    }


    @Override
    public Object onRetainNonConfigurationInstance() {
        return mt;
    }

    public class MyTask extends AsyncTask<String, Integer, Void> {
        @Override
        protected Void doInBackground(String... str) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress();
                    Log.d(LOG_TAG, "i = " + i +
                            ", MyTask: " + this.hashCode() +
                            ", MainActivity: " + MainActivity.this.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... result) {
            super.onProgressUpdate(result);
            tvInfo.setText("i = " + result);
        }
    }
}