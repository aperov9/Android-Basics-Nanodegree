package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        String pricetext =createOrderSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"justjava@gmail.com"});
        intent.putExtra(intent.EXTRA_TEXT, pricetext);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private String createOrderSummary(){
        String s;
        String a= "";
        String b= "";
        int price =calculatePrice();
        s = "Name: "+getUsername();
                s+="\nQuantity: " + quantity;

        if(withCream()) {
            a ="\nWith Whipped Cream";
            s+=a;}

        if(withChocolate()) {
            b ="\nWith Chocolate";
            s+=b;}

        s+="\nTotal Price: " + price + " $";
        return s;
    }

    private int calculatePrice() {
        int price = 5;
        if(withCream()){
            price += 1;
        }
        if(withChocolate()){
            price += 2;
        }

        return  quantity *price;
    }

    public void increment(View view) {
        if(quantity<100) {
            quantity++;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity>1) {
            quantity--;
        }
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private boolean withCream(){
        CheckBox ck = (CheckBox)findViewById(R.id.checkbox);
        boolean isChecked =ck.isChecked();
        return isChecked;
    }

    private boolean withChocolate(){
        CheckBox ck = (CheckBox)findViewById(R.id.checkbox2);
        boolean isChecked =ck.isChecked();
        return isChecked;
    }

    private String getUsername(){
        EditText name = (EditText)findViewById(R.id.namefield);
        String s = name.getText().toString();
        return s;
    }
}