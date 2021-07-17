package com.hafiz_1313617032_uas.nugo.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Hint;
import com.hafiz_1313617032_uas.nugo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ListViewHolder> {

    private static final String TAG = "FoodAdapter";

    List<Hint> listFoodHint;
    private OnFoodListener onFoodListener;

    public FoodAdapter(List<Hint> listFoodHint, OnFoodListener onFoodListener) {
        this.listFoodHint = listFoodHint;
        this.onFoodListener = onFoodListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_food, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view, onFoodListener);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String imageUrl = listFoodHint.get(position).getFood().getImage();
        Log.d(TAG, "label: " + listFoodHint.get(position).getFood().getLabel());
        Log.d(TAG, "imageUrl: " + imageUrl);
        Picasso.get().load(imageUrl).into(holder.ivHintImage);
        holder.tvHintName.setText(listFoodHint.get(position).getFood().getLabel());
    }

    @Override
    public int getItemCount() {
        return listFoodHint.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivHintImage;
        public TextView tvHintName;
        OnFoodListener onFoodListener;

        public ListViewHolder(@NonNull View itemView, OnFoodListener onFoodListener) {
            super(itemView);

            ivHintImage = (ImageView) itemView.findViewById(R.id.iv_hint_image);
            tvHintName = itemView.findViewById(R.id.tv_hint_name);
            this.onFoodListener = onFoodListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFoodListener.onFoodClick(getAdapterPosition());
        }
    }

    public interface OnFoodListener {
        void onFoodClick(int position);
    }
}
