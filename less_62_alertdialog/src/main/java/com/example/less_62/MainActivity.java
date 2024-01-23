package com.example.less_62;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    int cnt = 0;
    DB db;
    Cursor cursor;
    String[] data = {"one", "two", "three", "four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);

        findViewById(R.id.but_items).setOnClickListener(vOKL);
        findViewById(R.id.but_adapter).setOnClickListener(vOKL);
        findViewById(R.id.but_cursor).setOnClickListener(vOKL);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        if (id == DIALOG_ITEMS) {
            adb.setTitle(R.string.items);
            adb.setItems(data, myClickListener);
        } else if (id == DIALOG_ADAPTER) {
            adb.setTitle(R.string.adapter);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.select_dialog_item, data);
            adb.setAdapter(adapter, myClickListener);
        } else if (id == DIALOG_CURSOR) {
            adb.setTitle(R.string.cursor);
            adb.setCursor(cursor, myClickListener, DB.COLUMN_TXT);
        }
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        AlertDialog aDialog = (AlertDialog) dialog;
        ListAdapter lAdapter = aDialog.getListView().getAdapter();
         switch (id) {
            case DIALOG_ITEMS:
            case DIALOG_ADAPTER: {
                if (lAdapter instanceof BaseAdapter) {
                    BaseAdapter bAdapter = (BaseAdapter) lAdapter;
                    bAdapter.notifyDataSetChanged();
                }
                break;
            }
            default:
                break;
        }
    }

    View.OnClickListener vOKL = v -> {
        changeCount();
        int id = v.getId();
        if (id == R.id.but_items) showDialog(DIALOG_ITEMS);
        else if (id == R.id.but_adapter) showDialog(DIALOG_ADAPTER);
        else if (id == R.id.but_cursor) showDialog(DIALOG_CURSOR);
    };

    DialogInterface.OnClickListener myClickListener =
            (dialog, which) -> Log.d(LOG_TAG, "which = " + which);

    void changeCount() {
        cnt++;
        data[3] = String.valueOf(cnt);
        db.changeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();

    }
}