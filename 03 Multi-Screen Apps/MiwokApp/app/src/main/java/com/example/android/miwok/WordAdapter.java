package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorRecourseId;
    private MediaPlayer mediaPlayer;

    public WordAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Word> objects, int colorRecourceId, MediaPlayer mediaPlayer) {
        super(context, 0, objects);
        this.mColorRecourseId =colorRecourceId;
        this.mediaPlayer=mediaPlayer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Word currentWord = getItem(position);
        View listItemView = convertView;


        if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        View ly = listItemView.findViewById(R.id.tobecolored);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.english);
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwok);
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        ImageView playButton =(ImageView)listItemView.findViewById(R.id.playButton);

        nameTextView.setText(currentWord.getDefaultT());
        numberTextView.setText(currentWord.getMiwokT());

        if(currentWord.hasImage()){
            image.setImageResource(currentWord.getmImgResourceId());
            image.setVisibility(View.VISIBLE);
        }else {
            image.setVisibility(View.GONE);
        }

        ly.setBackgroundResource(mColorRecourseId);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(view.getContext(),currentWord.getmAudioResource());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
        });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}
