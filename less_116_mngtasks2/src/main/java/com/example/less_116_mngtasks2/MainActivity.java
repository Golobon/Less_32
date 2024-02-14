package com.example.less_116_mngtasks2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    List<ActivityManager.RunningTaskInfo> list;
    ActivityManager am;
    Button butStart;
    Button butInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.app_name) + " : " + getLocalClassName());
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        butStart = findViewById(R.id.but_start);
        butStart.setOnClickListener(this);
        butInfo = findViewById(R.id.but_info);
        butInfo.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (butStart.equals(v)) {
            startActivity(new Intent("mngtasks1_activity_c"));

//                    addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME).
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (butInfo.equals(v)) {
            list = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo task : list) {
                if (task.baseActivity.flattenToShortString().startsWith("com.example.less_116")) {
                    Log.d(LOG_TAG, "------------------");
                    Log.d(LOG_TAG, "Count: " + task.numActivities);
                    Log.d(LOG_TAG, "Root: " + task.baseActivity.flattenToShortString());
                    Log.d(LOG_TAG, "Top: " + task.topActivity.flattenToShortString());
                }
            }
        }
    }
}