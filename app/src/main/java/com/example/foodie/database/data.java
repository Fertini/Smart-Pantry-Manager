package com.example.foodie.database;

public class data {

    public static final String data = "INSERT INTO Recipes (recipe_name, description, ingredients, instructions, duration, serving_size, calories) VALUES " +

            // Recipe 1: Rice and Beans
            "('Rice and Beans','A nutritious, protein-packed staple dish combining rice and beans with simple seasonings.'," +
            "'[\"Cooked rice\",\"Canned black beans\",\"Canned diced tomatoes\",\"Onion\",\"Garlic\",\"Oil\",\"Cumin\",\"Salt and pepper\"]'," +
            "'[\"Heat oil in a large pot over medium heat\",\"Sauté onion until translucent (3-4 minutes)\",\"Add garlic and cook for 1 minute\",\"Stir in beans, tomatoes, and cumin\",\"Simmer for 10 minutes\",\"Combine with cooked rice\",\"Season with salt and pepper\"]'," +
            "30,4,380)," +

            // Recipe 2: Garlic Bread
            "('Garlic Bread','Crispy exterior with soft, garlicky interior—the perfect side dish or snack.'," +
            "'[\"Bread or baguette\",\"Butter\",\"Garlic\",\"Salt\",\"Black pepper\",\"Dried parsley\"]'," +
            "'[\"Preheat oven to 375°F\",\"Mix butter garlic, salt, pepper, and parsley in a bowl\",\"Slice bread in half lengthwise\",\"Spread garlic butter mixture evenly on cut sides\",\"Place on baking sheet cut-side up\",\"Bake for 8-10 minutes until golden and crispy\",\"Cool slightly before serving\"]'," +
            "15,4,285)," +

            // Recipe 3: Simple Pasta Carbonara
            "('Simple Pasta Carbonara','A creamy Roman pasta made with eggs, cheese, and bacon—rich and satisfying.'," +
            "'[\"Spaghetti\",\"Bacon, chopped\",\"Eggs\",\"Grated Parmesan cheese\",\"Garlic\",\"Salt and black pepper\"]'," +
            "'[\"Cook spaghetti according to package directions; reserve 1 cup pasta water\",\"While pasta cooks, fry bacon in a large pan until crispy; remove and set aside\",\"In a bowl, whisk together eggs, Parmesan, and black pepper\",\"Drain pasta and add to bacon pan (off heat)\",\"Quickly toss with egg mixture, adding pasta water gradually until creamy\",\"Return bacon to pan and toss gently\",\"Serve immediately\"]'," +
            "25,4,685)," +

            // Recipe 4: Canned Tuna Pasta
            "('Canned Tuna Pasta','Quick, protein-rich pasta dish perfect for weeknight dinners.'," +
            "'[\"Pasta\",\"Canned tuna\",\"Canned diced tomatoes\",\"Garlic\",\"Olive oil\",\"Dried oregano\",\"Salt and pepper\"]'," +
            "'[\"Cook pasta according to package directions\",\"Heat olive oil in a pan over medium heat\",\"Sauté garlic for 1 minute\",\"Add canned tomatoes and oregano\",\"Simmer for 5 minutes\",\"Stir in drained tuna and cook for 2 minutes\",\"Drain pasta and combine with tuna sauce\",\"Season with salt, pepper, and red pepper flakes\"]'," +
            "20,4,420)," +

            // Recipe 5: Lentil Soup
            "('Lentil Soup','A hearty, budget-friendly soup loaded with protein and fiber.'," +
            "'[\"Dried lentils\",\"Water or vegetable broth\",\"Onions\",\"Garlic\",\"Carrots\",\"Olive oil\",\"Dried thyme\",\"Bay leaf\",\"Salt and pepper\"]'," +
            "'[\"Heat olive oil in a large pot over medium heat\",\"Sauté onions until soft (4-5 minutes)\",\"Add garlic and carrots; cook for 2 minutes\",\"Add lentils, water, thyme, and bay leaf\",\"Bring to a boil, then reduce heat and simmer for 30 minutes until lentils are tender\",\"Remove bay leaf and season with salt and pepper\",\"Serve hot\"]'," +
            "45,6,285)," +

            // Recipe 6: Chickpea Curry
            "('Chickpea Curry','A flavourful, vegetarian curry with creamy coconut and warming spices.'," +
            "'[\"Canned chickpeas\",\"Canned coconut milk\",\"Canned diced tomatoes\",\"Onions\",\"Garlic\",\"Oil\",\"Curry powder\",\"Cumin\",\"Turmeric\",\"Salt and pepper\"]'," +
            "'[\"Heat oil in a large pan over medium heat\",\"Sauté onions until soft (4-5 minutes)\",\"Add garlic and cook for 1 minute\",\"Stir in curry powder, cumin, and turmeric; cook for 1 minute\",\"Add tomatoes, coconut milk, and chickpeas\",\"Simmer for 15-20 minutes\",\"Season with salt and pepper\",\"Serve over rice\"]'," +
            "35,4,385)," +

            // Recipe 7: Bean Chili
            "('Bean Chili','A warm, spiced chili loaded with beans and tomatoes—comfort food at its finest.'," +
            "'[\"Canned kidney beans\",\"Canned black beans\",\"Canned diced tomatoes\",\"Large onion\",\"Garlic\",\"Olive oil\",\"Chili powder\",\"Cumin\",\"Salt and pepper\"]'," +
            "'[\"Heat olive oil in a large pot over medium heat\",\"Sauté onion until soft (4-5 minutes)\",\"Add garlic and cook for 1 minute\",\"Stir in chili powder and cumin; cook for 1 minute\",\"Add all beans and tomatoes (with liquid)\",\"Bring to a boil, then reduce heat and simmer for 20-25 minutes\",\"Season with salt and pepper\",\"Serve hot with toppings if desired\"]'," +
            "40,6,270)," +

            // Recipe 8: Egg Fried Rice
            "('Egg Fried Rice','A quick, satisfying dish that uses leftover rice and pantry staples.'," +
            "'[\"Cooked rice\",\"Eggs, beaten\",\"Oil\",\"Garlic\",\"Soy sauce\",\"Sesame oil\",\"Frozen peas and carrots\",\"Green onions, chopped\"]'," +
            "'[\"Heat 1 tbsp oil in a large pan or wok over high heat\",\"Scramble eggs and set aside\",\"Add remaining oil and heat\",\"Add garlic and cook for 30 seconds\",\"Add cold rice, breaking up clumps, and stir-fry for 3-4 minutes\",\"Add peas and carrots, cooking for 2 minutes\",\"Return eggs to pan and stir in soy sauce and sesame oil\",\"Toss everything together and serve\"]'," +
            "15,4,360)," +

            // Recipe 9: Simple Pancakes
            "('Simple Pancakes','Fluffy, golden pancakes that are quick and easy to make.'," +
            "'[\"Flour\",\"Sugar\",\"Baking powder\",\"Salt\",\"Eggs\",\"Milk\",\"Melted butter\"]'," +
            "'[\"Mix flour, sugar, baking powder, and salt in a bowl\",\"In another bowl, whisk eggs, milk, and melted butter\",\"Combine wet and dry ingredients; mix until just combined (lumps are okay)\",\"Heat oil or butter on a griddle or pan over medium heat\",\"Pour ¼ cup batter per pancake\",\"Cook for 2-3 minutes until bubbles form on surface\",\"Flip and cook another 1-2 minutes until golden\",\"Serve hot with syrup\"]'," +
            "20,8,215)," +

            // Recipe 10: Cornbread
            "('Cornbread','A slightly sweet, crumbly bread with a golden crust—great as a side dish.'," +
            "'[\"Cornmeal\",\"All-purpose flour\",\"Sugar\",\"Baking powder\",\"Salt\",\"Milk\",\"Egg\",\"Melted butter\"]'," +
            "'[\"Preheat oven to 400°F\",\"Grease an 8x8 inch baking pan\",\"Mix cornmeal, flour, sugar, baking powder, and salt\",\"Whisk together milk, egg, and melted butter\",\"Combine wet and dry ingredients until just mixed\",\"Pour into prepared pan\",\"Bake for 20-25 minutes until golden brown\",\"Cool for 10 minutes before serving\"]'," +
            "40,9,210)," +

            // Recipe 11: Oatmeal Cookies
            "('Oatmeal Cookies','Chewy, filling cookies made with oats and simple pantry ingredients.'," +
            "'[\"Butter, softened\",\"Sugar\",\"Brown sugar\",\"Egg\",\"Vanilla extract\",\"All-purpose flour\",\"Baking soda\",\"Cinnamon\",\"Old-fashioned oats\",\"Raisins or chocolate chips\"]'," +
            "'[\"Preheat oven to 350°F\",\"Cream butter and sugars until fluffy (2-3 minutes)\",\"Beat in egg and vanilla\",\"Mix flour, baking soda, and cinnamon; add to butter mixture\",\"Fold in oats and raisins\",\"Drop spoonfuls onto baking sheets\",\"Bake for 10-12 minutes until edges are golden\",\"Cool on wire racks\"]'," +
            "25,24,130)," +

            // Recipe 12: Tomato Soup
            "('Tomato Soup','A smooth, comforting soup with a rich tomato flavor.'," +
            "'[\"Canned crushed tomatoes\",\"Onion\",\"Garlic\",\"Butter\",\"Vegetable broth\",\"Heavy cream or milk\",\"Sugar\",\"Dried basil\",\"Salt and pepper\"]'," +
            "'[\"Melt butter in a large pot over medium heat\",\"Sauté onion until soft (4-5 minutes)\",\"Add garlic and cook for 1 minute\",\"Add crushed tomatoes, broth, sugar, and basil\",\"Bring to a boil, then reduce heat and simmer for 15 minutes\",\"Blend soup until smooth (optional)\",\"Stir in cream\",\"Season with salt and pepper\",\"Serve hot\"]'," +
            "30,4,285)," +

            // Recipe 13: Minestrone Soup
            "('Minestrone Soup','A hearty vegetable and pasta soup—filling and nutritious.'," +
            "'[\"Olive oil\",\"Large onion\",\"Garlic\",\"Canned diced tomatoes\",\"Vegetable broth\",\"Canned kidney beans\",\"Small pasta\",\"Carrots\",\"Dried basil\",\"Dried oregano\",\"Salt and pepper\"]'," +
            "'[\"Heat olive oil in a large pot over medium heat\",\"Sauté onion until soft (4-5 minutes)\",\"Add garlic and cook for 1 minute\",\"Add tomatoes, broth, and carrots\",\"Bring to a boil and add pasta\",\"Simmer for 10 minutes until pasta is almost done\",\"Add beans, basil, and oregano\",\"Cook for another 5 minutes\",\"Season with salt and pepper\"]'," +
            "35,6,240)," +

            // Recipe 14: Baked Beans
            "('Baked Beans','A classic side dish with a savory, slightly sweet sauce.'," +
            "'[\"Canned beans\",\"Molasses or brown sugar\",\"Ketchup\",\"Apple cider vinegar\",\"Mustard\",\"Onion, finely diced\",\"Butter\",\"Salt\",\"Black pepper\"]'," +
            "'[\"Preheat oven to 325°F\",\"Melt butter in a baking dish over medium heat\",\"Sauté onion until soft (3-4 minutes)\",\"Add beans, molasses, ketchup, vinegar, and mustard\",\"Mix well and season with salt and pepper\",\"Cover and bake for 45 minutes, stirring halfway through\",\"Uncover and bake another 15 minutes\",\"Serve hot\"]'," +
            "70,6,320)," +

            // Recipe 15: Vegetable Stir-Fry
            "('Vegetable Stir-Fry','A quick, flexible dish that works with any frozen or canned vegetables.'," +
            "'[\"Oil\",\"Garlic\",\"Mixed vegetables (frozen)\",\"Soy sauce\",\"Vinegar\",\"Sesame oil\",\"Ginger powder\",\"Cooked rice for serving\"]'," +
            "'[\"Heat oil in a large pan or wok over high heat\",\"Add garlic and cook for 30 seconds\",\"Add vegetables and stir-fry for 5-7 minutes\",\"Mix soy sauce, vinegar, sesame oil, and ginger in a small bowl\",\"Pour sauce over vegetables and toss to coat\",\"Cook for another 2 minutes\",\"Serve over rice\"]'," +
            "15,4,220)," +

            // Recipe 16: Creamy Polenta
            "('Creamy Polenta','A smooth, buttery Italian cornmeal dish that serves as a base for toppings.'," +
            "'[\"Vegetable broth\",\"Cornmeal\",\"Butter\",\"Grated Parmesan cheese\",\"Salt and pepper\",\"Garlic powder\"]'," +
            "'[\"Bring broth to a boil in a large pot\",\"Slowly whisk in polenta to avoid lumps\",\"Reduce heat to medium-low and stir frequently\",\"Cook for 20-25 minutes, stirring often to prevent sticking\",\"Polenta is done when thick and pulls away from sides of pot\",\"Stir in butter, Parmesan, salt, pepper, and garlic powder\",\"Serve hot with desired toppings\"]'," +
            "35,4,310)," +

            // Recipe 17: Rice Pudding
            "('Rice Pudding','A creamy, comforting dessert with warm spices.'," +
            "'[\"Cooked rice\",\"Milk\",\"Sugar\",\"Eggs, beaten\",\"Butter\",\"Vanilla extract\",\"Cinnamon\",\"Nutmeg\",\"Salt\",\"Raisins\"]'," +
            "'[\"Preheat oven to 350°F\",\"Grease a 9x13 inch baking dish\",\"Mix rice, milk, sugar, eggs, butter, vanilla, cinnamon, nutmeg, and salt\",\"Fold in raisins if using\",\"Pour into baking dish\",\"Bake for 30-35 minutes until set but slightly jiggly in center\",\"Cool for 10 minutes before serving\",\"Can be served warm or chilled\"]'," +
            "50,6,340)," +

            // Recipe 18: Pasta Primavera
            "('Pasta Primavera','A light pasta dish with canned vegetables and a simple sauce.'," +
            "'[\"Pasta\",\"Canned mixed vegetables\",\"Canned diced tomatoes\",\"Garlic\",\"Olive oil\",\"Italian seasoning\",\"Vegetable broth\",\"Grated Parmesan cheese\",\"Salt and pepper\"]'," +
            "'[\"Cook pasta according to package directions\",\"Heat olive oil in a large pan over medium heat\",\"Sauté garlic for 1 minute\",\"Add canned tomatoes, vegetables, broth, and Italian seasoning\",\"Simmer for 10 minutes\",\"Drain pasta and add to the sauce\",\"Toss until coated\",\"Stir in Parmesan cheese\",\"Season with salt and pepper\",\"Serve hot\"]'," +
            "25,4,445);";
}