package com.example.less_64;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;
    String[] data = {"one", "two", "three", "four"};
    boolean[] chkd = {false, true, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);

        findViewById(R.id.but_items).setOnClickListener(vOKL);
        findViewById(R.id.but_cursor).setOnClickListener(vOKL);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        if (id == DIALOG_ITEMS) {
            adb.setTitle(R.string.items);
            adb.setMultiChoiceItems(data, chkd, myItemsMultiClickListener);
        } else if (id == DIALOG_CURSOR) {
            adb.setTitle(R.string.cursor);
            adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK,
                    DB.COLUMN_TXT, myCursorMultiClickListener);
        }
        adb.setPositiveButton(R.string.ok, myBtnClickListener);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        AlertDialog aDialog = (AlertDialog) dialog;
        ListAdapter lAdapter = aDialog.getListView().getAdapter();
        switch (id) {
            case DIALOG_ITEMS: {
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
        int id = v.getId();
        if (id == R.id.but_items) showDialog(DIALOG_ITEMS);
        else if (id == R.id.but_cursor) showDialog(DIALOG_CURSOR);
    };

    DialogInterface.OnMultiChoiceClickListener myItemsMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.d(LOG_TAG, "which = " + which + "isChecked = " + isChecked);
        }
    };

    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.d(LOG_TAG, "which = " + which +
                    "isChecked = " + isChecked);
            db.changeRec(which, isChecked);
            cursor.requery();
        }
    };

    DialogInterface.OnClickListener myBtnClickListener =
            (dialog, which) -> {
                SparseBooleanArray sbArray = ((AlertDialog) dialog).getListView().
                        getCheckedItemPositions();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if (sbArray.get(key)) Log.d(LOG_TAG, "chesked: " + key);
                }
            };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}