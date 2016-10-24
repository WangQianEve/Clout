package com.ihandy.a2014011319;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category>{
    private int resourceId;
    public CategoryAdapter(Context context, int textViewResourceId, List<Category> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position); // 获取当前项的Fruit实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        Switch catSwitch = (Switch) view.findViewById(R.id.category);
        catSwitch.setText(category.getName());
        return view;
    }
}