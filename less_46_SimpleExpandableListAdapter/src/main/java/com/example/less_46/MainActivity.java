package com.example.less_46;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);

        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = findViewById(R.id.elv);
        elvMain.setAdapter(adapter);

        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent,
                                        View v,
                                        int groupPosition,
                                        int childPosition,
                                        long id) {
                Log.d(LOG_TAG,
                        "onChildClick groupPosition = " + groupPosition +
                                " childPosition = " + childPosition +
                                " id = " + id);

                tvInfo.setText(ah.getGroupChildText(groupPosition,
                        childPosition));
                return false;
            }
        });

        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(
                    ExpandableListView parent,
                    View v,
                    int groupPosition,
                    long id) {
                Log.d(LOG_TAG, "onGroupClick groupPosition = " +
                        groupPosition + " id = " + id);

                if (groupPosition == 1) return true;

                return false;
            }
        });
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG, "onGroupExpand groupPosition = " +
                        groupPosition);
                tvInfo.setText("Свернули = " +
                        ah.getGroupText(groupPosition));
            }
        });
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG, "onGroupExpand groupPosition = " +
                        groupPosition);
                tvInfo.setText("Развернули = " +
                        ah.getGroupText(groupPosition));
            }
        });
        elvMain.expandGroup(2);
    }
}