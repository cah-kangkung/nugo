package com.hafiz_1313617032_uas.nugo.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.BasketActivity;
import com.hafiz_1313617032_uas.nugo.DatabaseHelper.BasketDatabaseHelper;
import com.hafiz_1313617032_uas.nugo.FoodNutritionActivity;
import com.hafiz_1313617032_uas.nugo.Model.Basket.Basket;
import com.hafiz_1313617032_uas.nugo.R;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;
import com.hafiz_1313617032_uas.nugo.SearchFoodActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private OnHomeFragmentListener onHomeFragmentListener;

    private AutoCompleteTextView actvSearchFood;
    private ImageView ivButtonSearch, ivButtonBasket, ivNavDrawerButton;
    private TextView tvBasketLabel;

    private ApiInterface apiInterface;

    View view;

    private int limitSearch = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        this.view = view;

        getBasketItemCount(this.view);

        ivNavDrawerButton = view.findViewById(R.id.nav_drawer_button);
        ivNavDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHomeFragmentListener.onAction();
            }
        });

        // search bar
        actvSearchFood = (AutoCompleteTextView) view.findViewById(R.id.autocomplete_search_food);
        ivButtonSearch = (ImageView) view.findViewById(R.id.iv_button_search);
        ivButtonBasket = (ImageView) view.findViewById(R.id.button_basket);
        ivButtonSearch.setVisibility(View.GONE);

        // listen to text changed
        actvSearchFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    ivButtonSearch.setVisibility(View.GONE);
                } else {
                    ivButtonSearch.setVisibility(View.VISIBLE);
                    getAutoComplete(s.toString(), limitSearch);
                }
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
                Intent intent = new Intent(getActivity(), SearchFoodActivity.class);
                intent.putExtra("FoodName", actvSearchFood.getText().toString());
                startActivity(intent);
            }
        });

        ivButtonBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click: Basket Menu Clicked");
                Intent intent = new Intent(getActivity(), BasketActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getBasketItemCount(this.view);
    }

    public void getAutoComplete(String q, int limit) {
        Call<ArrayList<String>> call = apiInterface.getAutoComplete(q, limit);
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d(TAG, "onResponse: Response Body = " + response.body());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, response.body());
                actvSearchFood.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }

    private void getBasketItemCount(View view) {
        // basket label
        BasketDatabaseHelper basketDatabaseHelper = new BasketDatabaseHelper(getActivity());
        int count = basketDatabaseHelper.getBasketItemCount();
        tvBasketLabel = view.findViewById(R.id.tv_basket_item_count);
        tvBasketLabel.setText(Integer.toString(count));
    }

    // setter getter for listener
    public OnHomeFragmentListener getOnHomeFragmentListener() {
        return onHomeFragmentListener;
    }

    public void setOnHomeFragmentListener(OnHomeFragmentListener onHomeFragmentListener) {
        this.onHomeFragmentListener = onHomeFragmentListener;
    }

    public interface OnHomeFragmentListener {
        void onAction();
    }
}