package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        List<Word> family = new ArrayList<Word>(){{
            add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
            add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
            add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
            add(new Word("daughter","oyyisa",R.drawable.family_daughter,R.raw.family_daughter));
            add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
            add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
            add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
            add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
            add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
            add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        }};

        ListView listView = (ListView) findViewById(R.id.list);
        MediaPlayer mediaPlayer= new MediaPlayer();
        WordAdapter itemsAdapter = new WordAdapter(this,R.layout.list_item,family,R.color.category_family,mediaPlayer);
        listView.setAdapter(itemsAdapter);
    }
}
