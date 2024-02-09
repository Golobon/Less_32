package com.example.less_110_fragments_preferencefragment_headers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().
                replace(android.R.id.content, new Fragment1()).commit();
    }
}