package com.example.less_54;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter boxAdapter;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.but);
        b.setOnClickListener(v -> showResult());

        fillData();
        boxAdapter = new BoxAdapter(this, products);

        ListView lvMain = findViewById(R.id.lv_main);
        lvMain.setAdapter(boxAdapter);
    }

    void fillData() {
        for (int i = 0; i <= 20; i++) {
            products.add(new Product("Product " + i,
                    i * 1000,
                    R.drawable.ic_launcher_foreground,
                    false));
        }
    }
    void showResult() {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()){
            if ((p.box)) result += "\n" + p.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}