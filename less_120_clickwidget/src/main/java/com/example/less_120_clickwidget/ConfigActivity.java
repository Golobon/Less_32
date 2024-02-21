package com.example.less_120_clickwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TIME_FORMAT = "widget_time_format_";
    public final static String WIDGET_COUNT = "widget_count_";

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;
    SharedPreferences sp;
    EditText etFormat;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        etFormat = findViewById(R.id.etFormat);
        etFormat.setText(sp.getString(
                WIDGET_TIME_FORMAT + widgetID, "HH:mm:ss"));

        int cnt = sp.getInt(ConfigActivity.WIDGET_COUNT +
                widgetID, -1);
        if (cnt == -1) {
            sp.edit().putInt(WIDGET_COUNT + widgetID, 0);
        }
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        sp.edit().putString(WIDGET_TIME_FORMAT + widgetID,
                etFormat.getText().toString()).commit();
        MyWidget.updateWidget(this, AppWidgetManager.getInstance(this), widgetID);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}
