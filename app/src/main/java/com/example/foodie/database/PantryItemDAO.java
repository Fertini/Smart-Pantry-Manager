package com.example.foodie.database;

import com.example.foodie.models.PantryItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PantryItemDAO {

    // get all pantryItems
    public static ArrayList<PantryItem> getAll(){
        ArrayList<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items";
        Connection conn = DatabaseConnection.getConnection();

        if(conn == null) return pantryItems;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                PantryItem pantryItem = new PantryItem(
                        resultSet.getInt("pantry_item_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unit"),
                        LocalDate.parse(resultSet.getString("duration"))
                );
                pantryItems.add(pantryItem);
            } return pantryItems;

        } catch (SQLException e){
            System.err.println(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return pantryItems;
    }

    // get pantryItem by id
    public static PantryItem getPantryItem(int pantryItemId){
        String sql = "SELECT * FROM Pantry_Items WHERE pantry_item_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if(conn == null) return null;

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1, pantryItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                PantryItem pantryItem = new PantryItem(
                        resultSet.getInt("pantry_item_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unit"),
                        LocalDate.parse(resultSet.getString("duration"))
                );
                return pantryItem;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }


        return null;
    }

    // Search pantryItems by name (partial match)
    public static List<PantryItem> searchPantryItemsByName(String searchTerm) {
        List<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items WHERE name LIKE ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return pantryItems;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PantryItem pantryItem = new PantryItem(
                        resultSet.getInt("pantry_item_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unit"),
                        LocalDate.parse(resultSet.getString("duration"))
                );
                pantryItems.add(pantryItem);
            }
        } catch (SQLException e) {
            System.err.println("Error searching pantry items: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return pantryItems;
    }

    // ============== ADD METHODS ==============

    // Add a new pantryItem to the database
    public static boolean addPantryItem(PantryItem pantryItem) {
        String sql = "INSERT INTO Pantry_Items (name, quantity, unit, expiry_date) " +
                "VALUES (?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, pantryItem.getName());
            preparedStatement.setInt(2, pantryItem.getQuantity());
            preparedStatement.setString(3, pantryItem.getUnit());
            preparedStatement.setString(4, pantryItem.getExpiryDate().toString());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pantry item added successfully: " + pantryItem.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding pantry item: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== UPDATE METHODS ==============

    // updating pantryItem details
    public static boolean updatePantryItem(PantryItem pantryItem) {
        String sql = "UPDATE PantryItem SET name=?, quantity=?, unit=?, expiry_date=? " +
                "WHERE pantry_item_id=?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, pantryItem.getName());
            preparedStatement.setInt(2, pantryItem.getQuantity());
            preparedStatement.setString(3, pantryItem.getUnit());
            preparedStatement.setString(4, pantryItem.getExpiryDate().toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Pantry item updated successfully: " + pantryItem.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating pantry item: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== PANTRY MANAGEMENT METHODS ==============

    // Get all expired pantry items
    public static List<PantryItem> getExpiredPantryItems() {
        List<PantryItem> pantryItems = new ArrayList<>();
        String sql = "SELECT * FROM Pantry_Items WHERE expiry_date < CURDATE()";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return pantryItems;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                PantryItem pantryItem = new PantryItem(
                        resultSet.getInt("pantry_item_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unit"),
                        LocalDate.parse(resultSet.getString("duration"))
                );
                pantryItems.add(pantryItem);
            }
        } catch (SQLException e) {
            System.err.println("Error loading expired pantry items: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return pantryItems;
    }

    // restocking quantities
    public static boolean addStock(int pantryItemId, int quantity) {
        if (quantity <= 0) {
            System.err.println("Quantity must be positive");
            return false;
        }

        String sql = "UPDATE Pantry_Items SET quantity = quantity + ? WHERE pantry_item_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, pantryItemId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Added " + quantity + " units to pantry item ID " + pantryItemId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding stock: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // reducing quantity from using in recipe
    public static boolean removeStock(int pantryItemId, int quantity) {
        if (quantity <= 0) {
            System.err.println("Quantity must be positive");
            return false;
        }

        //  check if we have enough stock
        PantryItem pantryItem = getPantryItem(pantryItemId);

        String sql = "UPDATE PantryItem SET quantity = quantity - ? WHERE pantry_item_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, pantryItemId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Removed " + quantity + " units from pantry item " + pantryItem.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error removing stock: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }


    // ============== DELETE METHODS ==============

    // deleting pantryItem from the database
    public static boolean deletePantryItem(int pantryItemId) {
        String sql = "DELETE FROM Pantry_Items WHERE pantry_item_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, pantryItemId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Pantry item deleted successfully (ID: " + pantryItemId + ")");
                return true;
            } else {
                System.err.println("Pantry item not found (ID: " + pantryItemId + ")");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting pantry item: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    // ============== COUNT METHODS ==============

    public static int getTotalPantryItemCount() {
        String sql = "SELECT COUNT(*) as count FROM Pantry_Items";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return -1;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error counting pantry items: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return -1;
    }
}
