package com.example.less_42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь", "Борис", "Костя", "Игорь", "Борис", "Костя", "Игорь", "Игорь",
            "Костя", "Игорь", "Борис", "Костя", "Игорь", "Борис", "Костя", "Игорь", "Игорь" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMain = findViewById(R.id.lv_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_list_item, names);

        lvMain.setAdapter(adapter);
    }
}