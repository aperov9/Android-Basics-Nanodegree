package com.example.android.booklistingapp;

public class BookListing {

    private String mName;
    private String mAuthor;
    private String mPublisher;
    private String mLink;

    public BookListing(String mName, String mAuthor, String mPublisher, String mLink) {
        this.mName = mName;
        this.mAuthor = mAuthor;
        this.mPublisher = mPublisher;
        this.mLink = mLink;
    }

    public String getmName() {
        return mName;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmLink() {
        return mLink;
    }


    @Override
    public String toString() {
        return "BookListing{" +
                "mName='" + mName + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mPublisher='" + mPublisher + '\'' +
                ", mLink='" + mLink + '\'' +
                '}';
    }
}
