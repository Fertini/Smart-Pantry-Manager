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
//import com.example.foodie.databinding.IngredientInstructionBinding;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment {

    private RecipeItemLayoutBinding binding;
//    private IngredientInstructionCard cardBinding;
    private Recipe recipe;

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
        // Get recipe from arguments
        if (getArguments() != null) {
            recipe = (Recipe) getArguments().getSerializable("recipe");
        }

        // display recipe details
        if (recipe != null) {
            displayDetails();
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //

    public void displayDetails(){
        String calories = recipe.getCalories() + "/per serving";
        String duration = recipe.getDuration() + " minutes";

        binding.recipeName.setText(recipe.getName());
        binding.recipeDescription.setText(recipe.getDescription());
        binding.recipeIngredients.setText(displayIngredients());
        binding.recipeInstructions.setText(displayInstructions());
        binding.recipeDuration.setText(duration);
        binding.recipeServing.setText(String.valueOf(recipe.getServingSize()));
        binding.recipeCalories.setText(calories);
    }

    public String displayIngredients(){
        ArrayList<String> ingredients = recipe.getIngredients();
        StringBuilder ingredientsList = new StringBuilder();
        for (String item : ingredients){
            ingredientsList.append(item).append("\n");
        }
        return ingredientsList.toString();
    }

    public String displayInstructions(){
        ArrayList<String> instructions = recipe.getInstructions();
        StringBuilder instructionsList = new StringBuilder();
        String stepCount = "Step ";
        int count = 0;
        for (String item : instructions){
            instructionsList.append("\n")
                    .append(stepCount)
                    .append(++count)
                    .append(". ")
                    .append(item)
                    .append("\n");
        }
        return instructionsList.toString();
    }

    // method to safely pass over the data of the selected recipe to this fragment from the RecipeListFragment
    public static RecipeDetailsFragment newInstance(Recipe recipe){
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("recipe", recipe);
        fragment.setArguments(args);
        return fragment;
    }
}
