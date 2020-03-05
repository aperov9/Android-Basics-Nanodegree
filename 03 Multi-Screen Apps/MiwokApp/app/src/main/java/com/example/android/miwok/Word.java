package com.example.android.miwok;

public class Word {

    private String defaultT;
    private String miwokT;
    private static final int NO_IMAGE=-1;
    private int mImgResourceId = NO_IMAGE;
    private int mAudioResource;

    public Word(String defaultT, String miwokT, int mImgResourceId, int mAudioResource) {
        this.defaultT = defaultT;
        this.miwokT = miwokT;
        this.mImgResourceId = mImgResourceId;
        this.mAudioResource = mAudioResource;
    }

    public Word(String defaultT, String miwokT, int mAudioResource) {
        this.defaultT = defaultT;
        this.miwokT = miwokT;
        this.mAudioResource = mAudioResource;
    }

    public String getMiwokT(){
        return miwokT;
    }

    public String getDefaultT(){
        return defaultT;
    }

    public int getmImgResourceId(){
        return mImgResourceId;
    }

    public boolean hasImage(){
      return mImgResourceId != NO_IMAGE;
    };

    public int getmAudioResource() {
        return mAudioResource;
    }

    @Override
    public String toString() {
        return "Word{" +
                "defaultT='" + defaultT + '\'' +
                ", miwokT='" + miwokT + '\'' +
                ", mImgResourceId=" + mImgResourceId +
                ", mAudioResource=" + mAudioResource +
                '}';
    }


}
