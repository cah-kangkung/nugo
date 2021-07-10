package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodNutritionActivity extends AppCompatActivity {
    private static final String TAG = "FoodNutritionActivity";

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_nutrition);

        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        String ingr = getIntent().getStringExtra("FoodName");

        getFoodNutrition(ingr);
    }

    public void getFoodNutrition(String ingr) {
        Call<FoodNutrition> call = apiInterface.getFoodNutrition(ingr);
        call.enqueue(new Callback<FoodNutrition>() {
            @Override
            public void onResponse(Call<FoodNutrition> call, Response<FoodNutrition> response) {
                Log.d(TAG, "onResponse: getFoodNutrition = " + response.body().getText());
            }

            @Override
            public void onFailure(Call<FoodNutrition> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }
}