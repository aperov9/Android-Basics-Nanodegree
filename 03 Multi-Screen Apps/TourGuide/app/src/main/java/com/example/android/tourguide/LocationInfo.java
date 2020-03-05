package com.example.android.tourguide;

import java.io.Serializable;

public class LocationInfo implements Serializable{

    private String mName;
    private String mDescription;
    private String mLocation;
    private int mRating;
    private int mImageResource;


    public LocationInfo(String mName, String mDescription, String mLocation, int mRating) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mRating = mRating;
    }

    public LocationInfo(String mName, String mDescription, String mLocation, int mRating, int mImageResource) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mRating = mRating;
        this.mImageResource = mImageResource;
    }


    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLocation() {
        return mLocation;
    }

    public int getmRating() {
        return mRating;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mLocation='" + mLocation + '\'' +
                ", mRating=" + mRating +
                ", mImageResource=" + mImageResource +
                '}';
    }
}
