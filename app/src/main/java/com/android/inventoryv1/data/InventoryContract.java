package com.android.inventoryv1.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract  {

    public static final String CONTENT_AUTHORITY = "com.android.inventoryv1";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static class Entry implements BaseColumns {

        public static final String COLUMN_PRODUCT_NAME= "product_name";
        public static final double COLUMN_PRICE= 0.0;
        public static final int COLUMN_QUANTITY= 0;
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE= "supplier_phone_number";

        //task type table name
        public static final String PRODUCT_TABLE_NAME = "PRODUCTS";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "inventory");
    }

    final String CREATE_TABLE = "CREATE_TABLE " + Entry.PRODUCT_TABLE_NAME + " (" +
            InventoryContract.Entry._ID + " INTEGER PRIMARY KEY, " +
            Entry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
            Entry.COLUMN_PRICE + " DOUBLE, " +
            Entry.COLUMN_QUANTITY + " INTEGER, " +
            Entry.COLUMN_SUPPLIER_NAME + " TEXT, " +
            Entry.COLUMN_SUPPLIER_PHONE + " TEXT, " +
            " UNIQUE (" + Entry.COLUMN_PRODUCT_NAME + ") ON CONFLICT REPLACE);";




}
