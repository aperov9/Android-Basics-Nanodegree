package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        String[] number = {"one","two","three","four","five","six","seven","eight","nine","ten"};
        List<Word> numbers = new ArrayList<Word>(){{
            add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
            add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
            add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
            add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
            add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
            add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
            add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
            add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
            add(new Word("nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
            add(new Word("ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));
        }};

        ListView listView = (ListView) findViewById(R.id.list);
        MediaPlayer mediaPlayer= new MediaPlayer();
        WordAdapter itemsAdapter = new WordAdapter(this,R.layout.list_item,numbers,R.color.category_numbers,mediaPlayer);
        listView.setAdapter(itemsAdapter);


    }
}
