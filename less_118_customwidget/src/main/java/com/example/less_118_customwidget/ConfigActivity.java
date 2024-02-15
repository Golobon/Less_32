package com.example.less_118_customwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener{
    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;
    final String LOG_TAG = "myLogs";
    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text";
    public final static String WIDGET_COLOR = "widget_color";
    Button btnOK;
    RadioGroup rg;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate config");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        setResult(RESULT_CANCELED, resultValue);

        setContentView(R.layout.config);

        btnOK = findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(this);

        rg = findViewById(R.id.rg_color);
        rg.setOnClickListener(this);

        et = findViewById(R.id.et_text);
    }


    @Override
    public void onClick(View v) {
        int selRGColorId = rg.getCheckedRadioButtonId();
        int color = Color.RED;
        if (selRGColorId == R.id.radio_red)
            color = Color.parseColor("#80ff0000");
        if (selRGColorId == R.id.radio_green)
            color = Color.parseColor("#8000ff00");
        if (selRGColorId == R.id.radio_blue)
            color = Color.parseColor("#800000ff");

        SharedPreferences sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(WIDGET_TEXT + widgetID, et.getText().toString());
        editor.putInt(WIDGET_COLOR + widgetID, color);
        editor.apply();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        MyWidget.updateWidget(this, appWidgetManager, sp, widgetID);

        setResult(RESULT_OK, resultValue);

        Log.d(LOG_TAG, "finish config " + widgetID);
        
        finish();
    }
}