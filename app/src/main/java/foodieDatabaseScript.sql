CREATE DATABASE IF NOT EXISTS Foodie_Database; -- create database
USE Foodie_Database;

-- create pantry items table
CREATE TABLE IF NOT EXISTS Pantry_Items (
    pantry_item_id INT AUTO_INCREMENT PRIMARY_KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    quantity INT,
    unit VARCHAR(50),
    expiry_date DATE
);

-- =====================================================
-- RECIPES DATABASE SCHEMA
-- =====================================================

-- Create Recipes Table
CREATE TABLE IF NOT EXISTS Recipes (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    duration VARCHAR(50) NOT NULL,
    serving_size INT NOT NULL,
    calories VARCHAR(50) NOT NULL
);

-- Create Ingredients Table
CREATE TABLE IF NOT EXISTS Ingredients (
    ingredient_id INT PRIMARY KEY AUTO_INCREMENT,
    ingredient_name VARCHAR(255) NOT NULL UNIQUE,
    ingredient_quantity INT NOT NULL,
);

-- Create Units of Measurement Table
CREATE TABLE Units (
    unit_id INT PRIMARY KEY AUTO_INCREMENT,
    unit_name VARCHAR(50) NOT NULL UNIQUE,
    abbreviation VARCHAR(10)
);

-- Create Recipe_Ingredients Junction Table (Many-to-Many)
CREATE TABLE Recipe_Ingredients (
    recipe_ingredient_id INT PRIMARY KEY AUTO_INCREMENT,
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    unit_id INT NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units(unit_id) ON DELETE CASCADE,
    UNIQUE KEY unique_recipe_ingredient (recipe_id, ingredient_id)
);

-- Create Instructions Table
CREATE TABLE Instructions (
    instruction_id INT PRIMARY KEY AUTO_INCREMENT,
    recipe_id INT NOT NULL,
    step_number INT NOT NULL,
    instruction_text TEXT NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id) ON DELETE CASCADE,
    UNIQUE KEY unique_recipe_step (recipe_id, step_number)
);

-- =====================================================
-- INSERT UNITS OF MEASUREMENT
-- =====================================================

INSERT INTO units (unit_name, abbreviation) VALUES
('cup', 'cup'),
('tablespoon', 'tbsp'),
('teaspoon', 'tsp'),
('gram', 'g'),
('kilogram', 'kg'),
('milliliter', 'ml'),
('liter', 'l'),
('ounce', 'oz'),
('pound', 'lb'),
('can', 'can'),
('whole', 'whole'),
('clove', 'clove'),
('slice', 'slice');

-- =====================================================
-- INSERT INGREDIENTS
-- =====================================================

INSERT INTO ingredients (ingredient_name, category) VALUES
-- Pantry Staples
('All-purpose flour', 'Dry Goods'),
('Sugar', 'Dry Goods'),
('Brown sugar', 'Dry Goods'),
('Cornmeal', 'Dry Goods'),
('Oats', 'Dry Goods'),
('Pasta', 'Dry Goods'),
('Rice', 'Dry Goods'),
('Lentils', 'Dry Goods'),
('Olive oil', 'Oils'),
('Sesame oil', 'Oils'),
('Soy sauce', 'Sauces'),
('Mustard', 'Condiments'),
('Vinegar', 'Condiments'),
('Apple cider vinegar', 'Condiments'),
('Ketchup', 'Condiments'),
('Molasses', 'Sweeteners'),
('Salt', 'Seasonings'),
('Black pepper', 'Seasonings'),
('Red pepper flakes', 'Seasonings'),
('Cinnamon', 'Spices'),
('Nutmeg', 'Spices'),
('Cumin', 'Spices'),
('Turmeric', 'Spices'),
('Curry powder', 'Spices'),
('Chili powder', 'Spices'),
('Garlic powder', 'Spices'),
('Ginger powder', 'Spices'),
('Dried basil', 'Dried Herbs'),
('Dried oregano', 'Dried Herbs'),
('Dried thyme', 'Dried Herbs'),
('Dried parsley', 'Dried Herbs'),
('Italian seasoning', 'Dried Herbs'),
('Baking powder', 'Leavening'),
('Baking soda', 'Leavening'),

-- Canned & Preserved
('Canned black beans', 'Canned'),
('Canned kidney beans', 'Canned'),
('Canned chickpeas', 'Canned'),
('Canned navy beans', 'Canned'),
('Canned diced tomatoes', 'Canned'),
('Canned crushed tomatoes', 'Canned'),
('Canned tuna', 'Canned'),
('Canned coconut milk', 'Canned'),
('Canned mixed vegetables', 'Canned'),
('Raisins', 'Dried Fruits'),

-- Fresh Pantry Staples
('Garlic', 'Produce'),
('Onion', 'Produce'),
('Carrots', 'Produce'),

-- Refrigerated/Pantry Staples
('Butter', 'Dairy'),
('Eggs', 'Dairy'),
('Milk', 'Dairy'),
('Parmesan cheese', 'Dairy'),
('Cheddar cheese', 'Dairy'),
('Heavy cream', 'Dairy'),
('Bacon', 'Meat'),

-- Other
('Vanilla extract', 'Extracts'),
('Bread', 'Bakery'),
('Baguette', 'Bakery'),
('Flour tortillas', 'Bakery'),
('Chocolate chips', 'Pantry'),
('Bay leaf', 'Spices'),
('Polenta', 'Dry Goods'),
('Frozen peas and carrots', 'Frozen'),
('Green onions', 'Produce');

-- =====================================================
-- INSERT RECIPES
-- =====================================================

INSERT INTO recipes (recipe_id, recipe_name, description, duration_minutes, serving_size, calories_per_serving) VALUES
(1, 'Pasta Aglio e Olio', 'A classic Italian pasta dish with minimal ingredients, featuring spaghetti coated in garlic-infused olive oil with red pepper flakes.', "20 minutes", 4, "520 per serving"),
(2, 'Rice and Beans', 'A nutritious, protein-packed staple dish combining rice and beans with simple seasonings.', "30 minutes", 4, "380 per serving"),
(3, 'Garlic Bread', 'Crispy exterior with soft, garlicky interior—the perfect side dish or snack.', "15 minutes", 4, "285 per serving"),
(4, 'Simple Pasta Carbonara', 'A creamy Roman pasta made with eggs, cheese, and bacon—rich and satisfying.', "25 minutes", 4, "685 per serving"),
(5, 'Canned Tuna Pasta', 'Quick, protein-rich pasta dish perfect for weeknight dinners.', "20 minutes", 4, "420 per serving"),
(6, 'Lentil Soup', 'A hearty, budget-friendly soup loaded with protein and fiber.', "45 minutes", 6, "285 per serving"),
(7, 'Chickpea Curry', 'A flavorful, vegetarian curry with creamy coconut and warming spices.', "35 minutes", 4, "385 per serving"),
(8, 'Bean Chili', 'A warm, spiced chili loaded with beans and tomatoes—comfort food at its finest.', "40 minutes", 6, "270 per serving"),
(9, 'Egg Fried Rice', 'A quick, satisfying dish that uses leftover rice and pantry staples.', "15 minutes", 4, "360 per serving"),
(10, 'Simple Pancakes', 'Fluffy, classic pancakes—perfect for breakfast or brunch.', "20 minutes", 4, "215 per serving"),
(11, 'Cornbread', 'A slightly sweet, crumbly bread with a golden crust—great as a side dish.', "40 minutes", 9, "210 per serving"),
(12, 'Oatmeal Cookies', 'Chewy, filling cookies made with oats and simple pantry ingredients.', "25 minutes", 24, "130 per serving"),
(13, 'Tomato Soup', 'A smooth, comforting soup with a rich tomato flavor.', "30 minutes", 4, "285 per serving"),
(14, 'Minestrone Soup', 'A hearty vegetable and pasta soup—filling and nutritious.',"35 minutes", 6, "240 per serving"),
(15, 'Baked Beans', 'A classic side dish with a savory, slightly sweet sauce.', "70 minutes", 6, "320 per serving"),
(16, 'Vegetable Stir-Fry', 'A quick, flexible dish that works with any frozen or canned vegetables.', "15 minutes", 4, "220 per serving"),
(17, 'Creamy Polenta', 'A smooth, buttery Italian cornmeal dish that serves as a base for toppings.', "35 minutes", 4, "310 per serving"),
(18, 'Simple Quesadillas', 'Crispy-outside, cheesy-inside Mexican-inspired treats ready in minutes.', "15 minutes", 4, "385 per serving"),
(19, 'Rice Pudding', 'A creamy, comforting dessert with warm spices.',"50 minutes", 6, "340 per serving"),
(20, 'Pasta Primavera', 'A light pasta dish with canned vegetables and a simple sauce.',"25 minutes", 4, "445 per serving");

-- =====================================================
-- INSERT RECIPE INGREDIENTS
-- =====================================================

-- Recipe 1: Pasta Aglio e Olio
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pasta'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'lb')),
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 8, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Red pepper flakes'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried parsley'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp'));

-- Recipe 2: Rice and Beans
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Rice'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned black beans'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cumin'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 3: Garlic Bread
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Bread'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 6, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(3, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried parsley'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp'));

-- Recipe 4: Simple Pasta Carbonara
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pasta'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'lb')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Bacon'), 6, (SELECT unit_id FROM units WHERE abbreviation = 'oz')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Parmesan cheese'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(4, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 5: Canned Tuna Pasta
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pasta'), 12, (SELECT unit_id FROM units WHERE abbreviation = 'oz')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned tuna'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried oregano'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(5, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Red pepper flakes'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 6: Lentil Soup
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Lentils'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Carrots'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried thyme'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Bay leaf'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(6, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 7: Chickpea Curry
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned chickpeas'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned coconut milk'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Curry powder'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cumin'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Turmeric'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(7, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 8: Bean Chili
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned kidney beans'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned black beans'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Chili powder'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cumin'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(8, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 9: Egg Fried Rice
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Rice'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Soy sauce'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sesame oil'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Frozen peas and carrots'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(9, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Green onions'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 10: Simple Pancakes
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'All-purpose flour'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Baking powder'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Milk'), 1.75, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(10, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 11: Cornbread
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cornmeal'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'All-purpose flour'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 0.25, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Baking powder'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Milk'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(11, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp'));

-- Recipe 12: Oatmeal Cookies
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Brown sugar'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Vanilla extract'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'All-purpose flour'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Baking soda'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cinnamon'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Oats'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(12, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Raisins'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup'));

-- Recipe 13: Tomato Soup
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned crushed tomatoes'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Milk'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried basil'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(13, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 14: Minestrone Soup
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned kidney beans'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pasta'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Carrots'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried basil'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Dried oregano'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(14, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 15: Baked Beans
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned navy beans'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Molasses'), 0.25, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Ketchup'), 0.25, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Apple cider vinegar'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Mustard'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(15, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'tsp'));

-- Recipe 16: Vegetable Stir-Fry
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Frozen peas and carrots'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Soy sauce'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Vinegar'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sesame oil'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(16, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Ginger powder'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp'));

-- Recipe 17: Creamy Polenta
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Polenta'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Parmesan cheese'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(17, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic powder'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'tsp'));

-- Recipe 18: Simple Quesadillas
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Flour tortillas'), 8, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cheddar cheese'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned black beans'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Onion'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(18, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- Recipe 19: Rice Pudding
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Rice'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Milk'), 2, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Vanilla extract'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Cinnamon'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Nutmeg'), 0.25, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(19, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Raisins'), 0.5, (SELECT unit_id FROM units WHERE abbreviation = 'cup'));

-- Recipe 20: Pasta Primavera
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pasta'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'lb')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned mixed vegetables'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Canned diced tomatoes'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'can')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Garlic'), 4, (SELECT unit_id FROM units WHERE abbreviation = 'clove')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Olive oil'), 3, (SELECT unit_id FROM units WHERE abbreviation = 'tbsp')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Italian seasoning'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'tsp')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Parmesan cheese'), 0.25, (SELECT unit_id FROM units WHERE abbreviation = 'cup')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole')),
(20, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black pepper'), 1, (SELECT unit_id FROM units WHERE abbreviation = 'whole'));

-- =====================================================
-- USEFUL QUERIES
-- =====================================================

-- Query 1: View all recipes with basic info
-- SELECT recipe_name, description, duration_minutes, serving_size, calories_per_serving
-- FROM recipes
-- ORDER BY recipe_name;

-- Query 2: Get ingredients for a specific recipe
-- SELECT r.recipe_name, i.ingredient_name, ri.amount, u.abbreviation
-- FROM recipe_ingredients ri
-- JOIN recipes r ON ri.recipe_id = r.recipe_id
-- JOIN ingredients i ON ri.ingredient_id = i.ingredient_id
-- JOIN units u ON ri.unit_id = u.unit_id
-- WHERE r.recipe_name = 'Pasta Aglio e Olio'
-- ORDER BY ri.recipe_ingredient_id;

-- Query 3: Find recipes ready in under 20 minutes
-- SELECT recipe_name, duration_minutes, calories_per_serving
-- FROM recipes
-- WHERE duration_minutes <= 20
-- ORDER BY duration_minutes;

-- Query 4: Find recipes with less than 300 calories per serving
-- SELECT recipe_name, calories_per_serving, serving_size
-- FROM recipes
-- WHERE calories_per_serving < 300
-- ORDER BY calories_per_serving;

-- Query 5: Get all recipes that use a specific ingredient
-- SELECT DISTINCT r.recipe_name, r.duration_minutes, r.calories_per_serving
-- FROM recipes r
-- JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id
-- JOIN ingredients i ON ri.ingredient_id = i.ingredient_id
-- WHERE i.ingredient_name = 'Garlic'
-- ORDER BY r.recipe_name;

-- Query 6: Get total number of servings available across all recipes
-- SELECT SUM(serving_size) as total_servings, COUNT(DISTINCT recipe_id) as num_recipes
-- FROM recipes;

-- Query 7: Find recipes by category of ingredients
-- SELECT DISTINCT r.recipe_name
-- FROM recipes r
-- JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id
-- JOIN ingredients i ON ri.ingredient_id = i.ingredient_id
-- WHERE i.category = 'Spices'
-- ORDER BY r.recipe_name;

-- Query 8: Get cooking time and calorie summary
-- SELECT recipe_name, duration_minutes, calories_per_serving,
--        (calories_per_serving * serving_size) as total_calories
-- FROM recipes
-- ORDER BY duration_minutes;

-- Query 9: Find most expensive recipes by unique ingredient count
-- SELECT r.recipe_name, COUNT(DISTINCT ri.ingredient_id) as ingredient_count
-- FROM recipes r
-- JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id
-- GROUP BY r.recipe_id
-- ORDER BY ingredient_count DESC;

-- Query 10: Get ingredient frequency across all recipes
-- SELECT i.ingredient_name, COUNT(ri.recipe_id) as used_in_recipes
-- FROM ingredients i
-- LEFT JOIN recipe_ingredients ri ON i.ingredient_id = ri.ingredient_id
-- GROUP BY i.ingredient_id
-- ORDER BY used_in_recipes DESC;