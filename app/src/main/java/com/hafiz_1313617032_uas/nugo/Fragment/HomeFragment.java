package com.hafiz_1313617032_uas.nugo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.hafiz_1313617032_uas.nugo.R;

public class HomeFragment extends Fragment {

    private EditText etSearchFoodNutrition;
    private ImageView ivButtonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearchFoodNutrition = (EditText) view.findViewById(R.id.et_search_food_nutrition);
        ivButtonSearch = (ImageView) view.findViewById(R.id.iv_button_search);
        ivButtonSearch.setVisibility(View.GONE);

        // listen to text changed
        etSearchFoodNutrition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    ivButtonSearch.setVisibility(View.GONE);
                } else {
                    ivButtonSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}