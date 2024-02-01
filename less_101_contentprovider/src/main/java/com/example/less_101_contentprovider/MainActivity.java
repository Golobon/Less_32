package com.example.less_101_contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final Uri CONTACT_URI = Uri.
            parse("content://com.golobon.providers.AdressBook/contacts");
    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(CONTACT_URI, null,
                null, null, null);
        startManagingCursor(cursor);

        String from[] = { "name", "email" };
        int to[] = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, from, to);

        ListView lvContact = findViewById(R.id.lv_contact);
        lvContact.setAdapter(adapter);

        findViewById(R.id.btn_insert).setOnClickListener(v -> {
            ContentValues cv = new ContentValues();
            cv.put(CONTACT_NAME, "Name 4");
            cv.put(CONTACT_EMAIL, "email 4");
            Uri newUri = getContentResolver().insert(CONTACT_URI, cv);
            Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
        });

        findViewById(R.id.btn_update).setOnClickListener(v -> {
            ContentValues cv = new ContentValues();
            cv.put(CONTACT_NAME, "Name 5");
            cv.put(CONTACT_EMAIL, "email 5");
            Uri uri = ContentUris.withAppendedId(CONTACT_URI, 2);
            int cnt = getContentResolver().update(uri, cv, null, null);
            Log.d(LOG_TAG, "update, count : " + cnt);
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            Uri uri = ContentUris.withAppendedId(CONTACT_URI, 3);
            int cnt = getContentResolver().delete(uri, null, null);
            Log.d(LOG_TAG, "delete,count : " + cnt);
        });

        findViewById(R.id.btn_error).setOnClickListener(v -> {
            Uri uri = Uri.
                    parse("content://com.golobon.providers.AdressBook/phones");
            try {
                Cursor cursor1 = getContentResolver().query(uri, null,
                        null, null, null);
            } catch (Exception e) {
                Log.d(LOG_TAG, "Error : " + e.getClass() + ", " +
                        e.getMessage());
            }

        });
    }
}