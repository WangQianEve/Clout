package com.ihandy.a2014011319;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by qian on 2016/9/8.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final News news = getItem(position); // 获取当前项的Fruit实例
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else {
            view = convertView;
        }
        final ImageView newsImage = (ImageView) view.findViewById(R.id.news_image);
        TextView title=(TextView)view.findViewById(R.id.news_title);
        title.setText(news.title);
        TextView source = (TextView)view.findViewById(R.id.textView);
        source.setText(news.source);
        try{
            if(news.bitmap!=null){
                newsImage.setImageBitmap(news.bitmap);
                return view;
            }else{
                new Thread() {
                    public void run() {
                        try{
                            news.bitmap = loadImage(news.imageUrl,news.id);
                            newsImage.post(new Runnable() {
                                @Override
                                public void run() {
                                    newsImage.setImageBitmap(news.bitmap);
                                }
                            });
                        }catch (Exception e){
                            Log.e("excp",e.toString());
                        }
                    }
                }.start();
            }
        }catch (Exception e) {
        }
        return view;
    }
    private Bitmap loadImage(String image,String id){
        Bitmap bitmap = null;
        FileInputStream fileInputStream = null;
        URL url;
        try{
            fileInputStream = getContext().openFileInput(id+".png");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (Exception e){
            try{
                url = new URL(image);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
                FileOutputStream fileOutputStream = getContext().openFileOutput(id+".png",Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }catch (Exception ee){
                Log.e("my",ee.toString());
            }
        }finally {
            try{
                if(fileInputStream!=null)
                    fileInputStream.close();
            }catch (Exception e ){}
            return bitmap;
        }

    }
}
