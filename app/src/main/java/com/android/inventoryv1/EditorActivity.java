package com.android.inventoryv1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.inventoryv1.data.InventoryContract;
import com.android.inventoryv1.data.InventoryDBHelper;

public class EditorActivity extends AppCompatActivity {

    public static final String LOG_TAG = EditorActivity.class.getSimpleName();

    EditText mProductEditText;
    EditText mProductPriceEditText;
    EditText mProductQuantityEditText;
    EditText mSupplierNameEditText;
    EditText mSupplierPhoneEditText;

    // Find all relevant views that we will need to read user input from


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        Log.e(LOG_TAG, "On Create");

        mProductEditText = (EditText) findViewById(R.id.product_name);
        mProductPriceEditText = (EditText) findViewById(R.id.product_price);
        mProductQuantityEditText = (EditText) findViewById(R.id.quantity);
        mSupplierNameEditText = findViewById(R.id.supplier_name);
        mSupplierPhoneEditText = findViewById(R.id.supplier_phone);
    }

    @Override
    protected void onStart() {
        Log.e(LOG_TAG, "On Start");
        insertInventory();
        super.onStart();
    }

    private void insertInventory() {
        Log.e(LOG_TAG, "Insert Inventory Method");

        String productString = mProductEditText.toString().trim();
        String productPriceString = mProductPriceEditText.toString().trim();
        String productQuantityString = mProductQuantityEditText.toString().trim();
        String supplierNameString = mSupplierNameEditText.toString().trim();
        String supplierPhoneString = mSupplierPhoneEditText.toString().trim();

        //int weight = Integer.parseInt(weightString);

        InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(this);
        SQLiteDatabase sqLiteDatabase = inventoryDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.Entry.COLUMN_PRODUCT_NAME, productString);
        contentValues.put(String.valueOf(InventoryContract.Entry.COLUMN_PRICE), productPriceString);
        contentValues.put(String.valueOf(InventoryContract.Entry.COLUMN_QUANTITY), productQuantityString);
        contentValues.put(InventoryContract.Entry.COLUMN_SUPPLIER_NAME, supplierNameString);
        contentValues.put(InventoryContract.Entry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);

        long newRow  =  sqLiteDatabase.insert(InventoryContract.Entry.PRODUCT_TABLE_NAME,null, contentValues );

        if(newRow == -1) {
            Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New Inventory Data Saved", Toast.LENGTH_SHORT).show();
        }


    }

}
