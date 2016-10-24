package com.ihandy.a2014011319;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by qian on 2016/9/8.
 */
public class News implements Serializable {
    public String title;
    public String id;
    public String source;
    public String imageUrl;
    public String url;
    public boolean isCollected;
    transient public Bitmap bitmap = null;
    public News(String title, String id, String source, String imageUrl, String url){
        this.title = title;
        this.id = id;
        this.source = source;
        this.imageUrl = imageUrl;
        this.url = url;
        this.isCollected = false;
    }
}
