package com.example.less_88_asynctask_get;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

        findViewById(R.id.btn_get).setOnClickListener(v -> {
            showResult();
        });
    }

    private void showResult() {
        if (mt == null) return;
        int result;

        try {
            Log.d(LOG_TAG, "Try to get result");
            result = mt.get();
            Log.d(LOG_TAG, "get returns " + result);
            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();

        } catch (Exception ignore) {
        }
    }

    public class MyTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception ignore) { }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tvInfo.setText("End. Result = " + result);
            Log.d(LOG_TAG, "End. Result = " + result);
        }
    }
}