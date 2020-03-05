package com.example.android.musicapp.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.android.musicapp.R;
import com.example.android.musicapp.data.Song;
import com.example.android.musicapp.data.SongAdapter;

public class Store_Recommendations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plain_listview);

        ListView listView = (ListView)findViewById(R.id.list);

        final List<Song> songs = new ArrayList<Song>(){{
            add(new Song("Depeche Mode","Delta Machine","Heaven"));
            add(new Song("Depeche Mode","Delta Machine","Angel"));
            add(new Song("Depeche Mode","Delta Machine","Slow"));
            add(new Song("Depeche Mode","Delta Machine","Broken"));
            add(new Song("Depeche Mode","Delta Machine","Alone"));
            add(new Song("Depeche Mode","Delta Machine","Goodbye"));
        }};

        listView.setAdapter(new SongAdapter(Store_Recommendations.this,songs));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Store_Recommendations.this,Store_Song.class);
                in.putExtra("songname",songs.get(position).getmSongName());
                startActivity(in);
            }
        });

        Button back_button =(Button)findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Store_Recommendations.this,Store.class);
                startActivity(in);
            }
        });
    }
}
