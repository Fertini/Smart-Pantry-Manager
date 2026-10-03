package com.example.foodie.fragments.recipeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.database.RecipeDAO;
import com.example.foodie.databinding.UpdateRecipeLayoutBinding;
import com.example.foodie.models.Recipe;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class EditRecipeFragment extends Fragment {

    private UpdateRecipeLayoutBinding binding;
    private Recipe recipe;

    public EditRecipeFragment(){}

    // ========== Override Methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = UpdateRecipeLayoutBinding.inflate(inflater, container, false);
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

        // --------- onclick listeneer for editing recipe -------- //
        binding.editRecipeItemBtn.setOnClickListener(btn ->{

            if (isValid()){
                recipe.setName(binding.recipeName.getText().toString());
                recipe.setDescription(binding.recipeDescription.getText().toString());

                if(RecipeDAO.updateRecipe(recipe)){ // update recipe if entered data is valid
                    Toast.makeText(getContext(), "Recipe successfully updated", Toast.LENGTH_LONG).show();

                    // redirect user to view their changes
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, RecipeDetailsFragment.newInstance(recipe))
                            .addToBackStack(null)
                            .commit();
                }
            } else {
                // failure message
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Operation Failed")
                        .setMessage("Failed to update recipe")
                        .setPositiveButton("Ok", null)
                        .show();
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //

    public void displayDetails(){
        String calories = recipe.getCalories() + " per serving";
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

    public boolean isValid(){
        boolean valid = true;

        String name = binding.recipeName.getText().toString();
        String description = binding.recipeDescription.getText().toString();

        // Name
        if (name.isEmpty()) {
            binding.recipeName.setError("Enter a name for the recipe");
            valid = false;
        }

        // Description
        if (name.isEmpty()) {
            binding.recipeName.setError("Enter a description for the recipe");
            valid = false;
        }

        return valid;
    }

    public static EditRecipeFragment newInstance(Recipe recipe){
        EditRecipeFragment fragment = new EditRecipeFragment();
        Bundle args = new Bundle();
        args.putSerializable("recipe", recipe);
        fragment.setArguments(args);
        return fragment;
    }
}
