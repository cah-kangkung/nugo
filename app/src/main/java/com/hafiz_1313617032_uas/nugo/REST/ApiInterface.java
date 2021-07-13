package com.hafiz_1313617032_uas.nugo.REST;

import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.DailyRecipe;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String FOOD_APP_ID = "fbe30dfa";
    String FOOD_APP_KEY = "551eb5cc7a7be157b61fc5fd4e3bdb9c";

    String RECIPE_APP_ID = "8aaf208d";
    String RECIPE_APP_KEY = " 180dfb6735b449f191e955f85864c7dc";

    // endpoint autocomplete
    @GET("/auto-complete?app_id=" + FOOD_APP_ID + "&app_key=" + FOOD_APP_KEY + "&")
    Call<ArrayList<String>> getAutoComplete(
        @Query("q") String q,
        @Query("limit") int limit
    );

    // endpoint food nutrition data
    @GET("/api/food-database/v2/parser?app_id=" + FOOD_APP_ID + "&app_key=" + FOOD_APP_KEY + "&nutrition-type=cooking")
    Call<FoodNutrition> getFoodNutrition(@Query("ingr") String ingr);

    // endpoint daily recipes
    @GET("/api/recipes/v2?type=public&app_id=" + RECIPE_APP_ID + "&app_key=" + RECIPE_APP_KEY + "&ingr=1%2B")
    Call<DailyRecipe> getDailyRecipe();
}
