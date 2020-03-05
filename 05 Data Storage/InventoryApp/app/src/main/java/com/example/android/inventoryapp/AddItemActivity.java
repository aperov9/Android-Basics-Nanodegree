package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddItemActivity extends AppCompatActivity {

    Spinner spinner;
    private final int SELECT_PHOTO = 1;
    Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //back button + onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner)findViewById(R.id.spinner_picture);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, InventoryContract.ItemEntry.PICTURE_ARRAY);
        spinner.setAdapter(adapter);

        Button picture_button = (Button)findViewById(R.id.picture_button);


        picture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PHOTO);
            }
        });

    }

    private void insertItem() {

        EditText nameField = (EditText)findViewById(R.id.edit_item_name);
        EditText countField = (EditText)findViewById(R.id.edit_item_count);
        EditText priceField = (EditText)findViewById(R.id.edit_item_price);

        int count = 0;
        int price = 0;

        if (!priceField.getText().toString().equals("")){
            price = Integer.parseInt(priceField.getText().toString());
        }else {
            Toast.makeText(AddItemActivity.this,"Please enter a valid price",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!countField.getText().toString().equals("")){
            count = Integer.parseInt(countField.getText().toString());
        }else {
            Toast.makeText(AddItemActivity.this,"Please enter a valid count",Toast.LENGTH_SHORT).show();
            return;
        }


        String name = nameField.getText().toString().trim();
        if(!name.equals("")){
                if(bmp!=null) {
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.ItemEntry.COLUMN_ITEM_NAME, name);
                    values.put(InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY, count);
                    values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PRICE, price);
                    values.put(InventoryContract.ItemEntry.COLUMN_ITEM_BMP, bitmapToByteArray(bmp));

                    int position = spinner.getSelectedItemPosition();
                    switch (position) {
                        case 0:
                            values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PIC, 0);
                            break;
                        case 1:
                            values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PIC, 1);
                            break;
                        case 2:
                            values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PIC, 2);
                            break;
                        case 3:
                            values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PIC, 3);
                            break;
                        default:
                            values.put(InventoryContract.ItemEntry.COLUMN_ITEM_PIC, 3);
                    }

                    Uri newUri = getContentResolver().insert(InventoryContract.ItemEntry.CONTENT_URI, values);

                    // Show a toast message depending on whether or not the insertion was successful
                    if (newUri == null) {
                        // If the new content URI is null, then there was an error with insertion.
                        Toast.makeText(this, "Insert failed",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // Otherwise, the insertion was successful and we can display a toast.
                        Toast.makeText(this, "Insert sucessfull",
                                Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }else {
                    Toast.makeText(AddItemActivity.this,"Please select a picture",Toast.LENGTH_SHORT).show();
                    return;
                }

        }else {
            Toast.makeText(AddItemActivity.this,"Please enter a valid name",Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_item_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_insert:
                insertItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(AddItemActivity.this,"Failed to select picture",Toast.LENGTH_SHORT);
                return;
            }
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bmp = BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }

    // convert from bitmap to byte array
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
