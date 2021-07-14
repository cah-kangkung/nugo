package com.hafiz_1313617032_uas.nugo.Adapter;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Hit;
import com.hafiz_1313617032_uas.nugo.Model.DailyRecipe.Recipe;
import com.hafiz_1313617032_uas.nugo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ListViewHolder> {

    private static final String TAG = "RecipeAdapter";

    List<Hit> listHit;
    private OnRecipeListener onRecipeListener;

    public RecipeAdapter(List<Hit> listHit, OnRecipeListener onRecipeListener) {
        this.listHit = listHit;
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_recipe, parent, false);
        RecipeAdapter.ListViewHolder listViewHolder = new RecipeAdapter.ListViewHolder(view, onRecipeListener);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ListViewHolder holder, int position) {
        Log.d(TAG, "Recipe Name: " + listHit.get(position).getRecipe().getLabel());
        Log.d(TAG, "Recipe Calories: " + listHit.get(position).getRecipe().getCalories());
        Log.d(TAG, "Recipe Time: " + listHit.get(position).getRecipe().getTotalTime());

        Picasso.get().load(listHit.get(position).getRecipe().getImage()).into(holder.ivRecipeImage);
        holder.tvRecipeName.setText(listHit.get(position).getRecipe().getLabel());
        holder.tvRecipeCalorie.setText("Calories (kcal) : " + Integer.toString((int) Math.floor(listHit.get(position).getRecipe().getCalories())));
        holder.tvRecipeTime.setText(Integer.toString(listHit.get(position).getRecipe().getTotalTime()) + " min");
    }

    @Override
    public int getItemCount() {
        return listHit.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivRecipeImage;
        private TextView tvRecipeName, tvRecipeCalorie, tvRecipeTime;
        OnRecipeListener onRecipeListener;

        public ListViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
            super(itemView);
            ivRecipeImage = (ImageView) itemView.findViewById(R.id.iv_recipe_image);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            tvRecipeCalorie = itemView.findViewById(R.id.tv_recipe_calorie);
            tvRecipeTime = itemView.findViewById(R.id.tv_recipe_time);
            this.onRecipeListener = onRecipeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }

    public interface OnRecipeListener {
        void onRecipeClick(int position);
    }
}
