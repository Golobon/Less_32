package com.example.less_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ExpandableListView alvMain;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        Cursor cursor = db.getCompanyData();
        startManagingCursor(cursor);

        String[] groupFrom = new String[]{DB.COMPANY_COLUMN_NAME};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{DB.PHONE_COLUMN_NAME};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleCursorTreeAdapter sctAdapter = new MyAdapter(
                this, cursor,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, android.R.layout.simple_list_item_1,
                childFrom, childTo);

        alvMain = findViewById(R.id.elv_Main);
        alvMain.setAdapter(sctAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
    class MyAdapter extends SimpleCursorTreeAdapter {
        public MyAdapter(Context context, Cursor cursor,
                         int groupLayout, String[] groupFrom, int[] groupTo,
                         int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor,
                    groupLayout, groupFrom, groupTo,
                    childLayout, childFrom, childTo);
        }
        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            int idColumn = groupCursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            return db.getPhoneData(groupCursor.getInt(idColumn));
        }
    }
}