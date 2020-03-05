package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsArticleAdapter extends BaseAdapter {

    Context cx;
    List<NewsArticle> newsArticleList;

    public NewsArticleAdapter(Context cx, List<NewsArticle> newsArticleList) {
        this.cx = cx;
        this.newsArticleList = newsArticleList;
    }

    @Override
    public int getCount() {
        return newsArticleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(cx, R.layout.newsarticle_item,null);

        TextView text_title = (TextView)convertView.findViewById(R.id.text_title);
        TextView text_section = (TextView)convertView.findViewById(R.id.text_section);

        text_title.setText(newsArticleList.get(position).getmTitle().toString());
        text_section.setText(newsArticleList.get(position).getmCategory().toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(newsArticleList.get(position).getmLink().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                cx.startActivity(intent);
            }
        });

        return convertView;
    }
}
