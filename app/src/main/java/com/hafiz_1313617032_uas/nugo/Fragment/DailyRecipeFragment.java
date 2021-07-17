package com.hafiz_1313617032_uas.nugo.Fragment;

import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.Adapter.RecipeAdapter;
import com.hafiz_1313617032_uas.nugo.FoodNutritionActivity;
import com.hafiz_1313617032_uas.nugo.MainActivity;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.DailyRecipe;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Hit;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Recipe;
import com.hafiz_1313617032_uas.nugo.R;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;
import com.hafiz_1313617032_uas.nugo.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getSystemService;

public class DailyRecipeFragment extends Fragment implements RecipeAdapter.OnRecipeListener {
    private static final String TAG = "DailyRecipeFragment";

    private ApiInterface apiInterface;

    // layout variable
    private TextView tvPageName;
    private ImageView ivSearchButton01, ivSearchButton02;
    private EditText etSearchRecipe;
    private ProgressBar infiniteScrollLoading;

    // recycler view
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;

    // class variable
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private String nextRecipeUrl;

    private List<Hit> listRecipe;

    public static DailyRecipeFragment dailyRecipeFragment;

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
        infiniteScrollLoading = view.findViewById(R.id.infinite_loading);

        infiniteScrollLoading.setVisibility(View.GONE);

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

        // listen to recipe search button click
        ivSearchButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + etSearchRecipe.getText());
                etSearchRecipe.onEditorAction(EditorInfo.IME_ACTION_DONE);
                getDailyRecipe(etSearchRecipe.getText().toString(), "1+");
            }
        });

        // listen to enter button click
        etSearchRecipe.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            etSearchRecipe.onEditorAction(EditorInfo.IME_ACTION_DONE);
                            getDailyRecipe(etSearchRecipe.getText().toString(), "1+");
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dailyRecipeFragment = this;

        // initiate recyclerview
        recyclerView = view.findViewById(R.id.rv_recipe);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.d(TAG, "onScrolled: Last Item Wow");

                            infiniteScrollLoading.setVisibility(View.VISIBLE);

                            getDailyRecipeAppend(nextRecipeUrl);
                        }
                    }
                }
            }
        });

        getDailyRecipe("", "1+");
    }

    private void getDailyRecipe(String q, String ingr) {
        Call<DailyRecipe> call = apiInterface.getDailyRecipe(q, ingr);
        call.enqueue(new Callback<DailyRecipe>() {
            @Override
            public void onResponse(Call<DailyRecipe> call, Response<DailyRecipe> response) {
                if (response.isSuccessful() && response.body().getHits().size() != 0) {
                    listRecipe = response.body().getHits();
                    nextRecipeUrl = response.body().getLink().getNext().getHref();
                    // Log.d(TAG, "onResponse: next link = " + nextRecipeUrl);
                    recipeAdapter = new RecipeAdapter(listRecipe, dailyRecipeFragment);
                    recyclerView.setAdapter(recipeAdapter);
                    recipeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DailyRecipe> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getDailyRecipeAppend(String nextRecipe) {
        Call<DailyRecipe> call = apiInterface.getDailyRecipeNext(nextRecipe);
        call.enqueue(new Callback<DailyRecipe>() {
            @Override
            public void onResponse(Call<DailyRecipe> call, Response<DailyRecipe> response) {
                if (response.isSuccessful() && response.body().getHits().size() != 0) {
                    listRecipe.addAll(listRecipe.size()-1, response.body().getHits());
                    nextRecipeUrl = response.body().getLink().getNext().getHref();
                    Log.d(TAG, "onResponse: Next Index = " + response.body().getFrom());
                    Log.d(TAG, "onResponse: Next Url = " + nextRecipeUrl);
                    loading = true;

                    recipeAdapter.notifyItemRangeInserted(listRecipe.size()-1, listRecipe.size());

                    infiniteScrollLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DailyRecipe> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
        intent.putExtra("Recipe", listRecipe.get(position).getRecipe());
        startActivity(intent);
    }
}