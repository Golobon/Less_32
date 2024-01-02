package com.example.less_57;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        adapter = new ArrayAdapter<>(this, R.layout.item, 
                R.id.tv_tv_text, data);
        gvMain = findViewById(R.id.gv_main);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView() {
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(300);
        gvMain.setHorizontalSpacing(5);
        gvMain.setVerticalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}