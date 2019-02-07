package com.android.inventoryv1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "store.db";
    public static final int DATABSE_VERSION = 1;


    public InventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE " + InventoryContract.Entry.PRODUCT_TABLE_NAME + " (" +
                InventoryContract.Entry._ID + " INTEGER PRIMARY KEY, " +
                InventoryContract.Entry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                InventoryContract.Entry.COLUMN_PRICE + " DOUBLE, " +
                InventoryContract.Entry.COLUMN_QUANTITY + " INTEGER, " +
                InventoryContract.Entry.COLUMN_SUPPLIER_NAME + " TEXT, " +
                InventoryContract.Entry.COLUMN_SUPPLIER_PHONE + " TEXT, " +
                " UNIQUE (" + InventoryContract.Entry.COLUMN_PRODUCT_NAME + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + InventoryContract.Entry.PRODUCT_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
