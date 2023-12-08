package com.example.less_36;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    String[] names = {"Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада"};
    Integer[] people = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35};
    String[] region = {"Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка"};
    Button butAllData, butFunc, butDemosMore, butDemosRegion, butDemosRegionMore, butSort;
    EditText eTFunc, eTDemosMore, eTDemosRegionMore;
    RadioGroup rGroupSort;
    RadioButton radioName, radioDemos, radioRegion;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butAllData = findViewById(R.id.but_all_data);
        butAllData.setOnClickListener(this);
        butFunc = findViewById(R.id.but_func);
        butFunc.setOnClickListener(this);
        butDemosMore = findViewById(R.id.but_demos_more);
        butDemosMore.setOnClickListener(this);
        butDemosRegion = findViewById(R.id.but_demos_reg);
        butDemosRegion.setOnClickListener(this);
        butDemosRegionMore = findViewById(R.id.but_demos_reg_more);
        butDemosRegionMore.setOnClickListener(this);
        butSort = findViewById(R.id.but_sort);
        butSort.setOnClickListener(this);

        eTFunc = findViewById(R.id.et_func);
        eTDemosMore = findViewById(R.id.et_demos_more);
        eTDemosRegionMore = findViewById(R.id.et_demos_reg_more);

        rGroupSort = findViewById(R.id.radio_group_sort);

        radioName = findViewById(R.id.radio_name);
        radioDemos = findViewById(R.id.radio_demos);
        radioRegion = findViewById(R.id.radio_region);

        dbHelper = new DBHelper(this);

        db = dbHelper.getWritableDatabase();

        Cursor c = db.query("myTable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            for (int i = 0; i < 10; i++) {
                cv.put("name", names[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("myTable", null, cv));
            }
            c.close();
            dbHelper.close();
            onClick(butAllData);
        }
    }

    @Override
    public void onClick(View v) {
        db = dbHelper.getWritableDatabase();

        String sFunc = eTFunc.getText().toString();
        String sPeople = eTDemosMore.getText().toString();
        String sRegionPeople = eTDemosRegionMore.getText().toString();

        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor c = null;

        if (v.equals(butAllData)) {
            Log.d(LOG_TAG, "--- Все записи ---");
            c = db.query("myTable", null, null, null, null, null, null);
        } else if (v.equals(butFunc)) {
            Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
            columns = new String[]{ sFunc };
            c = db.query("myTable", columns, null, null, null, null, null);
        } else if (v.equals(butDemosMore)) {
            Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");
            selection = "CAST(people AS INTEGER) > ?";
            selectionArgs = new String[]{ sPeople };
            c = db.query("myTable", null, selection, selectionArgs, null, null, null);
        } else if (v.equals(butDemosRegion)) {
            Log.d(LOG_TAG, "--- Население по региону ---");
            columns = new String[]{"region", "sum(people) as people"};
            groupBy = "region";
            c = db.query("myTable", columns, null, null, groupBy, null, null);
        } else if (v.equals(butDemosRegionMore)) {
            Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople + " ---");
            columns = new String[]{"region", "sum(people) as people"};
            groupBy = "region";
            having = "sum(people) > " + sRegionPeople;
            c = db.query("myTable", columns, null, null, groupBy, having, null);
        } else if (v.equals(butSort)) {
            if (rGroupSort.getCheckedRadioButtonId() == radioName.getId()) {
                Log.d(LOG_TAG, "--- Сортировка по имени ---");
                orderBy = "name";
            } else if (rGroupSort.getCheckedRadioButtonId() == radioDemos.getId()) {
                Log.d(LOG_TAG, "--- Сортировка по населению ---");
                orderBy = "CAST(people AS INTEGER) DESC";
            } else if (rGroupSort.getCheckedRadioButtonId() == radioRegion.getId()) {
                Log.d(LOG_TAG, "--- Сортировка по региону ---");
                orderBy = "region";
            }
            c = db.query("myTable", null, null, null, null, null, orderBy);
        }
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (c.moveToNext());
            }
            c.close();
        } else Log.d(LOG_TAG, "Cursor is null");
        dbHelper.close();
    }

    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table myTable (" +
                    " id integer primary key autoincrement," +
                    "name text," +
                    "people INTEGER," +
                    "region text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + "myTable");
            onCreate(db);
        }
    }
}