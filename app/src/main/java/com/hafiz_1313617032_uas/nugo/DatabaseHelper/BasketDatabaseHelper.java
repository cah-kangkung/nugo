package com.hafiz_1313617032_uas.nugo.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.hafiz_1313617032_uas.nugo.DatabaseContract.BasketContract.BasketEntry;
import com.hafiz_1313617032_uas.nugo.Model.Basket.Basket;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Food;

import java.util.ArrayList;
import java.util.List;

public class BasketDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "BasketDatabaseHelper";

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

    /**
     *
     * @param food  Object food
     * @return      the row ID of the newly inserted row, or -1 if an error occurred
     */
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

    /**
     *
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param sortOrder     The sort order
     * @return              Basket object, return null if there are no item that match the parameter
     */
    public Basket readBasketItem(String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                BasketEntry.TABLE_NAME, // The table to query
                null,            // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        Log.d(TAG, "readBasketItem: Cursor = " + cursor);
        Log.d(TAG, "readBasketItem: Cursor Count = " + cursor.getCount());

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
        } else {
            return null;
        }

        Basket basket = new Basket();
        basket.setFood_name(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_NAME)));
        basket.setFood_energy(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_ENERGY)));
        basket.setFood_protein(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_PROTEIN)));
        basket.setFood_fat(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_FAT)));
        basket.setFood_carbo(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_CARBO)));
        basket.setFood_image(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_IMAGE)));
        basket.setFood_count(cursor.getInt(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_COUNT)));

        cursor.close();
        return basket;
    }

    /**
     *
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param sortOrder     The sort order
     * @return              List<Basket>, if there are no item that match the parameter, return null
     */
    public List<Basket> readAllBasketItems(String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                BasketEntry.TABLE_NAME, // The table to query
                null,            // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Basket> basketItems = new ArrayList<>();
        if (cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                Basket basket = new Basket();
                basket.setFood_name(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_NAME)));
                basket.setFood_energy(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_ENERGY)));
                basket.setFood_protein(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_PROTEIN)));
                basket.setFood_fat(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_FAT)));
                basket.setFood_carbo(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_CARBO)));
                basket.setFood_image(cursor.getString(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_IMAGE)));
                basket.setFood_count(cursor.getInt(cursor.getColumnIndex(BasketEntry.COLUMN_NAME_FOOD_COUNT)));
                basketItems.add(basket);
            }
            cursor.close();
        } else {
            return null;
        }

        return basketItems;
    }

    /**
     *
     * @param values        a map from column names to new column values. null is a valid value that will be translated to NULL.
     * @param whereClause   the optional WHERE clause to apply when updating. Passing null will update all rows.
     * @param whereArgs     You may include ?s in the where clause, which will be replaced by the values from whereArgs. The values will be bound as Strings.
     * @return              the number of rows affected
     */
    public int updateBasketItem(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();

        int count = db.update(
                BasketEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs);

        return count;
    }

    /**
     *
     * @param selection     the optional WHERE clause to apply when deleting. Passing null will delete all rows.
     * @param selectionArgs You may include ?s in the where clause, which will be replaced by the values from whereArgs. The values will be bound as Strings.
     * @return
     */
    public int deleteBasketItem(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();

        // Issue SQL statement.
        int deletedRows = db.delete(BasketEntry.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }


}
