package com.example.android.newsapp;

public class NewsArticle {

    private String mTitle;
    private String mLink;
    private String mCategory;

    public NewsArticle(String mTitle, String mLink, String mCategory) {
        this.mTitle = mTitle;
        this.mLink = mLink;
        this.mCategory = mCategory;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmLink() {
        return mLink;
    }

    public String getmCategory() {
        return mCategory;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "mTitle='" + mTitle + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mCategory='" + mCategory + '\'' +
                '}';
    }
}
