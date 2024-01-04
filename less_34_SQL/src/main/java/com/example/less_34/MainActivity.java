package com.example.less_34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTName, eTMail, etId;
    Button butAdd, butRead, butClear, butUpd, butDel;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTName = findViewById(R.id.e_t_name);
        eTMail = findViewById(R.id.e_t_mail);
        etId = findViewById(R.id.e_t_id);

        butAdd = findViewById(R.id.but_add);
        butAdd.setOnClickListener(this);
        butRead = findViewById(R.id.but_read);
        butRead.setOnClickListener(this);
        butClear = findViewById(R.id.but_clear);
        butClear.setOnClickListener(this);
        butUpd = findViewById(R.id.but_upd);
        butUpd.setOnClickListener(this);
        butDel = findViewById(R.id.but_del);
        butDel.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String name = eTName.getText().toString();
        String mail = eTMail.getText().toString();
        String id = etId.getText().toString();

        SQLiteDatabase sQLDB = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        if (v.equals(butAdd)) {
            contentValues.put(DBHelper.KEY_NAME, name);
            contentValues.put(DBHelper.KEY_MAIL, mail);
            sQLDB.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        }

        else if (v.equals(butRead)) {
            Cursor cursor = sQLDB.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                int mailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                do {
                    Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                            ", name = " + cursor.getString(nameIndex) +
                            ", mail = " + cursor.getString(mailIndex));
                } while (cursor.moveToNext());
            } else Log.d("mLog", "0 rows");
            cursor.close();
        }

        else if (v.equals(butClear)) {
            sQLDB.delete(DBHelper.TABLE_CONTACTS, null, null);
            dbHelper.close();
        }

        else if (v.equals(butUpd)) {
            if (!id.equalsIgnoreCase("")) {
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, mail);
                int updCount = sQLDB.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_NAME + "= ?" , new String[]{name});
                Log.d("mLog", "updates log count = " + updCount);
            }
        }

        else if (v.equals(butDel)) {
            if (!id.equalsIgnoreCase("")) {
                int delCount = sQLDB.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_NAME + "= ?" , new String[]{name});
                Log.d("mLog", "deleted rows count = " + delCount);
            }
        }
    }
}