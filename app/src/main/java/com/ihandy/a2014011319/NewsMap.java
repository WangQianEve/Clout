package com.ihandy.a2014011319;

import java.io.Serializable;
import java.util.*;
import java.util.Collection;

/**
 * Created by qian on 2016/9/8.
 */
public class NewsMap implements Serializable {
    private Map<String,News> map;
    public NewsMap(){
        map = new HashMap<String,News>();
    }
    public News get(String s){
        return map.get(s);
    }
    public void put(String s, News n){
        map.put(s,n);
    }
    public void remove(String s){
        map.remove(s);
    }
    public Collection<News> values(){
        return map.values();
    }
}
