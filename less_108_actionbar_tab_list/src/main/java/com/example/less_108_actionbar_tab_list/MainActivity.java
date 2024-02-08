package com.example.less_108_actionbar_tab_list;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements
        //ActionBar.TabListener
    ActionBar.OnNavigationListener
{
    final String LOG_TAG = "myLogs";
    String[] data = new String[] { "one", "two", "three" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        ActionBar bar = getSupportActionBar();

//        bar.setTitle("App");
//        bar.setSubtitle("something");
        bar.setDisplayShowTitleEnabled(false);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bar.setListNavigationCallbacks(adapter, this);
//
//        ActionBar.Tab tab = bar.newTab();
//        tab.setText("tab1");
//        tab.setTabListener(this);
//        bar.addTab(tab);
//
//        tab = bar.newTab();
//        tab.setText("tab2");
//        tab.setTabListener(this);
//        bar.addTab(tab);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Log.d(LOG_TAG, "selected position = " + itemPosition +
                ", id = " + itemId +
                ", " + data[itemPosition]);
        return false;
    }
//
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Log.d(LOG_TAG, "selected tab: " + tab.getText());
//    }
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Log.d(LOG_TAG, "unselected tab: " + tab.getText());
//    }
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Log.d(LOG_TAG, "reselected tab: " + tab.getText());
//    }
}