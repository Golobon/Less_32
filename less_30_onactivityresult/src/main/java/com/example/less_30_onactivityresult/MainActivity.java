package com.example.less_30_onactivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tV;
    Button butColor;
    Button butGraity;
    public static final int REC_COLOR_CODE = 11;
    public static final int REC_GRAVITY_CODE = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV = findViewById(R.id.h_w);
        butColor = findViewById(R.id.but_color);
        butGraity = findViewById(R.id.but_gravity);
        butColor.setOnClickListener(this);
        butGraity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.equals(butColor)) {
            intent = new Intent(this, ColorActivity.class);
            startActivityForResult(intent, REC_COLOR_CODE);
        } else if (view.equals(butGraity)) {
            intent = new Intent(this, GravityActivity.class);
            startActivityForResult(intent, REC_GRAVITY_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REC_COLOR_CODE) {
                tV.setTextColor(data.getIntExtra("color", Color.BLACK));
            } else if (requestCode == REC_GRAVITY_CODE) {
                tV.setGravity(data.getIntExtra("gravity", Gravity.CENTER));
            }
        } else Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
    }
}