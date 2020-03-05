package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;

public final class InventoryContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ITEMS = "items";

    public static class ItemEntry{

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ITEMS;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ITEMS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ITEMS);

        public static String TABLE_NAME = "items";
        public static String COLUMN_ID = "_id";
        public static String COLUMN_ITEM_NAME = "name";
        public static String COLUMN_ITEM_PRICE = "price";
        public static String COLUMN_ITEM_QUANTITY = "quantity";
        public static String COLUMN_ITEM_PIC = "picture";
        public static String COLUMN_ITEM_BMP = "bmps";


        public static String PICTURE_FOOD = "Food";
        public static String PICTURE_BEVERAGE = "Beverage";
        public static String PICTURE_TOOL = "Tool";
        public static String PICTURE_OTHER = "Other";

        public static String[] PICTURE_ARRAY = {PICTURE_FOOD,PICTURE_BEVERAGE,PICTURE_TOOL,PICTURE_OTHER};
    }
}
