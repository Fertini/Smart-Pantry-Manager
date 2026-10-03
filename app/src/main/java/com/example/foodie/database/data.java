package com.example.foodie.database;

public class data {

    public static final String data = "INSERT INTO Recipes (recipe_name, description, ingredients, instructions, duration, serving_size, calories) VALUES " +

            // Recipe 1: Rice and Beans
            "('Rice and Beans','A nutritious, protein-packed staple dish combining rice and beans with simple seasonings.'," +
            "'[\"2 cups Cooked Rice\",\"2 cans Black beans\",\"1 can diced tomatoes\",\"1 Onion\",\"3 Garlic cloves\",\"Oil\",\"Cumin\",\"Salt\", \"pepper\"]'," +
            "'[\"Heat 2 tbsp of oil in a large pot over medium heat\",\"Dice and sauté onion until translucent (3-4 minutes)\",\"Finely chop add garlic and cook for 1 minute\"," +
            "\"Stir in drained and rinsed beans, tomatoes, and cumin\",\"Simmer for 10 minutes\",\"Combine with cooked rice\",\"Season with salt and pepper\"]'," +
            "30,4,380)," +

            // Recipe 2: Garlic Bread
            "('Garlic Bread','Crispy exterior with soft, garlicky interior—the perfect side dish or snack.'," +
            "'[\"1 loaf Bread\",\"Butter\",\"6 Garlic cloves\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Preheat oven to 190°C\",\"Mix 4 tbsp of butter, finely chopped garlic, 1tsp of salt, and 1tsp pepper in a bowl\",\"Slice bread in half lengthwise\"," +
            "\"Spread garlic butter mixture evenly on cut sides\",\"Place on baking sheet cut-side up\",\"Bake for 8-10 minutes until golden and crispy\",\"Cool slightly before serving\"]'," +
            "15,4,285)," +

            // Recipe 3: Simple Pasta Carbonara
            "('Simple Pasta Carbonara','A creamy Roman pasta made with eggs, cheese, and bacon—rich and satisfying.'," +
            "'[\"450g Spaghetti\",\"170g Bacon\",\"4 Eggs\",\"Parmesan cheese\",\"3 Garlic cloves\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Cook spaghetti according to package directions; reserve 1 cup pasta water\",\"While pasta cooks, chop and fry bacon and finely chopped garlic in a large pan until crispy; remove and set aside\"," +
            "\"In a bowl, whisk together eggs, 1 cup of grated parmesan cheese, and black pepper\",\"Drain pasta and add to bacon pan (off heat)\",\"Quickly toss with egg mixture, adding pasta water gradually until creamy\",\"Return bacon to pan and toss gently\",\"Serve immediately\"]'," +
            "25,4,685)," +

            // Recipe 4: Canned Tuna Pasta
            "('Canned Tuna Pasta','Quick, protein-rich pasta dish perfect for weeknight dinners.'," +
            "'[\"340g Pasta\",\"2 cans tuna\",\"1 can diced tomatoes\",\"3 Garlic cloves\",\"Olive oil\",\"Dried oregano\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Cook pasta according to package directions\",\"Heat 3tbsp olive oil in a pan over medium heat\",\"Sauté finely chopped garlic for 1 minute\"," +
            "\"Add canned tomatoes and 1tsp oregano\",\"Simmer for 5 minutes\",\"Stir in drained tuna and cook for 2 minutes\",\"Drain pasta and combine with tuna sauce\",\"Season with salt, pepper\"]'," +
            "20,4,420)," +

            // Recipe 5: Lentil Soup
            "('Lentil Soup','A hearty, budget-friendly soup loaded with protein and fiber.'," +
            "'[\"Lentils\",\"Water\",\"2 Onions\",\"4 Garlic cloves\",\"2 Carrots\",\"Olive oil\",\"Dried thyme\",\"1 Bay leaf\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Heat 3tbsp olive oil in a large pot over medium heat\",\"Dice and sauté onions until soft (4-5 minutes)\",\"Add finely chopped garlic and diced carrots; cook for 2 minutes\"," +
            "\"Add 2 cups of dried lentils, 8 cups of water, 2tsp dried thyme, and one bay leaf\",\"Bring to a boil, then reduce heat and simmer for 30 minutes until lentils are tender\",\"Remove bay leaf and season with salt and pepper\",\"Serve hot\"]'," +
            "45,6,285)," +

            // Recipe 6: Chickpea Curry
            "('Chickpea Curry','A flavourful, vegetarian curry with creamy coconut and warming spices.'," +
            "'[\"2 cans chickpeas\",\" 2 can coconut milk\",\"1 can diced tomatoes\",\"2 Onions\",\"3 Garlic cloves\",\"Oil\",\"Curry powder\",\"Cumin\",\"Turmeric\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Heat 2tbsp oil in a large pan over medium heat\",\"dice and sauté onions until soft (4-5 minutes)\",\"Add finely chopped garlic and cook for 1 minute\",\"Stir in 2tsp curry powder, 1tsp cumin, and 1tsp turmeric; cook for 1 minute\"," +
            "\"Add tomatoes, coconut milk, and chickpeas\",\"Simmer for 15-20 minutes\",\"Season with salt and pepper\",\"Serve over rice\"]'," +
            "35,4,385)," +

            // Recipe 7: Bean Chili
            "('Bean Chili','A warm, spiced chili loaded with beans and tomatoes—comfort food at its finest.'," +
            "'[\"2 cans kidney beans\",\"1 can black beans\",\"2 can diced tomatoes\",\"1 onion\",\"4 Garlic cloves\",\"Olive oil\",\"Chili powder\",\"Cumin\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Heat 2tbsp olive oil in a large pot over medium heat\",\"Dice and sauté onion until soft (4-5 minutes)\",\"Add finely chopped garlic and cook for 1 minute\",\"Stir in 2tbsp chili powder and 1tsp cumin; cook for 1 minute\"," +
            "\"Add all beans(drained) and tomatoes (with liquid)\",\"Bring to a boil, then reduce heat and simmer for 20-25 minutes\",\"Season with salt and pepper\",\"Serve hot with toppings if desired\"]'," +
            "40,6,270)," +

            // Recipe 8: Egg Fried Rice
            "('Egg Fried Rice','A quick, satisfying dish that uses leftover rice and pantry staples.'," +
            "'[\"Rice\",\"3 Eggs\",\"Oil\",\"4 Garlic cloves\",\"Soy sauce\",\"Frozen peas and carrots\"]'," +
            "'[\"Prepare 3 cups of cooked rice\",\"Heat 1tbsp oil in a large pan or wok over high heat\",\"Beat and scramble the eggs and set aside\",\"Add 2tbsp oil and heat\",\"Add finely chopped garlic and cook for 30 seconds\",\"Add cold rice, breaking up clumps, and stir-fry for 3-4 minutes\"," +
            "\"A half cup of peas and carrots, cook for 2 minutes\",\"Return eggs to pan and stir in 3tbsp soy sauce and optionally add 1tsp of sesame oil\",\"Toss everything together and serve\"]'," +
            "15,4,360)," +

            // Recipe 9: Simple Pancakes
            "('Simple Pancakes','Fluffy, golden pancakes that are quick and easy to make.'," +
            "'[\"All-purpose Flour\",\"25g Sugar\",\"Baking powder\",\"Salt\",\"2 Eggs\",\"450ml Milk\",\"30g Butter\",\"Oil\"]'," +
            "'[\"Mix 2 cups of flour, sugar, 2 tsp baking powder, and 1tsp salt in a bowl\",\"In another bowl, whisk eggs, milk, and melted butter\",\"Combine wet and dry ingredients; mix until just combined (lumps are okay)\"," +
            "\"Heat oil or butter on a griddle or pan over medium heat\",\"Pour ¼ cup batter per pancake\",\"Cook for 2-3 minutes until bubbles form on surface\",\"Flip and cook another 1-2 minutes until golden\",\"Serve hot with syrup\"]'," +
            "20,8,215)," +

            // Recipe 10: Cornbread
            "('Cornbread','A slightly sweet, crumbly bread with a golden crust—great as a side dish.'," +
            "'[\"Cornmeal\",\"All-purpose flour\",\"310g Sugar\",\"Baking powder\",\"Salt\",\"250ml Milk\",\"1 Egg\",\"40g Butter\"]'," +
            "'[\"Preheat oven to 204°C\",\"Grease an 8x8 inch baking pan\",\"Mix 1 cup of cornmeal, 1 cup of flour, sugar, 1tbsp baking powder, and 1tsp salt\",\"Whisk together milk, egg, and melted butter\",\"Combine wet and dry ingredients until just mixed\"," +
            "\"Pour into prepared pan\",\"Bake for 20-25 minutes until golden brown\",\"Cool for 10 minutes before serving\"]'," +
            "40,9,210)," +

            // Recipe 11: Oatmeal Cookies
            "('Oatmeal Cookies','Chewy, filling cookies made with oats and simple pantry ingredients.'," +
            "'[\"100g Butter\",\"Sugar\",\"Brown sugar\",\"1 Egg\",\"Vanilla extract\",\"All-purpose flour\",\"Baking soda\",\"Cinnamon\",\"Oats\"]'," +
            "'[\"Preheat oven to 176°C\",\"Cream butter and half cup of sugar and half cup of brown sugar until fluffy (2-3 minutes)\",\"Beat in egg and vanilla\",\"Mix 1 cup of flour, 1tsp baking soda, and 1tsp cinnamon; add to butter mixture\",\"Fold in oats and (optionally) raisins or chocolate chips\",\"Drop spoonfuls onto baking sheets\"," +
            "\"Bake for 10-12 minutes until edges are golden\",\"Cool on wire racks\"]'," +
            "25,24,130)," +

            // Recipe 12: Tomato Soup
            "('Tomato Soup','A smooth, comforting soup with a rich tomato flavor.'," +
            "'[\"2 cans crushed tomatoes\",\"1 Onion\",\"3 Garlic cloves\",\"30g Butter\",\"Vegetable Stock\",\"125ml Heavy cream\",\"Sugar\",\"Dried basil\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Melt butter in a large pot over medium heat\",\"Dice sauté onion until soft (4-5 minutes)\",\"Add finely chopped garlic and cook for 1 minute\",\"Infuse 2 blocks of vegetable stock to make vegetable broth then add crushed tomatoes, broth, 1tsp of sugar, and 1tsp basil\",\"Bring to a boil, then reduce heat and simmer for 15 minutes\"," +
            "\"You may optionally blend soup until smooth\",\"Stir in cream (or milk as substitute)\",\"Season with salt and pepper\",\"Serve hot\"]'," +
            "30,4,285)," +

            // Recipe 13: Vegetable Stir-Fry
            "('Vegetable Stir-Fry','A quick, flexible dish that works with any frozen or canned vegetables.'," +
            "'[\"Oil\",\"4 Garlic cloves\",\"Mixed vegetables\",\"Soy sauce\",\"Vinegar\",\"Sesame oil\",\"Ginger\"]'," +
            "'[\"Heat 3tbsp of oil in a large pan or wok over high heat\",\"Add finely chopped garlic and cook for 30 seconds\",\"Add 4 cups of mixed vegetables and stir-fry for 5-7 minutes\",\"Mix 3tbsp of soy sauce, 1tbsp of vinegar, 1tbsp of sesame oil, and 1tsp of ginger(powder or fresh) in a small bowl\"," +
            "\"Pour sauce over vegetables and toss to coat\",\"Cook for another 2 minutes\",\"Can be served with cooked rice\"]'," +
            "15,4,220)," +

            // Recipe 14: Rice Pudding
            "('Rice Pudding','A creamy, comforting dessert with warm spices.'," +
            "'[\"Rice\",\"500ml Milk\",\"100g Sugar\",\"2 Eggs\",\"40g Butter\",\"Vanilla extract\",\"Cinnamon\",\"Nutmeg\",\"Salt\"]'," +
            "'[\"Preheat oven to 176°C\",\"Grease a 9x13 inch baking dish\",\"Mix 3 cups of cooked rice, milk, sugar, eggs(beaten), butter, 1tsp vanilla extract, half tsp of cinnamon, quater tsp nutmeg, and a pinch salt\",\"Pour into baking dish\",\"Bake for 30-35 minutes until set but slightly jiggly in center\"," +
            "\"Cool for 10 minutes before serving\",\"Can be served warm or chilled\"]'," +
            "50,6,340)," +

            // Recipe 15: Pasta Primavera
            "('Pasta Primavera','A light pasta dish with canned vegetables and a simple sauce.'," +
            "'[\"Pasta\",\"Canned mixed vegetables\",\"Canned diced tomatoes\",\"Garlic\",\"Olive oil\",\"Italian seasoning\",\"Vegetable broth\",\"Grated Parmesan cheese\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Cook pasta according to package directions\",\"Heat olive oil in a large pan over medium heat\",\"Sauté garlic for 1 minute\",\"Add canned tomatoes, vegetables, broth, and Italian seasoning\",\"Simmer for 10 minutes\",\"Drain pasta and add to the sauce\",\"Toss until coated\",\"Stir in Parmesan cheese\",\"Season with salt and pepper\",\"Serve hot\"]'," +
            "25,4,445);";
}