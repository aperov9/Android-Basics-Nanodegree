package com.example.android.tourguide.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.android.tourguide.EventsAdapter;
import com.example.android.tourguide.LocationInfo;
import com.example.android.tourguide.R;

public class FoodFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        ListView listView =(ListView)rootView.findViewById(R.id.list);

        final ArrayList<LocationInfo> food = new ArrayList<LocationInfo>(){{
            add(new LocationInfo(getString(R.string.food_name1),getString(R.string.food_description1),getString(R.string.location),4));
            add(new LocationInfo(getString(R.string.food_name2),getString(R.string.food_description2),getString(R.string.location),4));
            add(new LocationInfo(getString(R.string.food_name3),getString(R.string.food_description3),getString(R.string.location),5));
            add(new LocationInfo(getString(R.string.food_name4),getString(R.string.food_description4),getString(R.string.location),4));
        }};

        listView.setAdapter(new EventsAdapter(inflater,food));
        return rootView;
    }
}
