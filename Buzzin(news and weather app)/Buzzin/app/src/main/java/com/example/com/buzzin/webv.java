package com.example.com.buzzin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class webv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webv);
        Intent in=getIntent();
        String url="";
        if(in.hasExtra(Intent.EXTRA_TEXT)){
            url=in.getStringExtra(Intent.EXTRA_TEXT);
        }
        WebView myWebView = (WebView) findViewById(R.id.w1);
        myWebView.loadUrl(url);
    }
}
