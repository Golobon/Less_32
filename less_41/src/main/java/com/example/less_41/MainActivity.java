package com.example.less_41;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] name = {"Иван", "Марья", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь", "Борис", "Костя", "Игорь", "Борис", "Костя", "Игорь", "Игорь"};
    String[] position = {"Программер", "Бухгалтер", "Программер",
            "Программер", "Бухгалтер", "Директор", "Программер", "Охранник", "Программер", "Бухгалтер", "Директор", "Программер", "Охранник",
            "Программер", "Бухгалтер"};
    int[] salary = {13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000, 10000, 13000, 13000, 10000, 15000, 13000, 8000};
    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linLayout = findViewById(R.id.lin_layout);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++) {
            Log.d("MyLog", "i = " + i);
            View item = inflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = item.findViewById(R.id.tv_name);
            tvName.setText(name[i]);
            TextView tvPosition = item.findViewById(R.id.tv_position);
            tvPosition.setText("Должность: " + position[i]);
            TextView tvSalary = item.findViewById(R.id.tv_salary);
            tvSalary.setText("Оклад: " + salary[i]);
            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);
        }
    }
}