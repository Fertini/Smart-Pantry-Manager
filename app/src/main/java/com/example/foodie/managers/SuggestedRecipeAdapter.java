package com.example.foodie.managers;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodie.databinding.RecipeItemCardBinding;
import com.example.foodie.models.Recipe;

import java.util.List;
public class SuggestedRecipeAdapter extends RecyclerView.Adapter<SuggestedRecipeAdapter.SuggestedRecipeViewHolder>{
    private List<Recipe> recipeList;
    private OnSuggestedRecipeClickListener listener;

    public SuggestedRecipeAdapter(List<Recipe> recipeList, OnSuggestedRecipeClickListener listener){
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestedRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        RecipeItemCardBinding binding = RecipeItemCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new SuggestedRecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedRecipeViewHolder holder, int position){
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount(){
        return recipeList.size();
    }


    // ViewHolder inner class
    public class SuggestedRecipeViewHolder extends RecyclerView.ViewHolder {
        private RecipeItemCardBinding binding;

        public SuggestedRecipeViewHolder(RecipeItemCardBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Recipe recipe){
            binding.recipeName.setText(recipe.getName());
            binding.recipeDescription.setText(recipe.getDescription());

            // handling clicks on recipe items
            itemView.setOnClickListener(v -> {
                if(listener!=null){
                    listener.onSuggestedRecipeClick(recipe);
                }
            });
        }
    }

    // Interface for click handling
    public interface OnSuggestedRecipeClickListener{
        void onSuggestedRecipeClick(Recipe recipe);
    }

    // updating the list dynamically
    public void updateList(List<Recipe> newList){
        this.recipeList = newList;
        notifyDataSetChanged();
    }
}