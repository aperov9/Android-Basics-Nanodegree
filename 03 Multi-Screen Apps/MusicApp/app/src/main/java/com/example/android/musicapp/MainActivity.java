package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.musicapp.library.Library;
import com.example.android.musicapp.settings.Settings;
import com.example.android.musicapp.store.Store;


public class MainActivity extends AppCompatActivity {

    Button bStore;
    Button bLibrary;
    Button bSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStore = (Button)findViewById(R.id.store);
        bLibrary = (Button)findViewById(R.id.library);
        bSettings = (Button)findViewById(R.id.settings);

        bStore.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent iinent= new Intent(MainActivity.this,Store.class);
                        startActivity(iinent);
                    }
                });
        bLibrary.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent iinent= new Intent(MainActivity.this,Library.class);
                        startActivity(iinent);

                    }
                });
        bSettings.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent iinent= new Intent(MainActivity.this,Settings.class);
                        startActivity(iinent);
                    }
                });

    }

    public void onBackPressed() {
    }

}
