package com.example.less_50;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";
    ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] load = {41, 48, 22, 35, 30, 67, 67, 51, 88};

        ArrayList<Map<String, Object>> data = new ArrayList<>(load.length);
        Map<String, Object> m;

        for (int i = 0; i < load.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) +
                    ". load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL};
        int[] to = {R.id.tv_load, R.id.pb_load, R.id.ll_load};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item,
                from, to);

        sAdapter.setViewBinder(new MyViewBinder());

        lvSimple = findViewById(R.id.lv_simple);
        lvSimple.setAdapter(sAdapter);

    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {
        int red = getResources().getColor(R.color.red);
        int orange = getResources().getColor(R.color.orange);
        int green = getResources().getColor(R.color.green);
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i;
            if (view.getId() == R.id.ll_load) {
                i = ((Integer) data).intValue();
                if (i < 40) view.setBackgroundColor(green);
                else if (i < 70) view.setBackgroundColor(orange);
                else view.setBackgroundColor(red);
                return true;
            } else if (view.getId() == R.id.pb_load) {
                i = ((Integer) data).intValue();
                ((ProgressBar) view).setProgress(i);
                return true;
            }
            return false;
        }
    }
}