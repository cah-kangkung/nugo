package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hafiz_1313617032_uas.nugo.DatabaseHelper.BasketDatabaseHelper;
import com.hafiz_1313617032_uas.nugo.Model.Basket.Basket;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private static final String TAG = "BasketActivity";

    private BasketDatabaseHelper basketDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        basketDatabaseHelper = new BasketDatabaseHelper(this);

        List<Basket> basketItems = basketDatabaseHelper.readAllBasketItems(null, null, null);
        Log.d(TAG, "onViewCreated: " + basketItems);
    }
}