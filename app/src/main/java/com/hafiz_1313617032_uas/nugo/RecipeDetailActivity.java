package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String TAG = "RecipeDetailActivity";

    // layout variable
    private ImageView ivRecipDetailimage, ivBackButton;
    private TextView tvRecipeDetailName, tvRecipeDetailCookingTime, tvRecipeDetailYield, tvRecipeDetailCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Recipe recipe = getIntent().getParcelableExtra("Recipe");

        ivRecipDetailimage = findViewById(R.id.iv_recipe_detail_image);
        ivBackButton = findViewById(R.id.back_button);
        tvRecipeDetailName = findViewById(R.id.tv_recipe_detail_name);
        tvRecipeDetailCookingTime =findViewById(R.id.tv_recipe_detail_cookingTime);
        tvRecipeDetailYield = findViewById(R.id.tv_recipe_detail_cookingYield);
        tvRecipeDetailCalories = findViewById(R.id.tv_recipe_detail_calories);

        Picasso.get().load(recipe.getImage()).into(ivRecipDetailimage);
        tvRecipeDetailName.setText(recipe.getLabel());
        tvRecipeDetailCookingTime.setText(Integer.toString(recipe.getTotalTime()));
        tvRecipeDetailYield.setText(Integer.toString(recipe.getYield()));
        tvRecipeDetailCalories.setText(Integer.toString(recipe.getCalories().intValue()));

    }

    public void click(View v) {
        switch(v.getId()) {
            case R.id.back_button:
                Log.d(TAG, "click: Top back button");
                finish();
        }
    }
}