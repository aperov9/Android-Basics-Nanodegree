package com.example.android.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.android.inventoryapp.data.InventoryContract.CONTENT_AUTHORITY;
import static com.example.android.inventoryapp.data.InventoryContract.ItemEntry;
import static com.example.android.inventoryapp.data.InventoryContract.PATH_ITEMS;

public class InventoryProvider extends ContentProvider {

    public static final String LOG_TAG = InventoryProvider.class.getSimpleName();

    private InventoryDBHelper inventoryDBHelper;

    private static final int ITEMS = 100; // whole table
    private static final int ITEM_ID = 101; //single item

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_ITEMS, ITEMS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_ITEMS + "/#",ITEM_ID);
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return ItemEntry.CONTENT_LIST_TYPE;
            case ITEM_ID:
                return ItemEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Override
    public boolean onCreate() {
        inventoryDBHelper = new InventoryDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = inventoryDBHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case ITEMS:
                cursor = database.query(ItemEntry.TABLE_NAME, projection, selection, selectionArgs,null,null,sortOrder);
                break;
            case ITEM_ID:
                selection = ItemEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ItemEntry.TABLE_NAME, projection, selection, selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown uri " + uri);
        }

        //set notification uri on cursor
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return insertItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values) {
        // Check that values are not null and valid
        String name = values.getAsString(ItemEntry.COLUMN_ITEM_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Item requires a name");
        }
        Integer price = values.getAsInteger(ItemEntry.COLUMN_ITEM_PRICE);
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Item requires valid price");
        }
        Integer quanitity = values.getAsInteger(ItemEntry.COLUMN_ITEM_QUANTITY);
        if (quanitity == null || quanitity < 0){
            throw new IllegalArgumentException("Item requires a valid quanitity");
        }
        Integer picture = values.getAsInteger(ItemEntry.COLUMN_ITEM_PIC);
        if (picture == null || picture < 0 || picture > ItemEntry.PICTURE_ARRAY.length){
            throw new IllegalArgumentException("Item requires a valid picture");
        }
        byte[] bmpByteArray = values.getAsByteArray(ItemEntry.COLUMN_ITEM_BMP);
        if (bmpByteArray == null ){
            throw new IllegalArgumentException("Item requires a valid bmp");
        }

        SQLiteDatabase database = inventoryDBHelper.getWritableDatabase();

        long id = database.insert(ItemEntry.TABLE_NAME,null,values);

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify listeners that the data has changed fot pet content URI
        getContext().getContentResolver().notifyChange(uri,null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = inventoryDBHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                rowsDeleted = database.delete(ItemEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;

            case ITEM_ID:
                // Delete a single row given by the ID in the URI
                selection = ItemEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsDeleted = database.delete(ItemEntry.TABLE_NAME, selection, selectionArgs);

                //notify to reload since data is changed at that uri
                if (rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case ITEM_ID:
                selection = ItemEntry.COLUMN_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }

    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.size() == 0) {
            return 0;
        }

        // Check if inputs are valid
        if(values.containsKey(ItemEntry.COLUMN_ITEM_NAME)){
            String name = values.getAsString(ItemEntry.COLUMN_ITEM_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Item requires a name");
            }
        }

        if(values.containsKey(ItemEntry.COLUMN_ITEM_PRICE)){
            Integer price = values.getAsInteger(ItemEntry.COLUMN_ITEM_NAME);
            if (price == null || price>0) {
                throw new IllegalArgumentException("Item requires valid price");
            }
        }
        if(values.containsKey(ItemEntry.COLUMN_ITEM_QUANTITY)){
            Integer quantity = values.getAsInteger(ItemEntry.COLUMN_ITEM_QUANTITY);
            if (quantity == null || quantity < 0){
                throw new IllegalArgumentException("Item requires a valid quanitity");
            }
        }

        if(values.containsKey(ItemEntry.COLUMN_ITEM_PIC)){
            Integer picture = values.getAsInteger(ItemEntry.COLUMN_ITEM_PIC);
            if (picture == null || picture < 0 || picture > ItemEntry.PICTURE_ARRAY.length){
                throw new IllegalArgumentException("Item requires a valid picture");
            }
        }

        SQLiteDatabase database = inventoryDBHelper.getWritableDatabase();

        int rowsUpdated = database.update(ItemEntry.TABLE_NAME, values, selection, selectionArgs);

        //notify to reload since data is changed at that uri
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
