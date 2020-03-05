package com.example.android.musicapp.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.android.musicapp.MainActivity;
import com.example.android.musicapp.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button back_button =(Button)findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Settings.this,MainActivity.class);
                startActivity(in);
            }
        });

        ListView lw = (ListView)findViewById(R.id.list);


        final List<String> libraryElements = new ArrayList<String>(){{
            add("Offline Mode");
            add("Auto Play");
            add("Display Lyrics");
        }};

        class ListviewAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return libraryElements.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = getLayoutInflater().inflate(R.layout.settings_list_item,null);
                TextView item = (TextView)view.findViewById(R.id.settings_text);
                item.setText(libraryElements.get(i));
                return view;
            }
        }

        lw.setAdapter(new ListviewAdapter());

    }
}
