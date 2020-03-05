package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        List<Word> colors = new ArrayList<Word>(){{
            add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
            add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
            add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
            add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
            add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
            add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
            add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
            add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        }};

        ListView listView = (ListView) findViewById(R.id.list);
        MediaPlayer mediaPlayer= new MediaPlayer();
        WordAdapter itemsAdapter = new WordAdapter(this,R.layout.list_item,colors,R.color.category_colors,mediaPlayer);
        listView.setAdapter(itemsAdapter);
    }
}
