package com.example.less_83_handler_callback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    Handler handler;

    Handler.Callback hc = msg -> {
        Log.d(LOG_TAG, "what = " + msg.what);
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        handler = new Handler(hc);
        sendMessage();
    }

    private void sendMessage() {
        Log.d(LOG_TAG, "send messages");
        handler.sendEmptyMessageDelayed(1, 1000);
        handler.sendEmptyMessageDelayed(2, 2000);
        handler.sendEmptyMessageDelayed(3, 3000);
    }
}