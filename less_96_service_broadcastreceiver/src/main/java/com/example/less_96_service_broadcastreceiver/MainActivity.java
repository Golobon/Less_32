package com.example.less_96_service_broadcastreceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;
    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;
    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";
    public final static String BROADCAST_ACTION = "com.example.less_96_service_broadcastreceiver";
    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = findViewById(R.id.tv_task1);
        tvTask1.setText("Task1");
        tvTask2 = findViewById(R.id.tv_task2);
        tvTask2.setText("Task1");
        tvTask3 = findViewById(R.id.tv_task3);
        tvTask3.setText("Task1");

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(LOG_TAG, "onRecieve: task = " + task +
                        ", status = " + status);

                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task1 start");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task2 start");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task3 start");
                            break;
                    }
                }

                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText(
                                    "Task1 finish, result = " + result);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText(
                                    "Task2 finish, result = " + result);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText(
                                    "Task3 finish, result = " + result);
                            break;
                    }
                }
            }
        };

        IntentFilter intFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intFilter);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent;

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 7).
                                putExtra(PARAM_TASK, TASK1_CODE));

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 4).
                                putExtra(PARAM_TASK, TASK2_CODE));

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 6).
                                putExtra(PARAM_TASK, TASK3_CODE));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}