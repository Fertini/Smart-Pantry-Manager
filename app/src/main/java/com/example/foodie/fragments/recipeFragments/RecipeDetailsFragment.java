package com.example.foodie.fragments.recipeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.models.Recipe;
import com.example.foodie.databinding.RecipeItemLayoutBinding;

import java.util.List;

public class RecipeDetailsFragment extends Fragment {

    private RecipeItemLayoutBinding binding;
    private final Recipe recipe = RecipeListFragment.getRecipeClicked();

    public RecipeDetailsFragment(){}

    // ========== Override Methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = RecipeItemLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // display recipe details
        displayDetails();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //

    public void displayDetails(){
        binding.recipeName.setText(recipe.getName());
        binding.recipeDescription.setText(recipe.getDescription());
        binding.recipeIngredients.setText(displayIngredients());
        binding.recipeInstructions.setText(displayInstructions());
        binding.recipeDuration.setText(recipe.getDuration());
        binding.recipeServing.setText(recipe.getServingSize());
        binding.recipeCalories.setText(recipe.getCalories());
    }

    public String displayIngredients(){
        List<String> ingredients = recipe.getIngredients();
        StringBuilder ingredientsList = new StringBuilder();
        for (String item : ingredients){
            ingredientsList.append(item).append("\n");
        }
        return ingredientsList.toString();
    }

    public String displayInstructions(){
        List<String> instructions = recipe.getInstructions();
        StringBuilder instructionsList = new StringBuilder();
        String stepCount = "Step ";
        int count = 0;
        for (String item : instructions){
            instructionsList.append(stepCount)
                    .append(++count)
                    .append(". ")
                    .append(item)
                    .append("\n");
        }
        return instructionsList.toString();
    }
}
