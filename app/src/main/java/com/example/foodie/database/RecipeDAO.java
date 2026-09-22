package com.example.foodie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodie.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private static SQLiteDatabase db;

    // Initialize the database
    public static void init(Context context) {
        db = DatabaseConnection.getConnection(context);
    }

    // ============== READ METHODS ==============

    // Get all recipes
    public static ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes";

        if (db == null) return recipes;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("ingredients"))),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("instructions"))),
                        cursor.getInt(cursor.getColumnIndexOrThrow("duration")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("serving_size")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("calories"))
                );
                recipes.add(recipe);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return recipes;
    }

    // Get recipe by id
    public static Recipe getRecipe(int recipeId) {
        String sql = "SELECT * FROM Recipes WHERE recipe_id = ?";

        if (db == null) return null;

        try (Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(recipeId)})) {
            if (cursor.moveToNext()) {
                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("ingredients"))),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("instructions"))),
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

    // Search recipes by name (partial match)
    public static List<Recipe> searchRecipesByName(String searchTerm) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes WHERE name LIKE ?";

        if (db == null) return recipes;

        try (Cursor cursor = db.rawQuery(sql, new String[]{"%" + searchTerm + "%"})) {
            while (cursor.moveToNext()) {
                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("ingredients"))),
                        parseItemsFromJSON(cursor.getString(cursor.getColumnIndexOrThrow("instructions"))),
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

    // Add a new recipe to the database
    public static boolean addRecipe(Recipe recipe) {
        if (db == null) return false;

        try {
            ContentValues values = new ContentValues();
            values.put("name", recipe.getName());
            values.put("description", recipe.getDescription());
            values.put("ingredients", itemsToJSON(recipe.getIngredients()));
            values.put("instructions", itemsToJSON(recipe.getInstructions()));
            values.put("duration", recipe.getDuration());
            values.put("serving_size", recipe.getServingSize());
            values.put("calories", recipe.getCalories());

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

    // Update recipe details
    public static boolean updateRecipe(Recipe recipe) {
        if (db == null) return false;

        try {
            ContentValues values = new ContentValues();
            values.put("name", recipe.getName());
            values.put("description", recipe.getDescription());
            values.put("ingredients", itemsToJSON(recipe.getIngredients()));
            values.put("instructions", itemsToJSON(recipe.getInstructions()));
            values.put("duration", recipe.getDuration());
            values.put("serving_size", recipe.getServingSize());
            values.put("calories", recipe.getCalories());

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

    // Delete recipe from the database
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

    // ============== JSON HELPER METHODS ==============

    // Parse JSON string into ArrayList<String>
    private static ArrayList<String> parseItemsFromJSON(String json) {
        ArrayList<String> items = new ArrayList<>();

        if (json == null || json.isEmpty()) {
            return items;
        }

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                items.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            System.err.println("Error parsing ingredients/instructions JSON: " + e.getMessage());
        }

        return items;
    }

     //Convert ArrayList<String> to JSON string
    private static String itemsToJSON(ArrayList<String> items) {
        if (items == null || items.isEmpty()) {
            return "[]";
        }

        try {
            JSONArray jsonArray = new JSONArray();

            for (String item : items) {
                jsonArray.put(item);
            }

            return jsonArray.toString();
        } catch (Exception e) {
            System.err.println("Error converting ingredients/instructions to JSON: " + e.getMessage());
        }

        return "[]";
    }
}