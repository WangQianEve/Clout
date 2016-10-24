package com.ihandy.a2014011319;

import android.app.Activity;
import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class ContentPage extends Activity {
    private WebView webView ;
    private News news;
    private Button shareButton ;
    private Button collectButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_page);
        news = (News) getIntent().getSerializableExtra("news");
        final String url = news.url;
        shareButton = (Button) findViewById(R.id.share_button);
        collectButton = (Button) findViewById(R.id.collect_button);
        if(news.isCollected)collectButton.setBackgroundResource(R.drawable.red_heart);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "Big News From Clout! "+url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCollect();
            }
        });
        webView = (WebView) findViewById(R.id.web_view);
        if(url.equals("null")){
            Toast.makeText(this, "No Source", Toast.LENGTH_LONG).show();
            return;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest url){
                webView.loadUrl(url.toString());
                return true;
            }
        });
        webView.loadUrl(url);
    }
    @Override public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("news",news);
        setResult(RESULT_OK, intent);
        finish();
    }
    void handleCollect(){
        if(news.isCollected){
            news.isCollected = false;
            collectButton.setBackgroundResource(R.drawable.grey_favorite);
            Toast.makeText(ContentPage.this, "Cancel Collection", Toast.LENGTH_SHORT).show();
        }
        else{
            news.isCollected = true ;
            collectButton.setBackgroundResource(R.drawable.red_heart);
            Toast.makeText(ContentPage.this, "Add to Collection", Toast.LENGTH_SHORT).show();
        }
=======
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
>>>>>>> origin/master
    }
}
