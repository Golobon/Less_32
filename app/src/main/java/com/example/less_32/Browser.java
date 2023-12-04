package com.example.less_32;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_activity);

        WebView webView = findViewById(R.id.web_view);

        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());
    }
}