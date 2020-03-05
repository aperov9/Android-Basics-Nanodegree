package com.example.android.inventoryapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.DetailActivity;
import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.data.InventoryContract.ItemEntry;

public class ItemAdapter extends CursorAdapter {

    Context context;

    public ItemAdapter(Context context, Cursor c) {
        super(context, c, 0 /*flags*/);
        this.context=context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView nameField = (TextView)view.findViewById(R.id.item_name);
        TextView priceField = (TextView)view.findViewById(R.id.item_price);
        final TextView quantityField = (TextView)view.findViewById(R.id.item_quantity);
        Button buyButton = (Button)view.findViewById(R.id.button_buy);

        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY);
        int idColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ID);

        String name = cursor.getString(nameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);
        final String id = cursor.getString(idColumnIndex);


        nameField.setText(name);
        priceField.setText("Price: "+price+" $");
        quantityField.setText("Quantity: "+quantity);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>0) {
                    ContentValues values = new ContentValues();
                    values.put(ItemEntry.COLUMN_ITEM_QUANTITY, (quantity - 1));
                    context.getContentResolver().update(ItemEntry.CONTENT_URI, values, "_id=?", new String[]{id});
                }else {
                    Toast.makeText(context,"You cannot buy this item currenty",Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("itemId",id);
                context.startActivity(intent);
            }
        });
    }
}
