package com.hafiz_1313617032_uas.nugo.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.hafiz_1313617032_uas.nugo.DatabaseContract.BasketContract.Basket;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Food;

import java.util.ArrayList;
import java.util.List;

public class BasketDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Basket.TABLE_NAME + " (" +
                    Basket._ID + " INTEGER PRIMARY KEY," +
                    Basket.COLUMN_NAME_FOOD_NAME + " TEXT," +
                    Basket.COLUMN_NAME_FOOD_ENERGY + " TEXT," +
                    Basket.COLUMN_NAME_FOOD_PROTEIN + " TEXT," +
                    Basket.COLUMN_NAME_FOOD_FAT + " TEXT," +
                    Basket.COLUMN_NAME_FOOD_CARBO + " TEXT," +
                    Basket.COLUMN_NAME_FOOD_IMAGE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Basket.TABLE_NAME;

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
        values.put(Basket.COLUMN_NAME_FOOD_NAME, food.getLabel());
        values.put(Basket.COLUMN_NAME_FOOD_ENERGY, food.getNutrients().getEnercKcal());
        values.put(Basket.COLUMN_NAME_FOOD_PROTEIN, food.getNutrients().getProcnt());
        values.put(Basket.COLUMN_NAME_FOOD_FAT, food.getNutrients().getFat());
        values.put(Basket.COLUMN_NAME_FOOD_CARBO, food.getNutrients().getChocdf());
        values.put(Basket.COLUMN_NAME_FOOD_IMAGE, food.getImage());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Basket.TABLE_NAME, null, values);
        return newRowId;
    }

    /**
     *
     * @param projection The array of columns to return (pass null to get all)
     * @param selection The columns for the WHERE clause
     * @param selectionArgs The values for the WHERE clause
     * @param sortOrder The sort order
     * @return
     */
    public List<Food> readBasketItem(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                Basket.TABLE_NAME,      // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Food> food = new ArrayList<>();


        return food;
    }
}
