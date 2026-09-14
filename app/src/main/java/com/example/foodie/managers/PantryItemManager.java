package com.example.foodie.managers;

import java.util.ArrayList;
import java.time.LocalDate;
import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.models.PantryItem;

public class PantryItemManager {

    public static ArrayList<PantryItem> pantryItems = new ArrayList<>();

    public static void createPantryItem(String name, int quantity, String unit, LocalDate expiryDate){
        PantryItem item = new PantryItem(name, quantity, unit, expiryDate);
        pantryItems.add(item);
        PantryItemDAO.addPantryItem(item);
    }

    public static void removePantryItem(int pantryItemId){
        for (PantryItem item : pantryItems){
            if(item.getPantryItemId()==pantryItemId){
                pantryItems.remove(item);
                PantryItemDAO.deletePantryItem(pantryItemId);
                return;
            }
        }
    }


}
