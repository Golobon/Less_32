package com.example.less_66;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
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
    final int DIALOG = 1;
    Button butDialog;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butDialog = findViewById(R.id.but_dialog);
        butDialog.setOnClickListener(onclick);
    }
    View.OnClickListener onclick = v -> {
        showDialog(DIALOG);
        Handler h = new Handler();

        h.postDelayed(this::method1, 2000);
        h.postDelayed(this::method2, 4000);
    };
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("Ok", null);
            dialog = adb.create();

            dialog.setOnShowListener(dialog -> Log.d(LOG_TAG, "Show"));
            dialog.setOnCancelListener(dialog -> Log.d(LOG_TAG, "Cancel"));
            dialog.setOnDismissListener(dialog -> Log.d(LOG_TAG, "Dismiss"));
            return dialog;
        }
        return super.onCreateDialog(id);
    }
    void method1() {
        dialog.cancel();
        dialog.dismiss();
        dismissDialog(DIALOG);
        removeDialog(DIALOG);
        dialog.hide();
    }
    void method2() {
        dialog.show();
        showDialog(DIALOG);
    }

}