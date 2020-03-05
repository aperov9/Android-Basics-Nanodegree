package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsArticle>> {

    private String HTTP;

    public NewsLoader(Context context, String HTTP) {
        super(context);
        this.HTTP = HTTP;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {

        String response = null;
        List<NewsArticle> newsArticleList = null;

        URL url = Utilities.createUrl(HTTP);
        try {
            response = Utilities.makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(response ==null)){
            newsArticleList =Utilities.jSONtoNewsArticleList(response);
        }

        return newsArticleList;
    }
}
