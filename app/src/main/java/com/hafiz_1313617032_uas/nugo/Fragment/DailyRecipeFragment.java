package com.hafiz_1313617032_uas.nugo.Fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Adapter.HintAdapter;
import com.hafiz_1313617032_uas.nugo.Adapter.RecipeAdapter;
import com.hafiz_1313617032_uas.nugo.FoodNutritionActivity;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.DailyRecipe;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Hit;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Recipe;
import com.hafiz_1313617032_uas.nugo.R;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyRecipeFragment extends Fragment {
    private static final String TAG = "DailyRecipeFragment";

    private ApiInterface apiInterface;

    // layout variable
    private TextView tvPageName;
    private ImageView ivSearchButton01, ivSearchButton02;
    private EditText etSearchRecipe;

    // recycler view
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;

    private List<Hit> listRecipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initiated layout variable
        tvPageName = view.findViewById(R.id.textView7);
        ivSearchButton01 = view.findViewById(R.id.iv_search_button01);
        ivSearchButton02 = view.findViewById(R.id.iv_search_button02);
        etSearchRecipe = view.findViewById(R.id.et_search_recipe);

        // listen to text changed
        ivSearchButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearchRecipe.setVisibility(View.VISIBLE);
                ivSearchButton02.setVisibility(View.VISIBLE);
                ivSearchButton01.setVisibility(View.GONE);
                tvPageName.setVisibility(View.GONE);
            }
        });

        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // initiate recyclerview
        recyclerView = view.findViewById(R.id.rv_recipe);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        getDailyRecipe();
    }

    private void getDailyRecipe() {
        Call<DailyRecipe> call = apiInterface.getDailyRecipe();
        call.enqueue(new Callback<DailyRecipe>() {
            @Override
            public void onResponse(Call<DailyRecipe> call, Response<DailyRecipe> response) {
                listRecipe = response.body().getHits();

                recipeAdapter = new RecipeAdapter(listRecipe);
                recyclerView.setAdapter(recipeAdapter);
            }

            @Override
            public void onFailure(Call<DailyRecipe> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}