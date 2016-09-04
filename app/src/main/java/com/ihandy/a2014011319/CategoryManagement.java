package com.ihandy.a2014011319;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryManagement extends AppCompatActivity {
    private String[] data = { "Apple", "Banana", "Orange", "Watermelon", "Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( CategoryManagement.this, android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.category_list);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.e("checklist",listView.getCheckedItemCount()+"");
    }
}
