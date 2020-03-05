package com.example.android.pointscounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int lifePointsPlayer1 = 8000;
    int lifePointsPlayer2 = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView player1life = (TextView) findViewById(R.id.player1life);
        final TextView player2life = (TextView) findViewById(R.id.player2life);
        Button resetbutton = (Button) findViewById(R.id.resetbutton);
        Button divide2p1 = (Button) findViewById(R.id.divide2p1);
        Button p1plus2000 = (Button) findViewById(R.id.p1plus2000);
        Button p1minus2000 = (Button) findViewById(R.id.p1minus2000);
        Button p1plus1000 = (Button) findViewById(R.id.p1plus1000);
        Button p1minus1000 = (Button) findViewById(R.id.p1minus1000);
        Button p1plus500 = (Button) findViewById(R.id.p1plus500);
        Button p1minus500 = (Button) findViewById(R.id.p1minus500);
        Button p1plus100 = (Button) findViewById(R.id.p1plus100);
        Button p1minus100 = (Button) findViewById(R.id.p1minus100);
        Button divide2p2 = (Button) findViewById(R.id.divide2p2);
        Button p2plus2000 = (Button) findViewById(R.id.p2plus2000);
        Button p2minus2000 = (Button) findViewById(R.id.p2minus2000);
        Button p2plus1000 = (Button) findViewById(R.id.p2plus1000);
        Button p2minus1000 = (Button) findViewById(R.id.p2minus1000);
        Button p2plus500 = (Button) findViewById(R.id.p2plus500);
        Button p2minus500 = (Button) findViewById(R.id.p2minus500);
        Button p2plus100 = (Button) findViewById(R.id.p2plus100);
        Button p2minus100 = (Button) findViewById(R.id.p2minus100);

        Button coinbutton = (Button) findViewById(R.id.coinbutton);


        coinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, CoinActivity.class);
                startActivity(in);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer1 = 8000;
                lifePointsPlayer2 = 8000;
                player1life.setText("" + lifePointsPlayer1);
                player2life.setText("" + lifePointsPlayer2);


            }
        });

        divide2p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("###.#");
                lifePointsPlayer1 = lifePointsPlayer1 / 2;
                player1life.setText("" + df.format(lifePointsPlayer1));

            }
        });

        p1plus2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer1 = lifePointsPlayer1 + 2000;
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1plus1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer1 = lifePointsPlayer1 + 1000;
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1plus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer1 = lifePointsPlayer1 + 500;
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1plus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer1 = lifePointsPlayer1 + 100;
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1minus2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer1 < 2000) {
                    lifePointsPlayer1 = 0;
                } else {
                    lifePointsPlayer1 = lifePointsPlayer1 - 2000;
                }
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1minus1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer1 < 1000) {
                    lifePointsPlayer1 = 0;
                } else {
                    lifePointsPlayer1 = lifePointsPlayer1 - 1000;
                }
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1minus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer1 < 500) {
                    lifePointsPlayer1 = 0;
                } else {
                    lifePointsPlayer1 = lifePointsPlayer1 - 500;
                }
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        p1minus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer1 < 100) {
                    lifePointsPlayer1 = 0;
                } else {
                    lifePointsPlayer1 = lifePointsPlayer1 - 100;
                }
                player1life.setText("" + lifePointsPlayer1);
            }
        });

        divide2p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("###.#");
                lifePointsPlayer2 = lifePointsPlayer2 / 2;
                player2life.setText("" + df.format(lifePointsPlayer2));

            }
        });

        p2plus2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer2 = lifePointsPlayer2 + 2000;
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2plus1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer2 = lifePointsPlayer2 + 1000;
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2plus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer2 = lifePointsPlayer2 + 500;
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2plus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifePointsPlayer2 = lifePointsPlayer2 + 100;
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2minus2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer2 < 2000) {
                    lifePointsPlayer2 = 0;
                } else {
                    lifePointsPlayer2 = lifePointsPlayer2 - 2000;
                }
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2minus1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer2 < 1000) {
                    lifePointsPlayer2 = 0;
                } else {
                    lifePointsPlayer2 = lifePointsPlayer2 - 1000;
                }
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2minus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer2 < 500) {
                    lifePointsPlayer2 = 0;
                } else {
                    lifePointsPlayer2 = lifePointsPlayer2 - 500;
                }
                player2life.setText("" + lifePointsPlayer2);
            }
        });

        p2minus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lifePointsPlayer2 < 100) {
                    lifePointsPlayer2 = 0;
                } else {
                    lifePointsPlayer2 = lifePointsPlayer2 - 100;
                }
                player2life.setText("" + lifePointsPlayer2);
            }
        });

    }
}

