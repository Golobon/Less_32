package com.example.less_43;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lvMain;
    String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lv_main);
        //Устанавливаем режим выбора списка
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //Создаем массив, используя массив из файла ресурсов
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_single_choice);
        lvMain.setAdapter(adapter);

        Button button = findViewById(R.id.but);
        button.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View v) {
        Log.d("MyLogs", "checked: " + names[lvMain.getCheckedItemPosition()]);
    }
}