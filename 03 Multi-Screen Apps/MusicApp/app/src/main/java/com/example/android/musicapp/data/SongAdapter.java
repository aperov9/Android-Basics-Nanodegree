package com.example.android.musicapp.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.musicapp.R;

import java.util.List;


public class SongAdapter extends BaseAdapter {

    Context cx;
    List<Song> songs;

    public SongAdapter(Context cx, List<Song> songs){
        this.cx=cx;
        this.songs=songs;
    }

    @Override
    public int getCount() {
        return songs.size();
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
        convertView= View.inflate(cx, R.layout.song_item,null);

        TextView song_item_artist =(TextView)convertView.findViewById(R.id.song_item_artist);
        TextView song_item_album =(TextView)convertView.findViewById(R.id.song_item_album);
        TextView song_item_song =(TextView)convertView.findViewById(R.id.song_item_song);

        song_item_artist.setText(songs.get(position).getmArtist().toString());
        song_item_album.setText(songs.get(position).getmAlbum().toString());
        song_item_song.setText(songs.get(position).getmSongName().toString());

        return convertView;
    }
}
