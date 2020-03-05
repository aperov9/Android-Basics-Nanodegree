package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Button orderMore,add,remove;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id  = getIntent().getExtras().getString("itemId");
        getLoaderManager().initLoader(0,null,this);
    }

    private void deleteItem() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure you want to delete the following item?");
        dialog.setTitle("Warning");
        dialog.setNegativeButton("No",null);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finalDelete();
            }
        });
        dialog.show();
    }

    private void finalDelete() {
        getContentResolver().delete(InventoryContract.ItemEntry.CONTENT_URI,"_id=?",new String[]{id});
        getLoaderManager().destroyLoader(0);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteItem();
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] columns = {
                InventoryContract.ItemEntry.COLUMN_ID,
                InventoryContract.ItemEntry.COLUMN_ITEM_NAME,
                InventoryContract.ItemEntry.COLUMN_ITEM_PRICE,
                InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY,
                InventoryContract.ItemEntry.COLUMN_ITEM_PIC};

        CursorLoader cursorLoader = new CursorLoader(DetailActivity.this, InventoryContract.ItemEntry.CONTENT_URI,columns,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        updateUI();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void updateUI() {

        TextView nameField = (TextView)findViewById(R.id.item_name);
        TextView priceField = (TextView)findViewById(R.id.item_price);
        TextView quantityField = (TextView)findViewById(R.id.item_count);

        String[] columns = {
                InventoryContract.ItemEntry.COLUMN_ID,
                InventoryContract.ItemEntry.COLUMN_ITEM_NAME,
                InventoryContract.ItemEntry.COLUMN_ITEM_PRICE,
                InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY,
                InventoryContract.ItemEntry.COLUMN_ITEM_PIC,
                InventoryContract.ItemEntry.COLUMN_ITEM_BMP};

        final Cursor cursor= getContentResolver().query(InventoryContract.ItemEntry.CONTENT_URI,columns,"_id=?",new String[]{id},null);

        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_PRICE);
        final int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY);
        int picColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_PIC);
        int bmpColumnIndex = cursor.getColumnIndex(InventoryContract.ItemEntry.COLUMN_ITEM_BMP);

        cursor.moveToFirst();
        final String name = cursor.getString(nameColumnIndex);
        final int  price = cursor.getInt(priceColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);
        final int picture = cursor.getInt(picColumnIndex);
        final byte[] bitmapByteArray = cursor.getBlob(bmpColumnIndex);
        final Bitmap bitmap = getImage(bitmapByteArray);

        nameField.setText(name);
        priceField.setText(price+" $");
        quantityField.setText(quantity+"");

        ImageView image = (ImageView)findViewById(R.id.image);

        image.setImageBitmap(bitmap);

        add = (Button)findViewById(R.id.button_add);
        remove = (Button)findViewById(R.id.button_remove);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY,(cursor.getInt(quantityColumnIndex)+1));
                getContentResolver().update(InventoryContract.ItemEntry.CONTENT_URI,contentValues,"_id=?",new String[]{id});
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor.getInt(quantityColumnIndex)>0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(InventoryContract.ItemEntry.COLUMN_ITEM_QUANTITY, (cursor.getInt(quantityColumnIndex) - 1));
                    getContentResolver().update(InventoryContract.ItemEntry.CONTENT_URI, contentValues, "_id=?", new String[]{id});
                }else {
                    Toast.makeText(DetailActivity.this,"You cannot reduce the quantity to less than 0",Toast.LENGTH_SHORT);
                }
            }
        });

        orderMore = (Button)findViewById(R.id.order_more);
        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"android@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Order more");
                i.putExtra(Intent.EXTRA_TEXT   , "Please order more of the Item: "+name+". There are currently "+quantity+" available at a price of "+price);
                final PackageManager pm = getPackageManager();
                final List<ResolveInfo> matches = pm.queryIntentActivities(i, 0);
                ResolveInfo best = null;
                for (final ResolveInfo info : matches)
                    if (info.activityInfo.packageName.endsWith(".gm") ||
                            info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                if (best != null)
                    i.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                startActivity(i);
            }
        });

    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
