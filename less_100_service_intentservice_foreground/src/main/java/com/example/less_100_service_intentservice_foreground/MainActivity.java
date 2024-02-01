package com.example.less_100_service_intentservice_foreground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import service.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            startService((new Intent(
                    MainActivity.this, MyService.class))
                    .putExtra("time", 3)
                    .putExtra("label", "call 1") );

            startService((new Intent(
                    MainActivity.this, MyService.class))
                    .putExtra("time", 2)
                    .putExtra("label", "call 2") );

            startService((new Intent(
                    MainActivity.this, MyService.class))
                    .putExtra("time", 4)
                    .putExtra("label", "call 3") );
        });
    }
}