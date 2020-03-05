package com.example.android.pointscounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CoinActivity extends AppCompatActivity {

    int headcounter=0;
    int tailcounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        final ImageView view = (ImageView)findViewById(R.id.image);
        Button flip = (Button)findViewById(R.id.flip);

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double random = Math.random();

                if(random<0.5){
                    if(headcounter==0) {
                        view.setImageResource(R.drawable.tails);
                        headcounter++;
                        tailcounter=0;
                    }
                    else{
                        view.setImageResource(R.drawable.tails_twice);
                        headcounter=0;
                        tailcounter=0;
                    }
                }
                else{
                    if(tailcounter==0) {
                        view.setImageResource(R.drawable.head);
                        tailcounter++;
                        headcounter=0;
                    }
                    else{
                        view.setImageResource(R.drawable.head_twice);
                        tailcounter=0;
                        headcounter=0;
                    }
                }

            }
        });
    }
}
