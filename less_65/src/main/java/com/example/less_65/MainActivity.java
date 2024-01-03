package com.example.less_65;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final int DIALOG = 1;
    int btn;
    LinearLayout view;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViews = new ArrayList<>(10);

        findViewById(R.id.but_add).setOnClickListener(onclick);
        findViewById(R.id.but_remove).setOnClickListener(onclick);
    }

    View.OnClickListener onclick = v -> {
        btn = v.getId();
        showDialog(DIALOG);
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");
        view = (LinearLayout) getLayoutInflater().
                inflate(R.layout.dialog, null);
        adb.setView(view);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {
            TextView tvTime = (TextView) dialog.getWindow().
                    findViewById(R.id.tv_time);
            tvTime.setText(sdf.format(new Date()));
            if (btn == R.id.but_add) {
                TextView tv = new TextView(this);
                view.addView(tv, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tv.setText("TextView " + (textViews.size() + 1));
                textViews.add(tv);
            } else {
                if (textViews.size() > 0) {
                    TextView tv = textViews.get(textViews.size() - 1);
                    view.removeView(tv);
                    textViews.remove(tv);
                }
            }
            tvCount.setText("Кол-во TextView = " + textViews.size());
        }
    }
}