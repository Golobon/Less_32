package com.example.less_95_service_pendingintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
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
    public final static String PARAM_INTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";
    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;

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

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pi;
                //Intent intent;

                pi = createPendingResult(TASK1_CODE, new Intent(), 0);

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 7).
                                putExtra(PARAM_INTENT, pi));

                pi = createPendingResult(TASK2_CODE, new Intent(), 0);

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 4).
                                putExtra(PARAM_INTENT, pi));

                pi = createPendingResult(TASK3_CODE, new Intent(), 0);

                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra(PARAM_TIME, 6).
                                putExtra(PARAM_INTENT, pi));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "requestCode = " + requestCode +
                ", resultCode = " + resultCode);

        if (resultCode == STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE :
                    tvTask1.setText("Task1 start");
                break;
                case TASK2_CODE :
                    tvTask2.setText("Task2 start");
                break;
                case TASK3_CODE :
                    tvTask3.setText("Task3 start");
                break;
            }
        }

        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT,0);
            switch (requestCode) {
                case TASK1_CODE :
                    tvTask1.setText(
                            "Task1 finish, result = " + result);
                    break;
                case TASK2_CODE :
                    tvTask2.setText(
                            "Task2 finish, result = " + result);
                    break;
                case TASK3_CODE :
                    tvTask3.setText(
                            "Task3 finish, result = " + result);
                    break;
            }
        }
    }
}