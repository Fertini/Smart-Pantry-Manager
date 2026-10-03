package com.example.foodie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodie.models.PantryItem;

import java.util.ArrayList;
import java.util.List;

public class PantryItemDAO {
    private static SQLiteDatabase db;

    // Initialize the database
    public static void init(Context context) {
        db = DatabaseConnection.getConnection(context);
    }

    // ============== READ METHODS ==============

    // Get all pantryItems
    public static ArrayList<PantryItem> getAll() {
        ArrayList<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items";

        if (db == null) return pantryItems;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                PantryItem pantryItem = new PantryItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("pantry_item_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pantry_item_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"))
                );
                pantryItems.add(pantryItem);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return pantryItems;
    }

    // Get pantryItem by id
    public static PantryItem getPantryItem(int pantryItemId) {
        String sql = "SELECT * FROM Pantry_Items WHERE pantry_item_id = ?";

        if (db == null) return null;

        try (Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(pantryItemId)})) {
            if (cursor.moveToNext()) {
                return new PantryItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("pantry_item_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pantry_item_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"))
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    // Search pantryItems by name (partial match)
    public static List<PantryItem> searchPantryItemsByName(String searchTerm) {
        List<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items WHERE name LIKE ?";

        if (db == null) return pantryItems;

        try (Cursor cursor = db.rawQuery(sql, new String[]{"%" + searchTerm + "%"})) {
            while (cursor.moveToNext()) {
                PantryItem pantryItem = new PantryItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("pantry_item_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pantry_item_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"))
                );
                pantryItems.add(pantryItem);
            }
        } catch (Exception e) {
            System.err.println("Error searching pantry items: " + e.getMessage());
        }

        return pantryItems;
    }

    // ============== ADD METHODS ==============

    // Add a new pantryItem to the database
    public static boolean addPantryItem(PantryItem pantryItem) {
        if (db == null) return false;

        try {
            ContentValues values = new ContentValues();
            values.put("pantry_item_name", pantryItem.getName());
            values.put("quantity", pantryItem.getQuantity());
            values.put("unit", pantryItem.getUnit());
            values.put("expiry_date", pantryItem.getExpiryDate());

            long result = db.insert("Pantry_Items", null, values);
            if (result > 0) {
                System.out.println("Pantry item added successfully: " + pantryItem.getName());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error adding pantry item: " + e.getMessage());
        }

        return false;
    }

    // ============== UPDATE METHODS ==============

    // Update pantryItem details
    public static boolean updatePantryItem(PantryItem pantryItem) {
        if (db == null) return false;

        try {
            ContentValues values = new ContentValues();
            values.put("pantry_item_name", pantryItem.getName());
            values.put("quantity", pantryItem.getQuantity());
            values.put("unit", pantryItem.getUnit());
            values.put("expiry_date", pantryItem.getExpiryDate());

            int rowsUpdated = db.update("Pantry_Items", values,
                    "pantry_item_id = ?",
                    new String[]{String.valueOf(pantryItem.getPantryItemId())});

            if (rowsUpdated > 0) {
                System.out.println("Pantry item updated successfully: " + pantryItem.getName());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error updating pantry item: " + e.getMessage());
        }

        return false;
    }

    // ============== PANTRY MANAGEMENT METHODS ==============

    // Get all expired pantry items
    public static List<PantryItem> getExpiredPantryItems() {
        List<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items WHERE expiry_date < date('now')";

        if (db == null) return pantryItems;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                PantryItem pantryItem = new PantryItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("pantry_item_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pantry_item_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"))
                );
                pantryItems.add(pantryItem);
            }
        } catch (Exception e) {
            System.err.println("Error loading expired pantry items: " + e.getMessage());
        }

        return pantryItems;
    }

    // Add stock (restocking quantities)
    public static boolean addStock(int pantryItemId, int quantity) {
        if (quantity <= 0) {
            System.err.println("Quantity must be positive");
            return false;
        }

        if (db == null) return false;

        try {
            String sql = "UPDATE Pantry_Items SET quantity = quantity + ? WHERE pantry_item_id = ?";
            db.execSQL(sql, new Object[]{quantity, pantryItemId});
            System.out.println("Added " + quantity + " units to pantry item ID " + pantryItemId);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding stock: " + e.getMessage());
        }

        return false;
    }

    // Remove stock (reducing quantity from using in recipe)
    public static boolean removeStock(int pantryItemId, int quantity) {
        if (quantity <= 0) {
            System.err.println("Quantity must be positive");
            return false;
        }

        PantryItem pantryItem = getPantryItem(pantryItemId);
        if (pantryItem == null) return false;

        if (db == null) return false;

        try {
            String sql = "UPDATE Pantry_Items SET quantity = quantity - ? WHERE pantry_item_id = ?";
            db.execSQL(sql, new Object[]{quantity, pantryItemId});
            System.out.println("Removed " + quantity + " units from pantry item " + pantryItem.getName());
            return true;
        } catch (Exception e) {
            System.err.println("Error removing stock: " + e.getMessage());
        }

        return false;
    }

    // ============== DELETE METHODS ==============

    // Delete pantryItem from the database
    public static boolean deletePantryItem(int pantryItemId) {
        if (db == null) return false;

        try {
            int rowsDeleted = db.delete("Pantry_Items",
                    "pantry_item_id = ?",
                    new String[]{String.valueOf(pantryItemId)});

            if (rowsDeleted > 0) {
                System.out.println("Pantry item deleted successfully (ID: " + pantryItemId + ")");
                return true;
            } else {
                System.err.println("Pantry item not found (ID: " + pantryItemId + ")");
            }
        } catch (Exception e) {
            System.err.println("Error deleting pantry item: " + e.getMessage());
        }

        return false;
    }

    // ============== COUNT METHODS ==============

    public static int getTotalPantryItemCount() {
        String sql = "SELECT COUNT(*) as count FROM Pantry_Items";

        if (db == null) return -1;

        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getInt(cursor.getColumnIndexOrThrow("count"));
            }
        } catch (Exception e) {
            System.err.println("Error counting pantry items: " + e.getMessage());
        }

        return -1;
    }
}