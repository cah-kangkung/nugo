package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Adapter.HintAdapter;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Hint;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodNutritionActivity extends AppCompatActivity implements HintAdapter.OnHintListener {
    private static final String TAG = "FoodNutritionActivity";

    private ApiInterface apiInterface;

    // Layout Variable
    private TextView tvFoodName, tvFoodEnergy, tvFoodProtein, tvFoodFat, tvFoodCarbs, tvFoodCategory;
    private ImageView ivFoodImage;

    // recycler view
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HintAdapter hintAdapter;
    public static FoodNutritionActivity foodNutritionActivity;

    private List<Hint> listFoodHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_nutrition);

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

        recyclerView = findViewById(R.id.hint_food_list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        foodNutritionActivity = this;


        // get food name from home fragment
        String ingr = getIntent().getStringExtra("FoodName");

        getFoodNutrition(ingr);
    }

    public void getFoodNutrition(String ingr) {
        Call<FoodNutrition> call = apiInterface.getFoodNutrition(ingr);
        call.enqueue(new Callback<FoodNutrition>() {
            @Override
            public void onResponse(Call<FoodNutrition> call, Response<FoodNutrition> response) {
                Log.d(TAG, "onResponse: getFoodNutrition = " + response.body().getText());
                FoodNutrition responsBody = response.body();
                Picasso.get().load(responsBody.getParsed().get(0).getFood().getImage()).into(ivFoodImage);
                tvFoodName.setText(responsBody.getParsed().get(0).getFood().getLabel());
                tvFoodEnergy.setText(responsBody.getParsed().get(0).getFood().getNutrients().getEnercKcal().intValue() + "kcal");
                tvFoodProtein.setText(Double.toString(responsBody.getParsed().get(0).getFood().getNutrients().getProcnt())  + "g");
                tvFoodFat.setText(Double.toString(responsBody.getParsed().get(0).getFood().getNutrients().getFat()) + "g");
                tvFoodCarbs.setText(Double.toString(responsBody.getParsed().get(0).getFood().getNutrients().getChocdf()) + "g");
                tvFoodCategory.setText(responsBody.getParsed().get(0).getFood().getCategory());

                listFoodHint = responsBody.getHints();
                hintAdapter = new HintAdapter(listFoodHint, foodNutritionActivity);
                recyclerView.setAdapter(hintAdapter);
            }

            @Override
            public void onFailure(Call<FoodNutrition> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }

    @Override
    public void onHintClick(int position) {
        Intent intent = new Intent(this, FoodNutritionActivity.class);
        intent.putExtra("FoodName", listFoodHint.get(position).getFood().getLabel());
        startActivity(intent);
    }
}