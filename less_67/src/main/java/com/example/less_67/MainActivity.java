package com.example.less_67;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    Handler h;
    ProgressDialog pd;
    Button butDefault;
    Button butProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butDefault = findViewById(R.id.but_dflt);
        butDefault.setOnClickListener(onclick);
        butProgress = findViewById(R.id.but_progress);
        butProgress.setOnClickListener(onclick);
    }

    View.OnClickListener onclick = v -> {
        int id = v.getId();

        if (id == butDefault.getId()) {
            pd = new ProgressDialog(this);
            pd.setTitle("Title");
            pd.setMessage("Message");
            pd.setButton(Dialog.BUTTON_POSITIVE, "Ok", (dialog, which) -> {
            });
            pd.show();
        } else if (id == butProgress.getId()) {
            pd = new ProgressDialog(this);
            pd.setTitle("Title");
            pd.setMessage("Message");
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setMax(2148);
            pd.setIndeterminate(true);
            pd.show();

            h = new Handler() {
                public void handleMessage(Message msg) {
                    pd.setIndeterminate(false);
                    if (pd.getProgress() < pd.getMax()) {
                        pd.incrementProgressBy(50);
                        pd.incrementSecondaryProgressBy(75);
                        h.sendEmptyMessageDelayed(0, 100);
                    } else pd.dismiss();
                }
            };
            h.sendEmptyMessageDelayed(0, 2000);
        }
    };
}