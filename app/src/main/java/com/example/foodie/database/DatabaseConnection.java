package com.example.foodie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Foodie_Database.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseConnection instance;
    private SQLiteDatabase db;

    // Private constructor
    private DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // get a single instance of the database connection
    public static synchronized DatabaseConnection getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseConnection(context.getApplicationContext());
        }
        return instance;
    }

    // Get writable database
    public static SQLiteDatabase getConnection(Context context) {
        try {
            DatabaseConnection dbConn = getInstance(context);
            SQLiteDatabase database = dbConn.getWritableDatabase();
            System.out.println("Connected to SQLite database");
            return database;
        } catch (Exception e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }

    // Get readable database
    public static SQLiteDatabase getReadableConnection(Context context) {
        try {
            DatabaseConnection dbConn = getInstance(context);
            return dbConn.getReadableDatabase();
        } catch (Exception e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }

    // Close connection
    public static void closeConnection(SQLiteDatabase db) {
        try {
            if (db != null && db.isOpen()) {
                db.close();
                System.out.println("Database connection closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create pantry items table
        String createPantrySQL = "CREATE TABLE IF NOT EXISTS Pantry_Items (" +
                "pantry_item_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pantry_item_name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "unit TEXT NOT NULL, " +
                "expiry_date TEXT NOT NULL)";
        db.execSQL(createPantrySQL);

        // Create recipes table
        String createRecipesSQL = "CREATE TABLE IF NOT EXISTS Recipes (" +
                "recipe_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipe_name TEXT NOT NULL, " +
                "description TEXT NOT NULL, " +
                "ingredients JSON NOT NULL, " +
                "instructions JSON NOT NULL, " +
                "duration INT NOT NULL, " +
                "serving_size INT NOT NULL, " +
                "calories INT NOT NULL)";
        db.execSQL(createRecipesSQL);

        System.out.println("Database tables created");

        // insert default data into the database
        insertRecipesIntoDB(db);
    }

    // Handle database upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pantry_Items");
        db.execSQL("DROP TABLE IF EXISTS Recipes");
        onCreate(db);
    }

    public void insertRecipesIntoDB(SQLiteDatabase db){
        db.execSQL(data.data);
    }
}