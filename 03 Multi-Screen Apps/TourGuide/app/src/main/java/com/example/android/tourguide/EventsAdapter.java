package com.example.android.tourguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends BaseAdapter {

    private static boolean NO_IMAGE = false;

    LayoutInflater inf;
    ArrayList<LocationInfo> locationInfoArrayList;
    Boolean showPictures = NO_IMAGE;

    public EventsAdapter(LayoutInflater inf, ArrayList<LocationInfo> locationInfoArrayList) {
        this.inf = inf;
        this.locationInfoArrayList = locationInfoArrayList;
    }

    public EventsAdapter(LayoutInflater inf, ArrayList<LocationInfo> locationInfoArrayList, Boolean showPictures) {
        this.inf = inf;
        this.locationInfoArrayList = locationInfoArrayList;
        this.showPictures = showPictures;
    }

    @Override
    public int getCount() {
        return locationInfoArrayList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(showPictures){
            convertView = inf.inflate(R.layout.list_item_picture, parent,false);
            ImageView location_picture = (ImageView)convertView.findViewById(R.id.location_picture);
            location_picture.setImageResource(locationInfoArrayList.get(position).getmImageResource());

        }else {
            convertView = inf.inflate(R.layout.list_item, parent,false);
        }


        TextView location_name = (TextView)convertView.findViewById(R.id.location_name);
        TextView location_description = (TextView)convertView.findViewById(R.id.location_description);
        TextView location_location = (TextView)convertView.findViewById(R.id.location_location);
        TextView location_rating = (TextView)convertView.findViewById(R.id.location_rating);

        location_name.setText(locationInfoArrayList.get(position).getmName());
        location_description.setText(locationInfoArrayList.get(position).getmDescription());
        location_location.setText(locationInfoArrayList.get(position).getmLocation());
        location_rating.setText(locationInfoArrayList.get(position).getmRating()+"/5");

        return convertView;
    }
}
