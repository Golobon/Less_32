package com.example.less_94_service_onstartcommand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import service.MyService;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startService(
                        new Intent(MainActivity.this, MyService.class).
                                putExtra("name", "value"));
//                Intent intent = new Intent("service.MyService");
//                intent.setPackage("service");
//                intent.putExtra("name", "value");
//                startService(intent);
            }
        });
    }
}