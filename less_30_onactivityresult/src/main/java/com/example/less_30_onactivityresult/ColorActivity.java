package com.example.less_30_onactivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {
    Button red;
    Button  green;
    Button  blue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_activity);
        red = findViewById(R.id.but_r);
        green = findViewById(R.id.but_g);
        blue = findViewById(R.id.but_b);
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.equals(red)) {
            intent.putExtra("color", Color.RED);
        } else if (view.equals(green)) {
            intent.putExtra("color", Color.GREEN);
        } else if (view.equals(blue)) {
            intent.putExtra("color", Color.BLUE);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
