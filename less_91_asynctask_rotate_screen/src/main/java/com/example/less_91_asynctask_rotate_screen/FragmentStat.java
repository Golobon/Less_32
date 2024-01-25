package com.example.less_91_asynctask_rotate_screen;

import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class FragmentStat extends Fragment {
    final String LOG_TAG = "myLogs";
    TextView tv;
    MyTask mt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(LOG_TAG, "create FragmentStat: " + this.hashCode());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        tv = view.findViewById(R.id.tv_stat);
        mt = new MyTask();
        view.findViewById(R.id.btn_start).setOnClickListener(v -> {
            if (mt.getStatus() != AsyncTask.Status.RUNNING) {
                    if (mt.getStatus() == AsyncTask.Status.FINISHED) mt = new MyTask();
                    mt.execute();
            }
            else Log.d(LOG_TAG, "MyTask is running. Wait " + mt.getStatus());
        });
        return view;
    }

    class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    Log.d(LOG_TAG, "i = " + i
                            + ", doInBackground MyTask: " + this.hashCode()
                            + ", MainActivity: " + getContext().hashCode()
                    );
                    publishProgress(i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText("i = " + values[0]);
        }
    }
}