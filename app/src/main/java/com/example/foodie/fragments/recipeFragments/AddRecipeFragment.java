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
import com.example.foodie.databinding.AddRecipeLayoutBinding;
import com.example.foodie.models.Recipe;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class AddRecipeFragment extends Fragment {

    private AddRecipeLayoutBinding binding;
    private Recipe recipe;
    private final ArrayList<String> ingredients = new ArrayList<>();
    private final ArrayList<String> instructions = new ArrayList<>();
    AddRecipeFragment(){}

    // ========== Override Methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = AddRecipeLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        RecipeDAO.init(requireContext()); // initialising database

        binding.addIngredientBtn.setOnClickListener(btn -> { // click handler for adding ingredients
            String ingredient = binding.ingredientDescription.getText().toString().trim();
            if (ingredient.isEmpty()){
                binding.ingredientDescription.setError("Enter an ingredient");
            } else {
                ingredients.add(ingredient);
                binding.recipeIngredients.setText(displayIngredients());
                binding.ingredientDescription.setText("");
            }
        });

        binding.addInstructionBtn.setOnClickListener(btn -> { // click handler for adding instructions
            String instruction = binding.instructionDescription.getText().toString().trim();
            if (instruction.isEmpty()){
                binding.instructionDescription.setError("Enter an instruction");
            } else {
                instructions.add(instruction);
                binding.recipeInstructions.setText(displayInstructions());
                binding.instructionDescription.setText("");
            }
        });

        binding.addRecipeItemBtn.setOnClickListener(btn -> { // click handler for adding new recipe
            if(isValid()){
                String name = binding.recipeName.getText().toString().trim();
                String description = binding.recipeDescription.getText().toString().trim();
                int duration =Integer.parseInt(binding.recipeDuration.getText().toString().trim());
                int serving =Integer.parseInt(binding.recipeServing.getText().toString().trim());
                int calories =Integer.parseInt(binding.recipeCalories.getText().toString().trim());
                recipe = new Recipe(name, description, ingredients, instructions, duration, serving, calories); // create new recipe

                boolean result = RecipeDAO.addRecipe(recipe); // add to database

                if (result){
                    // redirect user to see the recipe list
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new RecipeListFragment())
                            .addToBackStack(null)
                            .commit();
                    Toast.makeText(getContext(), "Recipe added successfully", Toast.LENGTH_SHORT).show(); // success message

                } else {
                    // failure message
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle("Operation Failed")
                            .setMessage("Failed to save recipe")
                            .setPositiveButton("Ok", null)
                            .show();
                }
            } else {
                // alert that one or more fields are empty
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Input Error")
                        .setMessage("One or more fields are empty")
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

    public boolean isValid(){
        boolean valid = true;

        String name = binding.recipeName.getText().toString().trim();
        String description = binding.recipeDescription.getText().toString().trim();
        String ingredientField = binding.ingredientDescription.getText().toString();
        String instructionField = binding.instructionDescription.getText().toString();
        String duration = binding.recipeDuration.getText().toString().trim();
        String serving = binding.recipeServing.getText().toString().trim();
        String calories = binding.recipeCalories.getText().toString().trim();

        // name
        if (name.isEmpty()) {
            binding.recipeName.setError("Enter a name for the recipe");
            valid = false;
        }

        // description
        if (description.isEmpty()) {
            binding.recipeDescription.setError("Enter a description for the recipe");
            valid = false;
        }

        // ingredient
        if (ingredients.isEmpty()) {
            binding.ingredientDescription.setError("Enter an ingredient");
            valid = false;
        }

        // instruction
        if (instructions.isEmpty()) {
            binding.instructionDescription.setError("Enter an instruction");
            valid = false;
        }

        // duration
        if (duration.isEmpty()) {
            binding.recipeDuration.setError("Enter a duration");
            valid = false;
        } else {
            try {
                int qty = Integer.parseInt(duration);
                if (qty <= 0) {
                    binding.recipeDuration.setError("Duration must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                binding.recipeDuration.setError("Duration should only be a whole number");
                valid = false;
            }
        }

        // serving size
        if (serving.isEmpty()) {
            binding.recipeServing.setError("Enter a serving size");
            valid = false;
        } else {
            try {
                int qty = Integer.parseInt(serving);
                if (qty <= 0) {
                    binding.recipeServing.setError("Serving size must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                binding.recipeServing.setError("Serving size should only be a whole number");
                valid = false;
            }
        }

        // calories
        if (calories.isEmpty()) {
            binding.recipeCalories.setError("Enter calories");
            valid = false;
        } else {
            try {
                int qty = Integer.parseInt(calories);
                if (qty <= 0) {
                    binding.recipeCalories.setError("Calories must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                binding.recipeCalories.setError("Calories should only be a whole number");
                valid = false;
            }
        }
        return valid;
    }

    public String displayIngredients(){
        StringBuilder ingredientsText = new StringBuilder();

        for(String item : ingredients){
            ingredientsText.append(item)
                    .append("\n");
        }

        return ingredientsText.toString();
    }

    public String displayInstructions(){
        StringBuilder instructionsText = new StringBuilder();

        for(String item : instructions){
            instructionsText.append(item)
                    .append("\n");
        }

        return instructionsText.toString();
    }
}
