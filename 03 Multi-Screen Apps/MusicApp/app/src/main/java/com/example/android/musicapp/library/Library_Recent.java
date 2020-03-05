package com.example.android.musicapp.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.android.musicapp.CurrentSong;
import com.example.android.musicapp.R;
import com.example.android.musicapp.data.Song;
import com.example.android.musicapp.data.SongAdapter;

public class Library_Recent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plain_listview);

        ListView listView = (ListView)findViewById(R.id.list);

        final List<Song> songs = new ArrayList<Song>(){{
            add(new Song("Green Day","American Idiot","Homecoming"));
            add(new Song("Green Day","American Idiot","Whatsername"));
            add(new Song("Green Day","American Idiot","Wake Me Up When September Ends"));
        }};

        listView.setAdapter(new SongAdapter(Library_Recent.this,songs));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Library_Recent.this,CurrentSong.class);
                in.putExtra("songname",songs.get(position).getmSongName());
                startActivity(in);
            }
        });

        Button back_button =(Button)findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Library_Recent.this,Library.class);
                startActivity(in);
            }
        });
    }
}
