package com.example.foodie.managers;

import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.database.RecipeDAO;
import com.example.foodie.models.PantryItem;
import com.example.foodie.models.Recipe;

import java.util.ArrayList;

public class RecipeManager {

    // getting pantry
    public static ArrayList<PantryItem> pantry = PantryItemDAO.getAll();
    public static ArrayList<Recipe> recipes = RecipeDAO.getAll();

    // check if pantry has necessary ingredients for the recipe
    public static Recipe hasIngredients(Recipe recipe){
        ArrayList<String> ingredients = recipe.getIngredients();

        for (String ingredient : ingredients) {
            if (pantry.contains(ingredient)) return recipe;
        } return null;
    }

    public static Recipe createRecipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions, String duration, int servingSize, String calories){
        Recipe recipe = new Recipe(name, description, ingredients, instructions, duration, servingSize, calories);
        if (!recipes.contains(recipe)){
            RecipeDAO.addRecipe(recipe);
            return recipe;
        } return null;
    }

    public static void removeRecipe(int recipeId){
        RecipeDAO.deleteRecipe(recipeId);
    }





}
