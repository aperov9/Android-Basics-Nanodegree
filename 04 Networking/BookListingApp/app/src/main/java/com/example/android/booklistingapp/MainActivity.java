package com.example.android.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    StringBuilder StringURL = new StringBuilder();
    String http = "https://www.googleapis.com/books/v1/volumes?maxResults=15&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_search = (Button)findViewById(R.id.button_search);
        final EditText input_search = (EditText)findViewById(R.id.input_search);
        final TextView text_empty = (TextView)findViewById(R.id.text_empty);
        final ListView listView = (ListView)findViewById(R.id.list);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOnline()) {
                    StringURL.delete(0, StringURL.length());
                    String toSearch = input_search.getText().toString();

                    if (toSearch.isEmpty()) {
                        text_empty.setText("Please enter a keyword");
                    } else {
                        String replaced = toSearch.replaceAll(" ", "%25");
                        StringURL.append(http).append(replaced);
                        URL realURL = Utilities.createUrl(StringURL.toString());
                        new AsyncRequest().execute(realURL);
                    }
                }else{
                    listView.setAdapter(null);
                    text_empty.setText("No Internet Connection");
                }
            }
        });

    }

    class AsyncRequest extends AsyncTask<URL,Void,List<BookListing>>{

        String jsonText;

        @Override
        protected List<BookListing> doInBackground(URL... params) {

            try {
                jsonText =Utilities.makeHttpRequest(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<BookListing> objects) {
            if(jsonText==null){
                TextView text_empty = (TextView)findViewById(R.id.text_empty);
                text_empty.setText("An error occurred. Please retry later");
                return;
            }
            List<BookListing> books = Utilities.extractFeatureFromJson(jsonText);
            updateUI(books);
        }
    }

    private void updateUI(List<BookListing> books) {
        ListView listView =(ListView)findViewById(R.id.list);

        if (books==null){
            listView.setAdapter(null);
            TextView text_empty = (TextView)findViewById(R.id.text_empty);
            text_empty.setText("Nothing found. Try another keyword and hit the Go button");
            return;
        }else {
            listView.setAdapter(new BookItemAdapter(books, MainActivity.this));
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
