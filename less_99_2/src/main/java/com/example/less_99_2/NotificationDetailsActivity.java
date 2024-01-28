package com.example.less_99_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NotificationDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti);
        Intent get = getIntent();
        String data = get.getStringExtra("msg");
        Toast.makeText(getBaseContext(), "Message: "+ data, Toast.LENGTH_LONG).show();
    }
}
