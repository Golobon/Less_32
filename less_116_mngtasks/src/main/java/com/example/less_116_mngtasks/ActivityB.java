package com.example.less_116_mngtasks;

import android.content.Intent;
import android.view.View;

public class ActivityB extends MainActivity {
    public void onClick(View v) {
        if (butStart.equals(v)) {
            startActivity(new Intent(this, ActivityC.class));
        } else if (butInfo.equals(v)) {
            showInfo();
        }
    }
}
