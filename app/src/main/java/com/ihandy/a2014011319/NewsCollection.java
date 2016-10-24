package com.ihandy.a2014011319;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsCollection extends Activity {
    final int INTENT_CONTENT = 2;
    NewsMap newsMap = null;
    NewsAdapter newsAdapter = null;
    List<News> newsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        newsMap = (NewsMap) getIntent().getSerializableExtra("collection");
        buildList();
        newsAdapter = new NewsAdapter(NewsCollection.this, R.layout.news, newsList);
        final ListView listView = (ListView) findViewById(R.id.collection_list);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsCollection.this,ContentPage.class);
                intent.putExtra("news",newsList.get((int)id));
                startActivityForResult(intent,INTENT_CONTENT);
            }
        });
    }
    private void buildList(){
        for (News value : newsMap.values()) {
            newsList.add(value);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("collection",newsMap);
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case INTENT_CONTENT:
                if (resultCode == RESULT_OK) {
                    News news = (News) data.getSerializableExtra("news");
                    String c = news.id;
                    boolean is = news.isCollected;
                    if(!is) {
                        newsList.remove(c);
                        newsMap.remove(c);
                        newsAdapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
        }
    }
}
