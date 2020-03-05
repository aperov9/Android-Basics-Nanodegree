package com.example.android.musicapp.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.android.musicapp.MainActivity;
import com.example.android.musicapp.R;

public class Library extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plain_listview);

        ListView lw = (ListView)findViewById(R.id.list);

        final List<Class> classes = new ArrayList<Class>(){{
            add(Library_Favorites.class);
            add(Library_Recent.class);
        }};

        List<String> libraryElements = new ArrayList<String>(){{
            add("Favorites");
            add("Recent Songs");
        }};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libraryElements);
        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Library.this,classes.get(position));
                startActivity(in);

            }
        });

        Button back_button =(Button)findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Library.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}
