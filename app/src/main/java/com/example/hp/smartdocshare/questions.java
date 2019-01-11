package com.example.hp.smartdocshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class questions extends AppCompatActivity {
    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        browser=(WebView)findViewById(R.id.webkit1);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("https://acc-smartdocshare.000webhostapp.com/test_paper.php");

    }
}
