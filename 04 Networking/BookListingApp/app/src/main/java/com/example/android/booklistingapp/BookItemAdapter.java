package com.example.android.booklistingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookItemAdapter extends BaseAdapter {

    List<BookListing> bookList;
    Context cx;

    public BookItemAdapter(List<BookListing> bookList, Context cx) {
        this.bookList = bookList;
        this.cx= cx;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= convertView.inflate(cx, R.layout.book_item,null);

        TextView book_title = (TextView)convertView.findViewById(R.id.book_title);
        TextView book_author = (TextView)convertView.findViewById(R.id.book_author);
        TextView book_puplisher = (TextView)convertView.findViewById(R.id.book_puplisher);

        book_title.setText(bookList.get(position).getmName());
        book_author.setText(bookList.get(position).getmAuthor());
        book_puplisher.setText(bookList.get(position).getmPublisher());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookList.get(position).getmLink().equals("NOT AVAILABLE")){
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(cx);
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Currently not available");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();

                }else {
                    Uri uri = Uri.parse(bookList.get(position).getmLink()); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    cx.startActivity(intent);
                }
            }
        });

        return convertView;
    }
}
