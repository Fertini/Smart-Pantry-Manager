package com.example.foodie.models;

import android.net.nsd.AdvertisingRequest;

public class Ingredient {
    private int ingredientId;
    private String name;
    private double amount;
    private String unit;
    private int recipeId;

    // constructor for retrieve db items
    public Ingredient(int ingredientId, String name, double amount, String unit, int recipeId){
        this.ingredientId = ingredientId;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.recipeId = recipeId;
    }

    // constructor for new ingredients
    public Ingredient(String name, double amount, String unit, int recipeId){
        this(0, name, amount, unit, recipeId);
    }

    // getters
    public int getIngredientId(){return ingredientId;}
    public String getName(){return name;}
    public double getAmount(){return amount;}
    public String getUnit(){return unit;}
    public int getRecipeId(){return recipeId;}

    // setters
    public void setIngredientId(int ingredientId){this.ingredientId = ingredientId;}
    public void setName(String name) {this.name = name;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setUnit(String unit) {this.unit = unit;}
    public void setRecipeId(int recipeId) {this.recipeId = recipeId;}

    // overrides
    @Override
    public String toString(){
        return "ID: " + ingredientId +
                "\nName: " + name +
                "\nAmount: " + amount +
                "\nUnit: " + unit +
                "\nRecipeId: " + recipeId;
    }
}
