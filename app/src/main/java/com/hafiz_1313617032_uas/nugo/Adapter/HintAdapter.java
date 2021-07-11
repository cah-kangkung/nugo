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
    private OnHintListener onHintListener;

    public HintAdapter(List<Hint> listFoodHint, OnHintListener onHintListener) {
        this.listFoodHint = listFoodHint;
        this.onHintListener = onHintListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_hint, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view, onHintListener);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
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

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivHintImage;
        public TextView tvHintName;
        OnHintListener onHintListener;

        public ListViewHolder(@NonNull View itemView, OnHintListener onHintListener) {
            super(itemView);

            ivHintImage = (ImageView) itemView.findViewById(R.id.iv_hint_image);
            tvHintName = itemView.findViewById(R.id.tv_hint_name);
            this.onHintListener = onHintListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onHintListener.onHintClick(getAdapterPosition());
        }
    }

    public interface OnHintListener {
        void onHintClick(int position);
    }
}
