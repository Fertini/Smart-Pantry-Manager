package com.example.foodie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodie.models.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeDAO {
    private static SQLiteDatabase db;
    private static Gson gson = new Gson();  // Gson instance for converting between JSON and Objects

    // Initialize the database
    public static void init(Context context) {
        db = DatabaseConnection.getConnection(context);
    }

    // ============== READ METHODS ==============

    public static ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes";

        if (db == null) return recipes;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                // Get each column value from the database
                int recipeId = cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("recipe_name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                // Get ingredients as JSON string and convert to ArrayList
                String ingredientsJson = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
                ArrayList<String> ingredients = parseItemsFromJSON(ingredientsJson);

                // Get instructions as JSON string and convert to ArrayList
                String instructionsJson = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
                ArrayList<String> instructions = parseItemsFromJSON(instructionsJson);

                int duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
                int servingSize = cursor.getInt(cursor.getColumnIndexOrThrow("serving_size"));
                int calories = cursor.getInt(cursor.getColumnIndexOrThrow("calories"));

                // Create a new Recipe object with all the data
                Recipe recipe = new Recipe(
                        recipeId, name, description, ingredients, instructions,
                        duration, servingSize, calories
                );
                recipes.add(recipe);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return recipes;
    }

    // ============== ADD METHODS ==============
    public static boolean addRecipe(Recipe recipe) {
        if (db == null) return false;

        try {
            // Create a container for the values to insert
            ContentValues values = new ContentValues();
            values.put("recipe_name", recipe.getName());
            values.put("description", recipe.getDescription());

            // Convert ArrayList<String> to JSON string before storing
            values.put("ingredients", itemsToJSON(recipe.getIngredients()));
            values.put("instructions", itemsToJSON(recipe.getInstructions()));

            values.put("duration", recipe.getDuration());
            values.put("serving_size", recipe.getServingSize());
            values.put("calories", recipe.getCalories());

            // Insert into database
            long result = db.insert("Recipes", null, values);
            if (result > 0) {
                System.out.println("Recipe added successfully: " + recipe.getName());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error adding recipe: " + e.getMessage());
        }

        return false;
    }

    // ============== UPDATE METHODS ==============

    // Update recipe
    public static boolean updateRecipe(Recipe recipe) {
        if (db == null) return false;

        try {
            ContentValues values = new ContentValues();
            values.put("recipe_name", recipe.getName());
            values.put("description", recipe.getDescription());
            values.put("ingredients", itemsToJSON(recipe.getIngredients()));
            values.put("instructions", itemsToJSON(recipe.getInstructions()));
            values.put("duration", recipe.getDuration());
            values.put("serving_size", recipe.getServingSize());
            values.put("calories", recipe.getCalories());

            // Update WHERE recipe_id = ?
            int rowsUpdated = db.update("Recipes", values,
                    "recipe_id = ?",
                    new String[]{String.valueOf(recipe.getRecipeId())});

            if (rowsUpdated > 0) {
                System.out.println("Recipe updated successfully: " + recipe.getName());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error updating recipe: " + e.getMessage());
        }

        return false;
    }

    // ============== DELETE METHODS ==============

    // delete recipe
    public static boolean deleteRecipe(int recipeId) {
        if (db == null) return false;

        try {
            int rowsDeleted = db.delete("Recipes",
                    "recipe_id = ?",
                    new String[]{String.valueOf(recipeId)});

            if (rowsDeleted > 0) {
                System.out.println("Recipe deleted successfully (ID: " + recipeId + ")");
                return true;
            } else {
                System.err.println("Recipe not found (ID: " + recipeId + ")");
            }
        } catch (Exception e) {
            System.err.println("Error deleting recipe: " + e.getMessage());
        }

        return false;
    }

    // ============== JSON CONVERSION HELPER METHODS ==============

    private static ArrayList<String> parseItemsFromJSON(String json) {
        try {
            // Check if JSON is null or empty
            if (json == null || json.isEmpty()) {
                return new ArrayList<>();  // Return empty list
            }

            // TypeToken is used to tell hte gson depenedency rhat the JSON should be converted to an ArrayList
            Type type = new TypeToken<ArrayList<String>>(){}.getType();

            // Use Gson to convert JSON string to ArrayList<String>
            ArrayList<String> items = gson.fromJson(json, type);

            return items != null ? items : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error parsing items JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // converting from an array list to an JSON array to store int the db
    private static String itemsToJSON(ArrayList<String> items) {
        try {
            // Check if list is null or empty
            if (items == null || items.isEmpty()) {
                return "[]";  // Return empty JSON array
            }

            // Use Gson to convert ArrayList<String> to JSON string
            return gson.toJson(items);
        } catch (Exception e) {
            System.err.println("Error converting ingredients to JSON: " + e.getMessage());
            return "[]";
        }
    }
}