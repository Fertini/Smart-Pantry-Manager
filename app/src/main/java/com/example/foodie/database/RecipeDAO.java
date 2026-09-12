package com.example.foodie.database;

import com.example.foodie.models.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    // get all recipes
    public static ArrayList<Recipe> getAll(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes";
        Connection conn = DatabaseConnection.getConnection();

        if(conn == null) return recipes;

        try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                Recipe recipe = new Recipe(
                        resultSet.getInt("recipe_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        ((ArrayList<String>) resultSet.getArray("ingredients")),
                        resultSet.getString("duration"),
                        resultSet.getInt("serving_size"),
                        resultSet.getDouble("calories")
                );
                recipes.add(recipe);
            } return recipes;

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return recipes;
    }

    // get recipe by id
    public static Recipe getRecipe(int recipeId){
        String sql = "SELECT * FROM Recipes WHERE recipe_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if(conn == null) return null;

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1, recipeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Recipe recipe = new Recipe(
                        resultSet.getInt("recipe_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        ((ArrayList<String>) resultSet.getArray("ingredients")),
                        resultSet.getString("duration"),
                        resultSet.getInt("serving_size"),
                        resultSet.getDouble("calories")
                );
                return recipe;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }


        return null;
    }

    // Search recipes by name (partial match)
    public static List<Recipe> searchRecipesByName(String searchTerm) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipe WHERE name LIKE ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return recipes;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe(
                        resultSet.getInt("recipe_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        ((ArrayList<String>) resultSet.getArray("ingredients")),
                        resultSet.getString("duration"),
                        resultSet.getInt("serving_size"),
                        resultSet.getDouble("calories")
                );
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            System.err.println("Error searching recipes: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return recipes;
    }
    
    // ============== ADD METHODS ==============

    // Add a new recipe to the database
    public static boolean addRecipe(Recipe recipe) {
        String sql = "INSERT INTO Recipe (name, description, ingredients, duration, serving_size, calories) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setArray(3, ((Array) recipe.getIngredients()));
            preparedStatement.setString(4, recipe.getDuration());
            preparedStatement.setInt(5, recipe.getServingSize());
            preparedStatement.setDouble(6, recipe.getCalories());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Recipe added successfully: " + recipe.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding recipe: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== UPDATE METHODS ==============

    // updating recipe details
    public static boolean updateRecipe(Recipe recipe) {
        String sql = "UPDATE Recipe SET name=?, description=?, ingredients=?, duration=?, " +
                    "serving_size=?, calories=? WHERE recipe_id=?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setArray(3, ((Array) recipe.getIngredients()));
            preparedStatement.setString(4, recipe.getDuration());
            preparedStatement.setInt(5, recipe.getServingSize());
            preparedStatement.setDouble(6, recipe.getCalories());
            preparedStatement.setInt(7, recipe.getRecipeId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Recipe updated successfully: " + recipe.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating recipe: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== DELETE METHODS ==============

    // deleting recipe from the database
    public static boolean deleteRecipe(int recipeId) {
        String sql = "DELETE FROM Recipe WHERE recipe_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, recipeId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Recipe deleted successfully (ID: " + recipeId + ")");
                return true;
            } else {
                System.err.println("Recipe not found (ID: " + recipeId + ")");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting recipe: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== COUNT METHODS ==============

    public static int getTotalRecipeCount() {
        String sql = "SELECT COUNT(*) as count FROM Recipe";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return -1;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error counting recipes: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return -1;
    }
}
