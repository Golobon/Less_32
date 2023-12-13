package com.example.less_38;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DB_NAME = "myDB";
    private static final String TABLE_NAME = "MyTable";
    private SQLiteDatabase sqLiteDatabase;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    private void initDB() {
        sqLiteDatabase = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(FirstNumber INT, SecondNumber INT, RESULT INT);");
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }
    @Override
    public void onClick(View view) {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        long startTime = System.currentTimeMillis();
        insertRecords();
        long diff = System.currentTimeMillis() - startTime;
        textView.setText("Time:" + Long.toString(diff) + " ms");
    }
    private void insertRecords() {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?);";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteDatabase.beginTransaction();
        try {
            for (int i = 0; i < 1000; i++) {
                sqLiteStatement.clearBindings();
                sqLiteStatement.bindLong(1, i);
                sqLiteStatement.bindLong(2, i);
                sqLiteStatement.bindLong(3, i * i);
                sqLiteStatement.execute();
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}