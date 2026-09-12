package com.example.foodie.models;

import java.time.LocalDate;

public class PantryItem {
    int pantryItemId;
    String name;
    int quantity;
    String unit;
    LocalDate expiryDate;

    // constructor for existing pantry items
    public PantryItem(int pantryItemId, String name, int quantity, String unit, LocalDate expiryDate){
        this.pantryItemId = pantryItemId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    // constructor for new pantry items
    public PantryItem(String name, int quantity, String unit, LocalDate expiryDate){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    // ======================= getters ======================== //
    public int getPantryItemId(){return pantryItemId;}
    public String getName(){return name;}
    public int getQuantity(){return quantity;}
    public String getUnit(){return unit;}
    public LocalDate getExpiryDate(){return expiryDate;}

    // ======================= setters ======================== //
    public void setPantryItemId(int pantryItemId){this.pantryItemId = pantryItemId;}
    public void setName(String name){this.name = name;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public void setUnit(String unit){this.unit = unit;}
    public void setExpiryDate(LocalDate expiryDate){this.expiryDate = expiryDate;}

    // ======================= methods ======================== //

    @Override
    public String toString(){
        return "Name: " + name +
                "\nQuantity: " + quantity +
                "\nUnit: " + unit +
                "\nExpiry Date: " + expiryDate;
    }
}
