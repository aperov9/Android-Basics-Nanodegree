package com.example.android.musicapp.data;

public class Song {

    private String mArtist="";
    private String mAlbum="";
    private String mSongName="";

    public Song(){
    }

    public Song(String mArtist, String mAlbum, String mSongName) {
        this.mArtist = mArtist;
        this.mAlbum = mAlbum;
        this.mSongName = mSongName;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public void setmAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getmSongName() {
        return mSongName;
    }

    public void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }
}
