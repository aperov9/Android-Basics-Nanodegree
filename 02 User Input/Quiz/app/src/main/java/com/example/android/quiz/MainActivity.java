package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radio1text4 = (RadioButton)findViewById(R.id.radio1text4);

                CheckBox answer1text = (CheckBox)findViewById(R.id.answer1text);
                CheckBox answer2text = (CheckBox)findViewById(R.id.answer2text);
                CheckBox answer3text = (CheckBox)findViewById(R.id.answer3text);
                CheckBox answer4text = (CheckBox)findViewById(R.id.answer4text);

                EditText editT1 = (EditText)findViewById(R.id.editT1);

                RadioButton radio2text2 = (RadioButton)findViewById(R.id.radio2text2);

                int sum=0;
                if(radio1text4.isChecked()) {
                    sum++;
                }

                if (answer1text.isChecked() && answer3text.isChecked() && !answer2text.isChecked() && !answer4text.isChecked()){
                        sum++;
                }

                if(editT1.getText().toString().toLowerCase().trim().equals("barack")){
                    sum++;
                }

                if(radio2text2.isChecked()){
                    sum++;
                }

                Toast.makeText(MainActivity.this,"You scored "+sum+" out of 4",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
