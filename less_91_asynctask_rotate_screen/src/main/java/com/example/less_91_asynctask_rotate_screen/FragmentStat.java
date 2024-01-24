package com.example.less_91_asynctask_rotate_screen;

import android.util.Log;
import android.widget.Button;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.concurrent.TimeUnit;

public class ProgressFragment extends Fragment {
    final String LOG_TAG = "myLogs";
    int[] integers=null;
    ProgressBar indicatorBar;
    TextView statusView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        integers = new int[100];
        for(int i=0;i<100;i++) {
            integers[i] = i + 1;
        }
        indicatorBar = (ProgressBar) view.findViewById(R.id.indicator);
        statusView = (TextView) view.findViewById(R.id.statusView);
        Button btnFetch = (Button)view.findViewById(R.id.progressBtn);
        btnFetch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new ProgressTask().execute();
            }
        });
        return view;
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