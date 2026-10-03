package com.example.foodie.models;

import java.io.Serializable;

public class PantryItem implements Serializable {
    int pantryItemId;
    String name;
    int quantity;
    String unit;
    String expiryDate;

    // constructor for existing pantry items
    public PantryItem(int pantryItemId, String name, int quantity, String unit, String expiryDate){
        this.pantryItemId = pantryItemId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    // constructor for new pantry items
    public PantryItem(String name, int quantity, String unit, String expiryDate){
        this(0, name, quantity, unit, expiryDate);
    }

    // ======================= getters ======================== //
    public int getPantryItemId(){return pantryItemId;}
    public String getName(){return name;}
    public int getQuantity(){return quantity;}
    public String getUnit(){return unit;}
    public String getExpiryDate(){return expiryDate;}

    // ======================= setters ======================== //
    public void setPantryItemId(int pantryItemId){this.pantryItemId = pantryItemId;}
    public void setName(String name){this.name = name;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public void setUnit(String unit){this.unit = unit;}
    public void setExpiryDate(String expiryDate){this.expiryDate = expiryDate;}

    // ======================= methods ======================== //

    @Override
    public String toString(){
        return "Name: " + name +
                "\nQuantity: " + quantity +
                "\nUnit: " + unit +
                "\nExpiry Date: " + expiryDate;
    }
}
