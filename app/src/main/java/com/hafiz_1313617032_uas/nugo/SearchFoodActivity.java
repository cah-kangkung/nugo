package com.hafiz_1313617032_uas.nugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Adapter.FoodAdapter;
import com.hafiz_1313617032_uas.nugo.Adapter.RecipeAdapter;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Food;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Hint;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFoodActivity extends AppCompatActivity implements FoodAdapter.OnFoodListener {

    private static final String TAG = "SearchFoodActivity";
    private SearchFoodActivity searchFoodActivity;

    private ApiInterface apiInterface;

    // layout variable
    private TextView tvPageName;
    private ImageView ivSearchButton01, ivSearchButton02;
    private AutoCompleteTextView actvSearchFood;

    // recycler view
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private FoodAdapter foodAdapter;

    List<Hint> listSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        initVariable();

        // get food name from home fragment
        String ingr = getIntent().getStringExtra("FoodName");
        Log.d(TAG, "onCreate: " + ingr);
        searchFood(ingr);

        addOnListener();
    }

    private void initVariable() {

        // initieate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // initiate layout variable
        tvPageName = findViewById(R.id.textView7);
        ivSearchButton01 = findViewById(R.id.iv_search_button01);
        ivSearchButton02 = findViewById(R.id.iv_search_button02);
        actvSearchFood = findViewById(R.id.actv_search_food);

        // initiate recyclerview
        recyclerView = findViewById(R.id.list_food);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        searchFoodActivity = this;
    }

    private void addOnListener() {
        // listen button click
        ivSearchButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvSearchFood.setVisibility(View.VISIBLE);
                ivSearchButton02.setVisibility(View.VISIBLE);
                ivSearchButton01.setVisibility(View.GONE);
                tvPageName.setVisibility(View.GONE);
            }
        });

        // listen to text change on actv for auto complete
        actvSearchFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAutoComplete(s.toString(), 1000);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // listen to autocomplete click and search for the item
        actvSearchFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: Item = " + actvSearchFood.getText().toString());
                Intent intent = new Intent(searchFoodActivity, SearchFoodActivity.class);
                intent.putExtra("FoodName", actvSearchFood.getText().toString());
                startActivity(intent);
            }
        });

        // listen to food search button click
        ivSearchButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + actvSearchFood.getText());
                actvSearchFood.onEditorAction(EditorInfo.IME_ACTION_DONE);
                searchFood(actvSearchFood.getText().toString());
            }
        });
    }

    private void searchFood(String ingr) {
        Call<FoodNutrition> call = apiInterface.getFoodNutrition(ingr);
        call.enqueue(new Callback<FoodNutrition>() {
            @Override
            public void onResponse(Call<FoodNutrition> call, Response<FoodNutrition> response) {
                Log.d(TAG, "onResponse: getFoodNutrition = " + response.body().getText());
                FoodNutrition responsBody = response.body();
                listSearchResult = responsBody.getHints();

                foodAdapter = new FoodAdapter(listSearchResult, searchFoodActivity);
                recyclerView.setAdapter(foodAdapter);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FoodNutrition> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }

    public void getAutoComplete(String q, int limit) {
        Call<ArrayList<String>> call = apiInterface.getAutoComplete(q, limit);
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d(TAG, "onResponse: Response Body = " + response.body());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(searchFoodActivity, android.R.layout.simple_list_item_1, response.body());
                actvSearchFood.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }

    public void click(View v) {
        switch(v.getId()) {
            case R.id.back_button:
                Log.d(TAG, "click: Top Back Button");
                finish();
                break;
        }
    }

    @Override
    public void onFoodClick(int position) {
        Intent intent = new Intent(this, FoodNutritionActivity.class);
        intent.putExtra("FoodInfo", listSearchResult.get(position).getFood());
        startActivity(intent);
    }
}