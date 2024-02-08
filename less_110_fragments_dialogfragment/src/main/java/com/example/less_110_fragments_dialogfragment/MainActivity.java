package com.example.less_110_fragments_dialogfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DialogFragment df1;
    DialogFragment df2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        df1 = new Dialog1();
        df2 = new Dialog2();

        findViewById(R.id.but_dial_1).setOnClickListener(v ->
                df1.show(getSupportFragmentManager(), "df1"));
        findViewById(R.id.but_dial_2).setOnClickListener(v ->
                df2.show(getSupportFragmentManager(), "df2"));
    }
}