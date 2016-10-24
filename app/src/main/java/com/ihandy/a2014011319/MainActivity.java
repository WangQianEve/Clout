package com.ihandy.a2014011319;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SwipeRefreshLayout.OnRefreshListener {
    //final
    final int INTENT_CONTENT = 2;
    final int INTENT_CAT = 1;
    final int INTENT_COLLECT = 3;
    final String FILE_TAG = "file_tag";
    final String FILE_COLLECTION = "file_collection";
    private String getNewsListHttp = new String("http://assignment.crazz.cn/news/query?locale=en&category=<category>&max_news_id=<news_id>");
    //common
    private LayoutInflater layoutInflater;
    private boolean not_first_time=false;
    public boolean refresh_succeed=false;
    //tab
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<View> viewList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeLayout;
    private NewsAdapter newsAdapter;
    //data
    private ArrayList<String> validCatList = new ArrayList<>();
    private ArrayList<String> catList = new ArrayList<>();
    private ArrayList<String> keyCatList = new ArrayList<>();
    private boolean [] catEnable;
    private NewsMap newsCollection = new NewsMap();
    private List<List<News>> newsList = new ArrayList<>();
    //file io
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private StringBuilder content = new StringBuilder();
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private JSONObject jsonObject2;
    private JSONArray jsonArray2;
    @Override
    public void onRefresh() {
        int i=tabLayout.getSelectedTabPosition(),ii=0;
        String title =validCatList.get(i);
        for(ii = i ; ii<catList.size(); ++ii){
            if(catList.get(ii).equals(title))break;
        }
        if(ii==catList.size())return;
        fillTab(ii,true,"");
        adapt(ii,i);
        mSwipeLayout=(SwipeRefreshLayout) viewList.get(i).findViewById(R.id.swipe_ly);
        mSwipeLayout.setRefreshing(false);
    }
=======
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //tab
        fetchCategory();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        layoutInflater = LayoutInflater.from(this);
        for(int i=0; i<catList.size(); ++i){
            if(!catEnable[i])continue;
            final int k = i;
            viewList.add(createTab(i));
            adapt(i,viewList.size()-1);
            tabLayout.addTab(tabLayout.newTab().setTag(catList.get(i)));
        }
        viewPagerAdapter = new ViewPagerAdapter(viewList,validCatList);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        //collection
        fetchCollection();
        not_first_time=true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        ObjectOutputStream objos;
        try{
            fileOutputStream = openFileOutput(FILE_TAG, Context.MODE_PRIVATE);
            objos = new ObjectOutputStream(fileOutputStream);
            objos.writeObject(validCatList);
            objos.close();
            fileOutputStream = openFileOutput(FILE_COLLECTION,Context.MODE_PRIVATE);
            objos = new ObjectOutputStream(fileOutputStream);
            objos.writeObject(newsCollection);
            objos.close();
            for(int i=0; i<catList.size(); ++i){
                if(!catEnable[i])continue;
                fileOutputStream = openFileOutput(catList.get(i),Context.MODE_PRIVATE);
                jsonArray = new JSONArray();
                for(int j = 0; j<newsList.get(i).size(); ++j){
                    News news = newsList.get(i).get(j);
                    jsonObject = new JSONObject();
                    jsonObject.put("category",catList.get(i));
                    jsonObject.put("origin",news.source);
                    jsonObject.put("news_id",news.id);
                    jsonObject.put("title",news.title);
                    jsonObject2 = new JSONObject();
                    jsonObject2.put("url",news.url);
                    jsonObject.put("source",jsonObject2);
                    jsonArray2 = new JSONArray();
                    jsonObject2 = new JSONObject();
                    jsonObject2.put("url",news.imageUrl);
                    jsonArray2.put(jsonObject2);
                    jsonObject.put("imgs",jsonArray2);
                    jsonArray.put(jsonObject);
                }
                jsonObject = new JSONObject();
                jsonObject.put("news",jsonArray);
                jsonObject2 = new JSONObject();
                jsonObject2.put("data",jsonObject);
                fileOutputStream = openFileOutput(keyCatList.get(i), Context.MODE_PRIVATE);
                bufferedWriter = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(fileOutputStream)));
                bufferedWriter.write(jsonObject2.toString());
                bufferedWriter.close();
            }
        }catch (Exception e){
            Log.e("on destroy",e.toString());
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this,CategoryManagement.class);
            intent.putStringArrayListExtra("cats",catList);
            intent.putExtra("enable",catEnable);
            startActivityForResult(intent,INTENT_CAT);
        } else if (id == R.id.nav_collection) {
            Intent intent = new Intent(MainActivity.this, NewsCollection.class);
            intent.putExtra("collection",newsCollection);
            startActivityForResult(intent,INTENT_COLLECT);
        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(MainActivity.this, AboutMe.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case INTENT_CAT:
                if (resultCode == RESULT_OK) {
                    catEnable = data.getBooleanArrayExtra("enable");
                    for(int i=0,k=0; i<catEnable.length; ++i){
                        if(k==validCatList.size()){
                            if(catEnable[i]){
                                validCatList.add(k,catList.get(i));
                                viewList.add(k,createTab(i));
                                adapt(i,k);
                                k++;
                            }
                            continue;
                        }
                        if(catEnable[i]&&validCatList.get(k).equals(catList.get(i))){
                            k++;
                            continue;
                        }
                        if(catEnable[i]&& !validCatList.get(k).equals(catList.get(i))){
                            validCatList.add(k,catList.get(i));
                            viewList.add(k,createTab(i));
                            adapt(i,k);
                            k++;
                            continue;
                        }
                        if(!catEnable[i]&& validCatList.get(k).equals(catList.get(i))){
                            validCatList.remove(k);
                            viewList.remove(k);
                            continue;
                        }
                    }
                    tabLayout.removeAllTabs();
                    for(int i=0; i<validCatList.size(); ++i){
                        tabLayout.addTab(tabLayout.newTab().setTag(validCatList.get(i)));
                        viewPagerAdapter.notifyDataSetChanged();
                    }
                    viewPagerAdapter = new ViewPagerAdapter(viewList,validCatList);
                    viewPager.setAdapter(viewPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                break;
            case INTENT_CONTENT:
                if (resultCode == RESULT_OK) {
                    News news = (News) data.getSerializableExtra("news");
                    String c = news.id;
                    boolean is = news.isCollected;
                    if(newsCollection.get(c)==null && is){
                        newsCollection.put(c,news);
                    }
                    else if (newsCollection.get(c)!= null && !is)
                        newsCollection.remove(c);
                }
                break;
            case INTENT_COLLECT:
                if (resultCode == RESULT_OK) {
                    newsCollection = (NewsMap) data.getSerializableExtra("collection");
                }
                break;
            default:
        }
    }

    private void modify_valid_cat(){
        try {
            fileInputStream = openFileInput(FILE_TAG);
            ObjectInputStream objin = new ObjectInputStream(fileInputStream);
            validCatList = (ArrayList<String>) objin.readObject();
            objin.close();
        } catch (Exception e) {
        }finally {
            StringBuilder log=new StringBuilder();
            for(int i=0; i<catEnable.length; ++i){
                if(validCatList.contains(catList.get(i)))catEnable[i]=true;
                else catEnable[i]=false;
                log.append(catEnable+" ");
            }
            Log.e("cat",log.toString());
        }
    }
    private void fetchCollection(){
        try{
            fileInputStream = openFileInput(FILE_COLLECTION);
            ObjectInputStream objin = new ObjectInputStream(fileInputStream);
            newsCollection = (NewsMap) objin.readObject();
        }catch (Exception e){ }
    }
    private void fetchCategory(){
        try {
            String [] key = {"entertainment","health","national","sports","technology","top_stories","world"};
            String [] title = {"Entertainment","Health","Nigeria","Sports","Technology","Top Stories","World"};
            int le=key.length,i =0;
            while(i<le){
                validCatList.add(title[i]);
                keyCatList.add(key[i]);
                catList.add(title[i]);
                List<News> temp = new ArrayList<>();
                newsList.add(temp);
                i++;
            }
            catEnable = new boolean[catList.size()];
            modify_valid_cat();
        }catch (Exception e){
            Log.e("Exception",e.getLocalizedMessage());
        }
    }

    private View createTab(int i){
        View view=layoutInflater.inflate(R.layout.layout_tab,null);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_ly);
        mSwipeLayout.setOnRefreshListener(this);
        fillTab(i,true,"");
        return view;
    }
    private void fillTab(int ii,boolean abandon,String max_id){//newslist
        final int i=ii;
        if(abandon)newsList.get(i).clear();
        String title, id, source, imgUrl, url, kcat=keyCatList.get(i);
        try {
            refresh_succeed = false;
            if(max_id.length()==0){
                refresh(kcat);
            } else
                refresh(kcat,max_id);
            if(!refresh_succeed){
                if(not_first_time)Toast.makeText(this, "load failed", Toast.LENGTH_SHORT).show();
                if(max_id.length()!=0)return;
            }else{
                if(not_first_time)Toast.makeText(this, "load succeed", Toast.LENGTH_SHORT).show();
            }
            fileInputStream = openFileInput(kcat);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line ="";
            content.delete(0,content.length());
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            jsonObject = new JSONObject(content.toString());
            String data = jsonObject.getString("data");
            jsonObject = new JSONObject(data);
            data = jsonObject.getString("news");
            jsonArray = new JSONArray(data);
            for(int j=0; j<jsonArray.length(); ++j){
                jsonObject = jsonArray.getJSONObject(j);
                id=jsonObject.getString("news_id");
                source=jsonObject.getString("origin");
                title=jsonObject.getString("title");
                url=jsonObject.get("source").toString();
                if(!url.equals("null")){
                    jsonObject2 = jsonObject.getJSONObject("source");
                    url=jsonObject2.getString("url");
                }
                jsonArray2=jsonObject.getJSONArray("imgs");
                imgUrl=jsonArray2.getJSONObject(0).getString("url");
                News news = new News(title,id,source,imgUrl,url);
                newsList.get(i).add(news);
            }
        } catch(Exception e) {
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
        }
    }
    private void refresh(String kcat){
        String http = getNewsListHttp.replaceAll("<category>",kcat);
        http = http.replaceAll("&max_news_id=<news_id>","");
        sendAndWrite(http,kcat);
    }
    private void refresh(String kcat,String id){
        String http = getNewsListHttp.replaceAll("<category>",kcat);
        http = http.replaceAll("<news_id>",id);
        sendAndWrite(http,kcat);
    }
    private void sendAndWrite(String http, String c){
        final String shttp = http;
        final String kcat = c;
        try {
            Thread thread = new Thread(new Runnable() {
                String line = "";
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(shttp);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        final StringBuilder stringBuilder = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        line = stringBuilder.toString();
                        writeFile(line, kcat);
                    } catch (Exception e) {
                    } finally {
                        if(line.length()==0){
                            refresh_succeed=false;
                        }else{
                            refresh_succeed=true;
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }
            });
            thread.start();
            thread.join();
        }catch (Exception e){}
    }
    private void writeFile(String content , String kcat){
        try {
            fileOutputStream = openFileOutput(kcat, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(content);
        }
        catch (Exception e) {
            Log.e("myExcep",e.toString());
        }
        finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                Log.e("myExcep", e.toString());
            }
        }

    }
    private void adapt(int ii, final int view_no){
        final int i=ii;
        newsAdapter = new NewsAdapter(MainActivity.this, R.layout.news, newsList.get(i));
        final ListView listView = (ListView) viewList.get(view_no).findViewById(R.id.news_item);
        listView.setAdapter(newsAdapter);
        if(listView.getFooterViewsCount()==0){
            TextView textView = new TextView(this);
            textView.setText("Load More");
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0,80,0,80);
            listView.addFooterView(textView);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id==-1){
                    String iid="";
                    if(position!=0){
                        iid=newsList.get(i).get((int)position-1).id;
                        Long lid=new Long(iid);
                        lid-=1;
                        iid=lid.toString();
                    }
                    fillTab(i,false,iid);
                    adapt(i,view_no);
                    return;
                }
                Intent intent = new Intent(MainActivity.this,ContentPage.class);
                News news = newsList.get(i).get((int)id);
                if(newsCollection.get(news.id)!=null)news.isCollected=true;
                else news.isCollected=false;
                intent.putExtra("news",news);
//                Log.e("http",news.url);
                startActivityForResult(intent,INTENT_CONTENT);
            }
        });
    }
=======
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String data = "Hello SecondActivity";
                Intent intent = new Intent(MainActivity.this,ContentPage.class);
                intent.putExtra("extra_data", data); startActivity(intent);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
>>>>>>> origin/master
}
