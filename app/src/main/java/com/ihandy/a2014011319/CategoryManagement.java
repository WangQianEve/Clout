package com.ihandy.a2014011319;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
=======
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
>>>>>>> origin/master
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import java.util.ArrayList;

public class CategoryManagement extends Activity {
    private ArrayList<String> data;
    private boolean [] enable;
    ListView listView = null;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_manager);
        intent = getIntent();
        data = intent.getStringArrayListExtra("cats");
        enable = intent.getBooleanArrayExtra("enable");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( CategoryManagement.this, R.layout.my_plain_text, data){
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view= (TextView) LayoutInflater.from(getContext()).inflate(R.layout.my_plain_text,null);
                view.setText(data.get(position));
                if(!enable[position])
                    view.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected));
                return view;
            }
        };
=======
public class CategoryManagement extends Activity {
    private String[] data = { "Apple", "Banana", "Orange", "Watermelon", "Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( CategoryManagement.this, android.R.layout.simple_list_item_1, data);
>>>>>>> origin/master
        listView = (ListView) findViewById(R.id.category_list);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)listView.getChildAt((int)id);
                if(tv.getCurrentTextColor()== ContextCompat.getColor(getApplicationContext(),R.color.colorCategorySelected)){
                    tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected));
<<<<<<< HEAD
                    enable[(int)id]=false;
                }else if(tv.getCurrentTextColor()== ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected)){
                    tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategorySelected));
                    enable[(int)id]=true;
=======
                }else if(tv.getCurrentTextColor()== ContextCompat.getColor(getApplicationContext(),R.color.colorCategoryUnselected)){
                    tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorCategorySelected));
>>>>>>> origin/master
                }
            }
        });

    }
<<<<<<< HEAD
    @Override public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("cats", data);
        intent.putExtra("enable",enable);
        setResult(RESULT_OK, intent);
        finish();
=======
    protected void onDestroy(){
        super.onDestroy();
//        Log.e("checklist",listView.getCheckedItemCount()+"");
>>>>>>> origin/master
    }
}
