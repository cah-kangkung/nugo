package com.hafiz_1313617032_uas.nugo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.hafiz_1313617032_uas.nugo.FoodNutritionActivity;
import com.hafiz_1313617032_uas.nugo.MainActivity;
import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.FoodNutrition;
import com.hafiz_1313617032_uas.nugo.R;
import com.hafiz_1313617032_uas.nugo.REST.ApiClient;
import com.hafiz_1313617032_uas.nugo.REST.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    
    private AutoCompleteTextView actvSearchFood;
    private ImageView ivButtonSearch;
    private ApiInterface apiInterface;

    private int limitSearch = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initiate apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // search bar
        actvSearchFood = (AutoCompleteTextView) view.findViewById(R.id.autocomplete_search_food);
        ivButtonSearch = (ImageView) view.findViewById(R.id.iv_button_search);
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
                Intent intent = new Intent(getActivity(), FoodNutritionActivity.class);
                intent.putExtra("FoodName", actvSearchFood.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void getAutoComplete(String q, int limit) {
        Call<ArrayList<String>> call = apiInterface.getAutoComplete(q, limit);
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Log.d(TAG, "onResponse: Response Body = " + response.body());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, response.body());
                actvSearchFood.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: Response Failed = " + t.getMessage());
            }
        });
    }


}