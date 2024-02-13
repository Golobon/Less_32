package com.example.less_114_multiplescreen;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

public class DetailsActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle sacedInstanceState) {
        super.onCreate(sacedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE
        && isLarge()) {
            finish();
            return;
        }

        if (sacedInstanceState == null) {
            DetailsFragment details = DetailsFragment.newInstance(
                    getIntent().getIntExtra("position", 0));
            getSupportFragmentManager().
                    beginTransaction().
                    add(android.R.id.content, details).commit();
        }
    }
    private boolean isLarge() {
        return (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
