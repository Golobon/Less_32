package com.example.less_47;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] texts = { "someText1", "someText2", "someText3",
                "someText4", "someText5" };
        boolean[] checked = { true, false, false, true, false };
        int img = R.drawable.ic_launcher_foreground;

        ArrayList<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> m;

        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED,
                ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT };
        int[] to = { R.id.tv_text, R.id.cb_cheched, R.id.iv_img, R.id.cb_cheched };

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item,
                from, to);
        lvSimple = findViewById(R.id.lv_simple);
        lvSimple.setAdapter(sAdapter);
    }
}