package com.hafiz_1313617032_uas.nugo.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.hafiz_1313617032_uas.nugo.DatabaseContract.BasketContract.BasketEntry;
import com.hafiz_1313617032_uas.nugo.Model.Basket.Basket;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Food;

import java.util.ArrayList;
import java.util.List;

public class BasketDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BasketEntry.TABLE_NAME + " (" +
                    BasketEntry._ID + " INTEGER PRIMARY KEY," +
                    BasketEntry.COLUMN_NAME_FOOD_NAME + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_ENERGY + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_PROTEIN + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_FAT + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_CARBO + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_IMAGE + " TEXT," +
                    BasketEntry.COLUMN_NAME_FOOD_COUNT + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BasketEntry.TABLE_NAME;

    public BasketDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long createBasketItem(Food food) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BasketEntry.COLUMN_NAME_FOOD_NAME, food.getLabel());
        values.put(BasketEntry.COLUMN_NAME_FOOD_ENERGY, food.getNutrients().getEnercKcal());
        values.put(BasketEntry.COLUMN_NAME_FOOD_PROTEIN, food.getNutrients().getProcnt());
        values.put(BasketEntry.COLUMN_NAME_FOOD_FAT, food.getNutrients().getFat());
        values.put(BasketEntry.COLUMN_NAME_FOOD_CARBO, food.getNutrients().getChocdf());
        values.put(BasketEntry.COLUMN_NAME_FOOD_IMAGE, food.getImage());
        values.put(BasketEntry.COLUMN_NAME_FOOD_COUNT, 1);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(BasketEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    public Basket readBasketItem(String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                BasketEntry.TABLE_NAME,      // The table to query
                columns,                // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        
    }

    /**
     *
     * @param columns       The array of columns to return (pass null to get all)
     * @param selection     The columns for the WHERE clause
     * @param selectionArgs The values for the WHERE clause
     * @param sortOrder     The sort order
     * @return
     */
    public List<Basket> readAllBasketItems(String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                BasketEntry.TABLE_NAME,      // The table to query
                columns,                // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Basket> basketItems = new ArrayList<>();
        while(cursor.moveToNext()) {
            Basket basket = new Basket();
            basketItems.add(basket);
        }
        return basketItems;
    }

    /**
     *
     * @param selection     Define 'where' part of query.
     * @param selectionArgs Specify arguments in placeholder order.
     * @return
     */
    public int deleteBasketItem(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();

        // Issue SQL statement.
        int deletedRows = db.delete(BasketEntry.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }
}
