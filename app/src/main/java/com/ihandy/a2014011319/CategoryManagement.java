package com.ihandy.a2014011319;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryManagement extends Activity {
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)listView.getChildAt((int)id);
                if(tv.getCurrentTextColor()== ContextCompat.getColor(getApplicationContext(),R.color.colorCategorySelected)){
                    tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected));
                }else if(tv.getCurrentTextColor()== ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected)){
                    tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategorySelected));
                }
            }
        });

    }
    protected void onDestroy(){
        super.onDestroy();
//        Log.e("checklist",listView.getCheckedItemCount()+"");
    }
}
