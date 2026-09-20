package com.example.foodie.managers;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodie.databinding.RecipeItemCardBinding;
import com.example.foodie.models.Recipe;

import java.util.List;
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> recipeList;
    private OnRecipeClickListener listener;

    public RecipeAdapter(List<Recipe> recipeList, OnRecipeClickListener listener){
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        RecipeItemCardBinding binding = RecipeItemCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position){
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount(){
        return recipeList.size();
    }


    // ViewHolder inner class
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private RecipeItemCardBinding binding;

        public RecipeViewHolder(RecipeItemCardBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Recipe recipe){
            binding.recipeName.setText(recipe.getName());
            binding.recipeDescription.setText(recipe.getDescription());

            // handling clicks on recipe items
            itemView.setOnClickListener(v -> {
                if(listener!=null){
                    listener.onRecipeClick(recipe);
                }
            });
        }
    }

    // Interface for click handling
    public interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    // updating the list dynamically
    public void updateList(List<Recipe> newList){
        this.recipeList = newList;
        notifyDataSetChanged();
    }
}
