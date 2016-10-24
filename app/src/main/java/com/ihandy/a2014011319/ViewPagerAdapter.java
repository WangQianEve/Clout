package com.ihandy.a2014011319;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by qian on 2016/9/5.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;
    public ViewPagerAdapter(List<View> viewList,List<String> titleList){
        this.viewList=viewList;
        this.titleList = titleList;
    }
    public int getCount(){
        return viewList.size();
    }
    public CharSequence getPageTitle(int position){
        return titleList.get(position);
    }
    public void destroyItem(ViewGroup containter, int position , Object object){
        containter.removeView(viewList.get(position));
    }
    public Object instantiateItem(ViewGroup container, int position){
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
