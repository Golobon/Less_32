package com.example.less_89_asynctask_cancel;

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

        tvInfo = findViewById(R.id.tv_info);
        findViewById(R.id.btn_start).setOnClickListener(v -> {
            mt = new MyTask();
            mt.execute();
        });

        findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            cancelTask();
        });
    }

    private void cancelTask() {
        if (mt == null) return;
        Log.d(LOG_TAG, "cancel result: " + mt.cancel(false));
    }

    public class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (isCancelled()) return null;
                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }

            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.d(LOG_TAG, "End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.d(LOG_TAG, "Cancel");
        }

    }
}