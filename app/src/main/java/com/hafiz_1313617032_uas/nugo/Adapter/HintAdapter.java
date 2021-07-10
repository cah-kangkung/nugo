package com.hafiz_1313617032_uas.nugo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz_1313617032_uas.nugo.Model.FoodNutrition.Hint;
import com.hafiz_1313617032_uas.nugo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HintAdapter extends RecyclerView.Adapter<HintAdapter.ListViewHolder> {

    List<Hint> listFoodHint;

    public HintAdapter(List<Hint> listFoodHint) {
        this.listFoodHint = listFoodHint;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_hint, parent, false);
        HintAdapter.ListViewHolder listViewHolder = new HintAdapter.ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HintAdapter.ListViewHolder holder, int position) {

        if (position == 0) {

        }
        String imageUrl = listFoodHint.get(position).getFood().getImage();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.ivHintImage);
        } else {
            holder.ivHintImage.setBackgroundResource(R.drawable.baseline_restaurant_menu_black_24dp);
        }
        holder.tvHintName.setText(listFoodHint.get(position).getFood().getLabel());
    }

    @Override
    public int getItemCount() {
        return listFoodHint.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivHintImage;
        public TextView tvHintName;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivHintImage = (ImageView) itemView.findViewById(R.id.iv_hint_image);
            tvHintName = itemView.findViewById(R.id.tv_hint_name);
        }
    }
}
