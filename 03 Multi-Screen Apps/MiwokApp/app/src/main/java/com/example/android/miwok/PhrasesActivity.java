package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        List<Word> phrases = new ArrayList<Word>(){{
            add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
            add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
            add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
            add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
            add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
            add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
            add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
            add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
            add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
            add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));
        }};


        ListView listView = (ListView) findViewById(R.id.list);
        mediaPlayer= new MediaPlayer();
        WordAdapter itemsAdapter = new WordAdapter(this,R.layout.list_item,phrases,R.color.category_phrases,mediaPlayer);
        listView.setAdapter(itemsAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
