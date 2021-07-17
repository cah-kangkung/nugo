package com.hafiz_1313617032_uas.nugo.DatabaseContract;

import android.provider.BaseColumns;

public class BasketContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BasketContract() {}

    /* Inner class that defines the table contents */
    public static class Basket implements BaseColumns {
        public static final String TABLE_NAME = "basket";
        public static final String COLUMN_NAME_FOOD_NAME = "food_name";
        public static final String COLUMN_NAME_FOOD_ENERGY = "food_energy";
        public static final String COLUMN_NAME_FOOD_PROTEIN = "food_protein";
        public static final String COLUMN_NAME_FOOD_FAT = "food_fat";
        public static final String COLUMN_NAME_FOOD_CARBO = "food_carbo";
        public static final String COLUMN_NAME_FOOD_IMAGE = "food_image";
    }
}
