package com.example.foodie.models;
import java.util.*;
public class Recipe {
    int recipeId;
    String name;
    String description;
    ArrayList<String> ingredients;
    String duration;
    int servingSize;
    double calories;

    // =============== constructors =================== //

    // constructor for db
    public Recipe(int recipeId, String name, String description, ArrayList<String> ingredients, String duration, int servingSize, double calories){
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.duration = duration;
        this.servingSize = servingSize;
        this.calories = calories;
    }

    // constructor for new recipes
    public Recipe(String name, String description, ArrayList<String> ingredients, String duration, int servingSize, double calories){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.duration = duration;
        this.servingSize = servingSize;
        this.calories = calories;
    }

    // ================ getters ====================== //
    public int getRecipeId(){return recipeId;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public ArrayList<String> getIngredients(){return ingredients;}
    public String getDuration(){return duration;}
    public int getServingSize(){return servingSize;}
    public double getCalories(){return calories;}

    // ================ setters ====================== //
    public void setRecipeId(int recipeId){this.recipeId = recipeId;}
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setIngredients(ArrayList<String> ingredients){this.ingredients = ingredients;}
    public void setDuration(String duration){this.duration = duration;}
    public void setServingSize(int servingSize){this.servingSize = servingSize;}
    public void setCalories(double calories){this.calories = calories;}

    // ==================== methods ======================= //

    @Override
    public String toString(){
        return "Name: " + name +
                "\nDescription: " + description +
                "\nIngredients: " + ingredients +
                "\nDuration: " + duration +
                "\nServing Size: " + servingSize +
                "\nCalories: " + calories;
    }
}
