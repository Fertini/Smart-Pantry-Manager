package com.example.foodie.managers;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie.R;
import com.example.foodie.databinding.RecipeItemCardBinding;
import com.example.foodie.models.Recipe;

import java.util.List;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> recipeList;
    private final OnRecipeClickListener listener;
    boolean isSelectMode = false;
    private ArrayList<Recipe> selectedRecipes = new ArrayList<>();

    public RecipeAdapter(List<Recipe> recipeList, OnRecipeClickListener listener){
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_card, parent, false);
        return new RecipeViewHolder(view);
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

        public RecipeViewHolder(@NonNull View view){
            super(view);
        }

        public void bind(Recipe recipe){
            binding.recipeName.setText(recipe.getName());
            binding.recipeDescription.setText(recipe.getDescription());

            // handling clicks on recipe items
            itemView.setOnClickListener(view -> {
                if(listener!=null){
                    listener.onRecipeClick(recipe);
                }
            });
        }
    }

    // Interface for click handling for recipes
    public interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    // updating the list dynamically
    public void updateList(List<Recipe> newList){
        this.recipeList = newList;
        notifyDataSetChanged();
    }
}