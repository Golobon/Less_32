package com.example.less_30_onactivityresult;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class GravityActivity extends AppCompatActivity implements View.OnClickListener {
    Button  left;
    Button  center;
    Button  right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravity_activity);
        left = findViewById(R.id.but_l);
        center = findViewById(R.id.but_c);
        right = findViewById(R.id.but_r);
        left.setOnClickListener(this);
        center.setOnClickListener(this);
        right.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.equals(left)) {
            intent.putExtra("gravity", Gravity.LEFT);
        } else if (view.equals(center)) {
            intent.putExtra("gravity", Gravity.CENTER);
        } else if (view.equals(right)) {
            intent.putExtra("gravity", Gravity.RIGHT);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
