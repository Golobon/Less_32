package com.example.less_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eT;
    SharedPreferences sP;
    final String SAVED_TEXT= "Saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eT = findViewById(R.id.editText);
        findViewById(R.id.but_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });
        findViewById(R.id.but_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadText();
            }
        });
        loadText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void saveText() {
        sP = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString(SAVED_TEXT, eT.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }

    private void loadText() {
        sP = getSharedPreferences("MyPref", MODE_PRIVATE);
        String loadRes = sP.getString(SAVED_TEXT, "");
        eT.setText(loadRes);
        Toast.makeText(this, "Loaded", Toast.LENGTH_LONG).show();
    }
}