package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Food;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Hint;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodNutritionActivity extends AppCompatActivity {
    private static final String TAG = "FoodNutritionActivity";

    public static FoodNutritionActivity foodNutritionActivity;

    private ApiInterface apiInterface;

    // Layout Variable
    private TextView tvFoodName, tvFoodEnergy, tvFoodProtein, tvFoodFat, tvFoodCarbs, tvFoodCategory;
    private ImageView ivFoodImage;

    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_nutrition);

        initVariable();

        // get food data
        food = getIntent().getParcelableExtra("FoodInfo");

        Log.d(TAG, "label: " + food.getLabel());

        Picasso.get().load(food.getImage()).error(R.drawable.baseline_restaurant_menu_black_24dp).into(ivFoodImage);
        tvFoodName.setText(food.getLabel());
        tvFoodEnergy.setText(food.getNutrients().getEnercKcal().intValue() + "kcal");
        tvFoodProtein.setText((food.getNutrients().getProcnt().intValue())  + "g");
        tvFoodFat.setText((food.getNutrients().getFat().intValue()) + "g");
        tvFoodCarbs.setText((food.getNutrients().getChocdf().intValue()) + "g");
        tvFoodCategory.setText(food.getCategory());
    }

    private void initVariable() {
        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // initiate layout variable
        tvFoodName = findViewById(R.id.tv_food_name);
        tvFoodEnergy = findViewById(R.id.tv_food_energy);
        tvFoodProtein = findViewById(R.id.tv_food_protein);
        tvFoodFat = findViewById(R.id.tv_food_fat);
        tvFoodCarbs = findViewById(R.id.tv_food_carbs);
        tvFoodCategory = findViewById(R.id.tv_food_category);
        ivFoodImage = (ImageView) findViewById(R.id.iv_food_image);

        foodNutritionActivity = this;
    }


    public void click(View v) {
        switch(v.getId()) {
            case R.id.back_button:
                Log.d(TAG, "click: Top back button");
                finish();
        }
    }
}