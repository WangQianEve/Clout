package com.ihandy.a2014011319;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentPage extends Activity {
    private WebView webView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);
        webView = (WebView) findViewById(R.id.web_view);
//        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
//        webView.loadData(summary, "text/html", null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com");
//        Intent intent = getIntent(); String data = intent.getStringExtra("extra_data");
    }
}
