package com.android.inventoryv1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.inventoryv1.data.InventoryContract;
import com.android.inventoryv1.data.InventoryDBHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    InventoryDBHelper inventoryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(LOG_TAG, "On Create Method here");
    }

    @Override
    protected void onStart() {
        Log.e(LOG_TAG, "On Start");
        insertInventory();
        displayDatabaseInfo();
        super.onStart();
    }

    private void insertInventory() {
        ContentValues values = new ContentValues();
        values.put(InventoryContract.Entry.COLUMN_PRODUCT_NAME, "Headphone");
        values.put(String.valueOf(InventoryContract.Entry.COLUMN_PRICE), 24.00);
        values.put(String.valueOf(InventoryContract.Entry.COLUMN_QUANTITY), 32);
        values.put(InventoryContract.Entry.COLUMN_SUPPLIER_NAME, "Bose");
        values.put(InventoryContract.Entry.COLUMN_SUPPLIER_PHONE, 800-343-5689);

        Uri uri = getContentResolver().insert(InventoryContract.BASE_CONTENT_URI, values);
    }

    private void displayDatabaseInfo() {
        Log.e(LOG_TAG, "displayDatabaseInfo Method");
        SQLiteDatabase sqLiteDatabase = inventoryDBHelper.getReadableDatabase();

        String [] projection = {
                InventoryContract.Entry._ID,
                InventoryContract.Entry.COLUMN_PRODUCT_NAME,
                String.valueOf(InventoryContract.Entry.COLUMN_PRICE),
                String.valueOf(InventoryContract.Entry.COLUMN_QUANTITY),
                InventoryContract.Entry.COLUMN_SUPPLIER_NAME,
                InventoryContract.Entry.COLUMN_SUPPLIER_PHONE};

        Cursor cursor = sqLiteDatabase.query(
                InventoryContract.Entry.COLUMN_PRODUCT_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = findViewById(R.id.inventory_list);
        try {
            displayView.setText("This product is in the database = " + cursor.getCount() + "product.\n\n");
            displayView.append(InventoryContract.Entry._ID + " - " +
                    InventoryContract.Entry.COLUMN_PRODUCT_NAME + " - " +
                    InventoryContract.Entry.COLUMN_PRICE + " - " +
                    InventoryContract.Entry.COLUMN_QUANTITY + " - " +
                    InventoryContract.Entry.COLUMN_SUPPLIER_NAME + " - " +
                    InventoryContract.Entry.COLUMN_SUPPLIER_PHONE + "\n");
            int idColumnIndex = cursor.getColumnIndex(InventoryContract.Entry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_PRODUCT_NAME);
            int productPriceColumnIndex = cursor.getColumnIndex(String.valueOf(InventoryContract.Entry.COLUMN_PRICE));
            int quantityColumnIndex = cursor.getColumnIndex(String.valueOf(InventoryContract.Entry.COLUMN_QUANTITY));
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(productNameColumnIndex);
                double currentPrice = cursor.getDouble(productPriceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                int currentSupplierName = cursor.getInt(supplierNameColumnIndex);
                int currentSupplierPhone = cursor.getInt(supplierPhoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
