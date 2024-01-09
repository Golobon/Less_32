package com.example.less_71_preferences;

//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends Activity {
//
//    TextView tvInfo;
//    SharedPreferences sp;
//
//    /** Called when the activity is first created. */
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tvInfo = (TextView) findViewById(R.id.tvInfo);
//
//        // получаем SharedPreferences, которое работает с файлом настроек
//        sp = PreferenceManager.getDefaultSharedPreferences(this);
//        // полная очистка настроек
//        // sp.edit().clear().commit();
//    }
//
//    protected void onResume() {
//        Boolean notif = sp.getBoolean("notif", false);
//        String address = sp.getString("address", "");
//        String text = "Notifications are "
//                + ((notif) ? "enabled, address = " + address : "disabled");
//        tvInfo.setText(text);
//        super.onResume();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        MenuItem mi = menu.add(0, 1, 0, "Preferences");
//        mi.setIntent(new Intent(this, PrefActivity.class));
//        return true;
//    }
//}


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv_info);

        // получаем SharedPreferences, которое работает с файлом настроек
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        // полная очистка настроек
        // sp.edit().clear().commit();
    }

    protected void onResume() {
        boolean notif = sp.getBoolean("notif", false);
        String address = sp.getString("address", "");
        String text = "Notifications are "
                + ((notif) ? "enabled, address = " + address : "disabled");
        tv.setText(text);
        super.onResume();
    }
    //Чтобы onCreateOptionsMenu сработало, нужно в Theme убрать
    // после "DayNight" убрать ".NoActionBar"
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}