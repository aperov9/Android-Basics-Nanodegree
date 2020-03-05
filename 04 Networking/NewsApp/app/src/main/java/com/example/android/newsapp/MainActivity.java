package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>>{

    public static final String LOG_TAG = MainActivity.class.getName();
    private StringBuilder HTTP = new StringBuilder();
    private String HTTP1 ="https://content.guardianapis.com/search?tag=politics/politics&from-date=";
    private String HTTP2 ="&api-key=test";
    private NewsArticleAdapter newsArticleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HTTP.append(HTTP1).append(getCurrentTime()).append(HTTP2);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(0, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                ListView listView = (ListView)findViewById(R.id.list);
                listView.setAdapter(null);
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.restartLoader(0, null, MainActivity.this);
                break;
        }
        return true;
    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(date.getTime());
        return currentTime;
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        if(isOnline()) {
            return new NewsLoader(this, HTTP.toString());
        }else {
            TextView empty_text = (TextView)findViewById(R.id.empty_text);
            empty_text.setText("No internet connection.");
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> newsArticleList) {
        if(newsArticleList!=null){
            try {
                updateUI(newsArticleList);
            }catch (Exception e){
                Log.d(LOG_TAG,"Something went wrong.");
            }
        }else {
            return;
        }
    }

    private void updateUI(List<NewsArticle> newsArticleList) {
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(null);
        newsArticleAdapter = new NewsArticleAdapter(MainActivity.this,newsArticleList);
        listView.setAdapter(newsArticleAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {}

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
