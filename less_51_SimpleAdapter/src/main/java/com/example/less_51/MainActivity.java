package com.example.less_51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int CM_DELETE_ID = 1;
    private static final int CM_CHANGE_ID = 2;
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    ListView lvSimple;
    ImageView iv;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = findViewById(R.id.but);

        but.setOnClickListener(v -> {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
            m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
            data.add(m);
            sAdapter.notifyDataSetChanged();
        });

        data = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
            m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
            data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};
        int[] to = {R.id.tv_text, R.id.iv_img};

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = findViewById(R.id.lv_simple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
        menu.add(0, CM_CHANGE_ID, 0, "Изменить запись");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterView.AdapterContextMenuInfo acmi =
                    (AdapterView.AdapterContextMenuInfo)
                            item.getMenuInfo();
            data.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == CM_CHANGE_ID) {
                AdapterView.AdapterContextMenuInfo acmi =
                        (AdapterView.AdapterContextMenuInfo)
                                item.getMenuInfo();
                data.get(acmi.position).remove(ATTRIBUTE_NAME_TEXT);
                data.get(acmi.position).remove(ATTRIBUTE_NAME_IMAGE);
                data.get(acmi.position).put(ATTRIBUTE_NAME_TEXT, "Новь");
                data.get(acmi.position).put(ATTRIBUTE_NAME_IMAGE,
                        R.drawable.ic_launcher_background);
                sAdapter.notifyDataSetChanged();
                return true;
            }
        return super.onContextItemSelected(item);
    }
}