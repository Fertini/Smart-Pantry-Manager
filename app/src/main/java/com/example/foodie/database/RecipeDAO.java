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
import java.util.List;

public class RecipeDAO {
    private static SQLiteDatabase db;
    private static Gson gson = new Gson();  // ← Gson instance for converting between JSON and Objects

    // Initialize the database (call this in your Activity/Fragment)
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
                ArrayList<String> ingredients = parseIngredientsFromJSON(ingredientsJson);

                // Get instructions as JSON string and convert to ArrayList
                String instructionsJson = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
                ArrayList<String> instructions = parseInstructionsFromJSON(instructionsJson);

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

    // get a recipe by id
    public static Recipe getRecipe(int recipeId) {
        String sql = "SELECT * FROM Recipes WHERE recipe_id = ?";

        if (db == null) return null;

        try (Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(recipeId)})) {
            if (cursor.moveToNext()) {
                String ingredientsJson = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
                ArrayList<String> ingredients = parseIngredientsFromJSON(ingredientsJson);

                String instructionsJson = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
                ArrayList<String> instructions = parseInstructionsFromJSON(instructionsJson);

                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("recipe_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        ingredients, instructions,
                        cursor.getInt(cursor.getColumnIndexOrThrow("duration")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("serving_size")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("calories"))
                );
                return recipe;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    // search recipes through partial match
    public static List<Recipe> searchRecipesByName(String searchTerm) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes WHERE name LIKE ?";

        if (db == null) return recipes;

        try (Cursor cursor = db.rawQuery(sql, new String[]{"%" + searchTerm + "%"})) {
            while (cursor.moveToNext()) {
                String ingredientsJson = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
                ArrayList<String> ingredients = parseIngredientsFromJSON(ingredientsJson);

                String instructionsJson = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
                ArrayList<String> instructions = parseInstructionsFromJSON(instructionsJson);

                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("recipe_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        ingredients, instructions,
                        cursor.getInt(cursor.getColumnIndexOrThrow("duration")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("serving_size")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("calories"))
                );
                recipes.add(recipe);
            }
        } catch (Exception e) {
            System.err.println("Error searching recipes: " + e.getMessage());
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
            values.put("ingredients", ingredientsToJSON(recipe.getIngredients()));
            values.put("instructions", instructionsToJSON(recipe.getIngredients()));

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
            values.put("ingredients", ingredientsToJSON(recipe.getIngredients()));
            values.put("instructions", instructionsToJSON(recipe.getIngredients()));
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

    // ============== COUNT METHODS ==============

    public static int getTotalRecipeCount() {
        String sql = "SELECT COUNT(*) as count FROM Recipes";

        if (db == null) return -1;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getInt(cursor.getColumnIndexOrThrow("count"));
            }
        } catch (Exception e) {
            System.err.println("Error counting recipes: " + e.getMessage());
        }

        return -1;
    }

    // ============== JSON CONVERSION HELPER METHODS ==============

    private static ArrayList<String> parseIngredientsFromJSON(String json) {
        try {
            // Check if JSON is null or empty
            if (json == null || json.isEmpty()) {
                return new ArrayList<>();  // Return empty list
            }

            // TypeToken is used to tell hte gson depenedency rhat the JSON should be converted to an ArrayList
            Type type = new TypeToken<ArrayList<String>>(){}.getType();

            // Use Gson to convert JSON string to ArrayList<String>
            ArrayList<String> ingredients = gson.fromJson(json, type);

            return ingredients != null ? ingredients : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error parsing ingredients JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static ArrayList<String> parseInstructionsFromJSON(String json) {
        try {
            // Check if JSON is null or empty
            if (json == null || json.isEmpty()) {
                return new ArrayList<>();  // Return empty list
            }

            // TypeToken is used to tell hte gson depenedency rhat the JSON should be converted to an ArrayList
            Type type = new TypeToken<ArrayList<String>>(){}.getType();

            // Use Gson to convert JSON string to ArrayList<String>
            ArrayList<String> instructions = gson.fromJson(json, type);

            return instructions != null ? instructions : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error parsing instructions JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // converting from an array list to an JSON array to store int the db
    private static String ingredientsToJSON(ArrayList<String> ingredients) {
        try {
            // Check if list is null or empty
            if (ingredients == null || ingredients.isEmpty()) {
                return "[]";  // Return empty JSON array
            }

            // Use Gson to convert ArrayList<String> to JSON string
            String json = gson.toJson(ingredients);

            return json;
        } catch (Exception e) {
            System.err.println("Error converting ingredients to JSON: " + e.getMessage());
            return "[]";
        }
    }

    private static String instructionsToJSON(ArrayList<String> instructions) {
        try {
            // Check if list is null or empty
            if (instructions == null || instructions.isEmpty()) {
                return "[]";  // Return empty JSON array
            }

            // Use Gson to convert ArrayList<String> to JSON string
            String json = gson.toJson(instructions);

            return json;
        } catch (Exception e) {
            System.err.println("Error converting ingredients to JSON: " + e.getMessage());
            return "[]";
        }
    }
}