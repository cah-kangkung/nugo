package com.hafiz_1313617032_uas.nugo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.hafiz_1313617032_uas.nugo.R;

public class CalculatorFragment extends Fragment {

    private static final String TAG = "CalculatorFragment";

    // layout variable
    private EditText etAge, etHeight, etWeight;
    private RadioGroup rgGender;
    private Spinner spActivity;
    private Button btnCalculate;
    private TextView tvMaintain;
    private ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLayoutVariable(view);
        addOnListenerButton();
    }

    private void initLayoutVariable(View view) {
        etAge = view.findViewById(R.id.et_age);
        etHeight = view.findViewById(R.id.et_height);
        etWeight = view.findViewById(R.id.et_weight);
        rgGender = view.findViewById(R.id.rg_gender);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        spActivity = view.findViewById(R.id.sp_activity);
        tvMaintain = view.findViewById(R.id.tv_maintain);
        constraintLayout = view.findViewById(R.id.cl_calories_intake);
    }

    private void addOnListenerButton() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get textview value
                int age = Integer.parseInt(etAge.getText().toString());
                int height = Integer.parseInt(etHeight.getText().toString());
                int weight = Integer.parseInt(etWeight.getText().toString());

                // get radio button value
                int selected = rgGender.getCheckedRadioButtonId();
                RadioButton gender = getView().findViewById(selected);

                // get spinner value
                int activity = spActivity.getSelectedItemPosition();

                double bmr = calculateBmr(age, height, weight, gender.getText().toString(), activity);

                int calories = (int) Math.round(bmr);

                tvMaintain.setText(calories + " cal/day");

                constraintLayout.setVisibility(View.VISIBLE);

                /* Log.d(TAG, "onViewCreated: " + age);
                Log.d(TAG, "onViewCreated: " + height);
                Log.d(TAG, "onViewCreated: " + weight);
                Log.d(TAG, "onViewCreated: " + gender.getText().toString());
                Log.d(TAG, "onViewCreated: " + activity);
                Log.d(TAG, "onClick: " + bmr); */
            }
        });
    }

    private double calculateBmr(int age, int height, int weight, String gender, int activity) {
        double bmr;
        if (gender.equals("Male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        float activityRate = 0;
        if (activity == 0) {
            activityRate = 1.2f;
        } else if (activity == 1) {
            activityRate = 1.375f;
        }else if (activity == 2) {
            activityRate = 1.55f;
        }else if (activity == 3) {
            activityRate = 1.725f;
        }else if (activity == 4) {
            activityRate = 1.9f;
        }

        bmr *= activityRate;

        return bmr;
    }



}