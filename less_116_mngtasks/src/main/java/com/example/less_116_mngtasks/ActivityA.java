package com.example.less_116_mngtasks;

import android.content.Intent;
import android.view.View;

public class ActivityA extends MainActivity {
    public void onClick(View v) {
        if (butStart.equals(v)) {
            startActivity(new Intent(this, ActivityB.class).
                    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        } else if (butInfo.equals(v)) {
            showInfo();
        }
    }
}
