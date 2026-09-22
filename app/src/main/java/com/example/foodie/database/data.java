package com.example.foodie.database;

public class data {

    public static final String data = "INSERT INTO recipes (recipe_name, description, ingredients, instructions, duration, serving_size, calories) VALUES " +

            // Recipe 1: Rice and Beans
            "('Rice and Beans','A nutritious, protein-packed staple dish combining rice and beans with simple seasonings.'," +
            "JSON_ARRAY(JSON_OBJECT('Cooked rice'),JSON_OBJECT('Canned black beans'),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Onion'),JSON_OBJECT('Garlic '),JSON_OBJECT('Oil'),JSON_OBJECT('Cumin'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Heat oil in a large pot over medium heat'),JSON_OBJECT('Sauté onion until translucent (3-4 minutes)'),JSON_OBJECT('Add garlic and cook for 1 minute'),JSON_OBJECT('Stir in beans, tomatoes, and cumin'),JSON_OBJECT('Simmer for 10 minutes')," +
            "JSON_OBJECT('Combine with cooked rice'),JSON_OBJECT('Season with salt and pepper')),30,4,380)," +

            // Recipe 2: Garlic Bread
            "('Garlic Bread','Crispy exterior with soft, garlicky interior—the perfect side dish or snack.'," +
            "JSON_ARRAY(JSON_OBJECT('Bread or baguette'),JSON_OBJECT('Butter'),JSON_OBJECT('Garlic '),JSON_OBJECT('Salt'),JSON_OBJECT('Black pepper'),JSON_OBJECT('Dried parsley'))," +
            "JSON_ARRAY(JSON_OBJECT('Preheat oven to 375°F'),JSON_OBJECT('Mix butter garlic, salt, pepper, and parsley in a bowl'),JSON_OBJECT('Slice bread in half lengthwise'),JSON_OBJECT('Spread garlic butter mixture evenly on cut sides')," +
            "JSON_OBJECT('Place on baking sheet cut-side up'),JSON_OBJECT('Bake for 8-10 minutes until golden and crispy'),JSON_OBJECT('Cool slightly before serving')),15,4,285)," +

            //Recipe 3: Simple Pasta Carbonara
            "('Simple Pasta Carbonara','A creamy Roman pasta made with eggs, cheese, and bacon—rich and satisfying.'," +
            "JSON_ARRAY(JSON_OBJECT('Spaghetti'),JSON_OBJECT('Bacon, chopped'),JSON_OBJECT('Eggs'),JSON_OBJECT('Grated Parmesan cheese'),JSON_OBJECT('Garlic '),JSON_OBJECT('Salt and black pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Cook spaghetti according to package directions; reserve 1 cup pasta water'),JSON_OBJECT('While pasta cooks, fry bacon in a large pan until crispy; remove and set aside'),JSON_OBJECT('In a bowl, whisk together eggs, Parmesan, and black pepper'),JSON_OBJECT('Drain pasta and add to bacon pan (off heat)')," +
            "JSON_OBJECT('Quickly toss with egg mixture, adding pasta water gradually until creamy'),JSON_OBJECT('Return bacon to pan and toss gently'),JSON_OBJECT('Serve immediately')), 25, 4, 685)," +

            // Recipe 4: Canned Tuna Pasta
            "('Canned Tuna Pasta','Quick, protein-rich pasta dish perfect for weeknight dinners.'," +
            "JSON_ARRAY(JSON_OBJECT('Pasta'),JSON_OBJECT('Canned tuna'),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Garlic '),JSON_OBJECT('Olive oil'),JSON_OBJECT('Dried oregano'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Cook pasta according to package directions'),JSON_OBJECT('Heat olive oil in a pan over medium heat'),JSON_OBJECT('Sauté garlic for 1 minute'),JSON_OBJECT('Add canned tomatoes and oregano'),JSON_OBJECT('Simmer for 5 minutes'),JSON_OBJECT('Stir in drained tuna and cook for 2 minutes')," +
            "JSON_OBJECT('Drain pasta and combine with tuna sauce'),JSON_OBJECT('Season with salt, pepper, and red pepper flakes')),20,4,420)," +

            // Recipe 5: Lentil Soup
            "('Lentil Soup','A hearty, budget-friendly soup loaded with protein and fiber.'," +
            "JSON_ARRAY(JSON_OBJECT('Dried lentils'),JSON_OBJECT('Water or vegetable broth'),JSON_OBJECT('Onions'),JSON_OBJECT('Garlic '),JSON_OBJECT('Carrots'),JSON_OBJECT('Olive oil'),JSON_OBJECT('Dried thyme'),JSON_OBJECT('Bay leaf'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Heat olive oil in a large pot over medium heat'),JSON_OBJECT('Sauté onions until soft (4-5 minutes)'),JSON_OBJECT('Add garlic and carrots; cook for 2 minutes'),JSON_OBJECT('Add lentils, water, thyme, and bay leaf'),JSON_OBJECT('Bring to a boil, then reduce heat and simmer for 30 minutes until lentils are tender')," +
            "JSON_OBJECT('Remove bay leaf and season with salt and pepper'),JSON_OBJECT('Serve hot')),45,6,285)," +

            // Recipe 6: Chickpea Curry
            "('Chickpea Curry','A flavourful, vegetarian curry with creamy coconut and warming spices.'," +
            "JSON_ARRAY(JSON_OBJECT('Canned chickpeas'),JSON_OBJECT('Canned coconut milk'),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Onions'),JSON_OBJECT('Garlic '),JSON_OBJECT('Oil'),JSON_OBJECT('Curry powder'),JSON_OBJECT('Cumin'),JSON_OBJECT('Turmeric'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Heat oil in a large pan over medium heat'),JSON_OBJECT('Sauté onions until soft (4-5 minutes)'),JSON_OBJECT('Add garlic and cook for 1 minute'),JSON_OBJECT('Stir in curry powder, cumin, and turmeric; cook for 1 minute'),JSON_OBJECT('Add tomatoes, coconut milk, and chickpeas')," +
            "JSON_OBJECT('Simmer for 15-20 minutes'),JSON_OBJECT('Season with salt and pepper'),JSON_OBJECT('Serve over rice')),35,4,385)," +

            // Recipe 7: Bean Chili
            "('Bean Chili','A warm, spiced chili loaded with beans and tomatoes—comfort food at its finest.'," +
            "JSON_ARRAY(JSON_OBJECT('Canned kidney beans'),JSON_OBJECT('Canned black beans'),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Large onion'),JSON_OBJECT('Garlic'),JSON_OBJECT('Olive oil'),JSON_OBJECT('Chili powder'),JSON_OBJECT('Cumin'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Heat olive oil in a large pot over medium heat'),JSON_OBJECT('Sauté onion until soft (4-5 minutes)'),JSON_OBJECT('Add garlic and cook for 1 minute'),JSON_OBJECT('Stir in chili powder and cumin; cook for 1 minute'),JSON_OBJECT('Add all beans and tomatoes (with liquid)')," +
            "JSON_OBJECT('Bring to a boil, then reduce heat and simmer for 20-25 minutes'),JSON_OBJECT('Season with salt and pepper'),JSON_OBJECT('Serve hot with toppings if desired')),40,6,270)," +

            // Recipe 8: Egg Fried Rice
            "('Egg Fried Rice','A quick, satisfying dish that uses leftover rice and pantry staples.'," +
            "JSON_ARRAY(JSON_OBJECT('Cooked rice'),JSON_OBJECT('Eggs, beaten'),JSON_OBJECT('Oil'),JSON_OBJECT('Garlic '),JSON_OBJECT('Soy sauce'),JSON_OBJECT('Sesame oil'),JSON_OBJECT('Frozen peas and carrots'),JSON_OBJECT('Green onions, chopped'))," +
            "JSON_ARRAY(JSON_OBJECT('Heat 1 tbsp oil in a large pan or wok over high heat'),JSON_OBJECT('Scramble eggs and set aside'),JSON_OBJECT('Add remaining oil and heat'),JSON_OBJECT('Add garlic and cook for 30 seconds'),JSON_OBJECT('Add cold rice, breaking up clumps, and stir-fry for 3-4 minutes')," +
            "JSON_OBJECT('Add peas and carrots, cooking for 2 minutes'),JSON_OBJECT('Return eggs to pan and stir in soy sauce and sesame oil'),JSON_OBJECT('Toss everything together and serve')),15,4,360)," +

            // Recipe 9: Simple Pancakes
            "('Simple Pancakes','Fluffy, classic pancakes—perfect for breakfast or brunch.'," +
            "JSON_ARRAY(JSON_OBJECT('All-purpose flour'),JSON_OBJECT('Sugar'),JSON_OBJECT('Baking powder'),JSON_OBJECT('Salt'),JSON_OBJECT('Eggs'),JSON_OBJECT('Milk'),JSON_OBJECT('Melted butter'),JSON_OBJECT('Oil or butter for cooking'))," +
            "JSON_ARRAY(JSON_OBJECT('Mix flour, sugar, baking powder, and salt in a bowl'),JSON_OBJECT('In another bowl, whisk eggs, milk, and melted butter'),JSON_OBJECT('Combine wet and dry ingredients; mix until just combined (lumps are okay)')," +
            "JSON_OBJECT('Heat oil or butter on a griddle or pan over medium heat'),JSON_OBJECT('Pour ¼ cup batter per pancake'),JSON_OBJECT('Cook for 2-3 minutes until bubbles form on surface'),JSON_OBJECT('Flip and cook another 1-2 minutes until golden'),JSON_OBJECT('Serve hot with syrup')),20,8,215)," +

            // Recipe 10: Cornbread
            "('Cornbread','A slightly sweet, crumbly bread with a golden crust—great as a side dish.'," +
            "JSON_ARRAY(JSON_OBJECT('Cornmeal'),JSON_OBJECT('All-purpose flour'),JSON_OBJECT('Sugar'),JSON_OBJECT('Baking powder'),JSON_OBJECT('Salt'),JSON_OBJECT('Milk'),JSON_OBJECT('Egg'),JSON_OBJECT('Melted butter'))," +
            "JSON_ARRAY(JSON_OBJECT('Preheat oven to 400°F'),JSON_OBJECT('Grease an 8x8 inch baking pan'),JSON_OBJECT('Mix cornmeal, flour, sugar, baking powder, and salt'),JSON_OBJECT('Whisk together milk, egg, and melted butter'),JSON_OBJECT('Combine wet and dry ingredients until just mixed')," +
            "JSON_OBJECT('Pour into prepared pan'),JSON_OBJECT('Bake for 20-25 minutes until golden brown'),JSON_OBJECT('Cool for 10 minutes before serving')),40,9,210)," +

            // Recipe 11: Oatmeal Cookies
            "('Oatmeal Cookies','Chewy, filling cookies made with oats and simple pantry ingredients.'," +
            "JSON_ARRAY(JSON_OBJECT('Butter, softened'),JSON_OBJECT('Sugar'),JSON_OBJECT('Brown sugar'),JSON_OBJECT('Egg'),JSON_OBJECT('Vanilla extract'),JSON_OBJECT('All-purpose flour'),JSON_OBJECT('Baking soda'),JSON_OBJECT('Cinnamon'),JSON_OBJECT('Old-fashioned oats'),JSON_OBJECT('Raisins or chocolate chips'))," +
            "JSON_ARRAY(JSON_OBJECT('Preheat oven to 350°F'),JSON_OBJECT('Cream butter and sugars until fluffy (2-3 minutes)'),JSON_OBJECT('Beat in egg and vanilla'),JSON_OBJECT('Mix flour, baking soda, and cinnamon; add to butter mixture'),JSON_OBJECT('Fold in oats and raisins'),JSON_OBJECT('Drop spoonfuls onto baking sheets')," +
            "JSON_OBJECT('Bake for 10-12 minutes until edges are golden'),JSON_OBJECT('Cool on wire racks')),25,24,130)," +

            // Recipe 12: Tomato Soup
            "('Tomato Soup','A smooth, comforting soup with a rich tomato flavor.'," +
            "JSON_ARRAY(JSON_OBJECT('Canned crushed tomatoes')JSON_OBJECT('Onion'),JSON_OBJECT('GarlicJSON_OBJECT('Butter'),JSON_OBJECT('Vegetable broth'),JSON_OBJECT('Heavy cream or milk'),JSON_OBJECT('Sugar'),JSON_OBJECT('Dried basil'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Melt butter in a large pot over medium heat'),JSON_OBJECT('Sauté onion until soft (4-5 minutes)'),JSON_OBJECT('Add garlic and cook for 1 minute'),JSON_OBJECT('Add crushed tomatoes, broth, sugar, and basil')JSON_OBJECT('Bring to a boil, then reduce heat and simmer for 15 minutes')," +
            "JSON_OBJECT('Blend soup until smooth (optional)'),JSON_OBJECT('Stir in cream'),JSON_OBJECT('Season with salt and pepper'),JSON_OBJECT('Serve hot')),30,4,285)," +

            // Recipe 13: Minestrone Soup
            "('Minestrone Soup','A hearty vegetable and pasta soup—filling and nutritious.',JSON_ARRAY(JSON_OBJECT('Olive oil'),JSON_OBJECT('Large onion'),JSON_OBJECT('Garlic '),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Vegetable broth'),JSON_OBJECT('Canned kidney beans'),JSON_OBJECT('Small pasta'),JSON_OBJECT('Carrots'),JSON_OBJECT('Dried basil'),JSON_OBJECT('Dried oregano'),JSON_OBJECT('Salt and pepper')),JSON_ARRAY(JSON_OBJECT('Heat olive oil in a large pot over medium heat'),JSON_OBJECT('Sauté onion until soft (4-5 minutes)'),JSON_OBJECT('Add garlic and cook for 1 minute'),JSON_OBJECT('Add tomatoes, broth, and carrots'),JSON_OBJECT('Bring to a boil and add pasta'),JSON_OBJECT('Simmer for 10 minutes until pasta is almost done'),JSON_OBJECT('Add beans, basil, and oregano'),JSON_OBJECT('Cook for another 5 minutes'),JSON_OBJECT('Season with salt and pepper')),35,6,240)," +

            // Recipe 14: Baked Beans
            "('Baked Beans','A classic side dish with a savory, slightly sweet sauce.'," +
            "JSON_ARRAY(JSON_OBJECT('Canned beans'),JSON_OBJECT('Molasses or brown sugar'),JSON_OBJECT('Ketchup'),JSON_OBJECT('Apple cider vinegar'),JSON_OBJECT('Mustard'),JSON_OBJECT('Onion, finely diced'),JSON_OBJECT('Butter'),JSON_OBJECT('Salt'),JSON_OBJECT('Black pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Preheat oven to 325°F'),JSON_OBJECT('Melt butter in a baking dish over medium heat'),JSON_OBJECT('Sauté onion until soft (3-4 minutes)'),JSON_OBJECT('Add beans, molasses, ketchup, vinegar, and mustard'),JSON_OBJECT('Mix well and season with salt and pepper')," +
            "JSON_OBJECT('Cover and bake for 45 minutes, stirring halfway through'),JSON_OBJECT('Uncover and bake another 15 minutes'),JSON_OBJECT('Serve hot')),70,6,320)," +

            // Recipe 15: Vegetable Stir-Fry
            "('Vegetable Stir-Fry','A quick, flexible dish that works with any frozen or canned vegetables.'," +
            "JSON_ARRAY(JSON_OBJECT('Oil'),JSON_OBJECT('Garlic '),JSON_OBJECT('Mixed vegetables (frozen)'),JSON_OBJECT('Soy sauce'),JSON_OBJECT('Vinegar'),JSON_OBJECT('Sesame oil'),JSON_OBJECT('Ginger powder'),JSON_OBJECT('Cooked rice for serving''))," +
            "JSON_ARRAY(JSON_OBJECT('Heat oil in a large pan or wok over high heat'),JSON_OBJECT('Add garlic and cook for 30 seconds'),JSON_OBJECT('Add vegetables and stir-fry for 5-7 minutes'),JSON_OBJECT('Mix soy sauce, vinegar, sesame oil, and ginger in a small bowl')," +
            "JSON_OBJECT('Pour sauce over vegetables and toss to coat'),JSON_OBJECT('Cook for another 2 minutes'),JSON_OBJECT('Serve over rice')),15,4,220)," +

            // Recipe 16: Creamy Polenta
            "('Creamy Polenta','A smooth, buttery Italian cornmeal dish that serves as a base for toppings.'," +
            "JSON_ARRAY(JSON_OBJECT('Vegetable broth'),JSON_OBJECT('Cornmeal'),JSON_OBJECT('Butter'),JSON_OBJECT('Grated Parmesan cheese'),JSON_OBJECT('Salt and pepper'),JSON_OBJECT('Garlic powder'))," +
            "JSON_ARRAY(JSON_OBJECT('Bring broth to a boil in a large pot'),JSON_OBJECT('Slowly whisk in polenta to avoid lumps'),JSON_OBJECT('Reduce heat to medium-low and stir frequently'),JSON_OBJECT('Cook for 20-25 minutes, stirring often to prevent sticking')," +
            "JSON_OBJECT('Polenta is done when thick and pulls away from sides of pot'),JSON_OBJECT('Stir in butter, Parmesan, salt, pepper, and garlic powder'),JSON_OBJECT('Serve hot with desired toppings')),35,4,310)," +

            // Recipe 17: Rice Pudding
            "('Rice Pudding', 'A creamy, comforting dessert with warm spices.'," +
            "JSON_ARRAY(JSON_OBJECT('Cooked rice'),JSON_OBJECT('Milk'),JSON_OBJECT('Sugar'),JSON_OBJECT('Eggs, beaten'),JSON_OBJECT('Butter'),JSON_OBJECT('Vanilla extract'),JSON_OBJECT('Cinnamon'),JSON_OBJECT('Nutmeg'),JSON_OBJECT('Salt'),JSON_OBJECT('Raisins'))," +
            "JSON_ARRAY(JSON_OBJECT('Preheat oven to 350°F'),JSON_OBJECT('Grease a 9x13 inch baking dish'),JSON_OBJECT('Mix rice, milk, sugar, eggs, butter, vanilla, cinnamon, nutmeg, and salt'),JSON_OBJECT('Fold in raisins if using'),JSON_OBJECT('Pour into baking dish')," +
            "JSON_OBJECT('Bake for 30-35 minutes until set but slightly jiggly in center'),JSON_OBJECT('Cool for 10 minutes before serving'),JSON_OBJECT('Can be served warm or chilled')), 50, 6, 340)," +

            // Recipe 18: Pasta Primavera
            "('Pasta Primavera', 'A light pasta dish with canned vegetables and a simple sauce.'," +
            "JSON_ARRAY(JSON_OBJECT('Pasta'),JSON_OBJECT('Canned mixed vegetables'),JSON_OBJECT('Canned diced tomatoes'),JSON_OBJECT('Garlic '),JSON_OBJECT('Olive oil'),JSON_OBJECT('Italian seasoning'),JSON_OBJECT('Vegetable broth'),JSON_OBJECT('Grated Parmesan cheese'),JSON_OBJECT('Salt and pepper'))," +
            "JSON_ARRAY(JSON_OBJECT('Cook pasta according to package directions'),JSON_OBJECT('Heat olive oil in a large pan over medium heat'),JSON_OBJECT('Sauté garlic for 1 minute'),JSON_OBJECT('Add canned tomatoes, vegetables, broth, and Italian seasoning')," +
            "JSON_OBJECT('Simmer for 10 minutes'),JSON_OBJECT('Drain pasta and add to the sauce'),JSON_OBJECT('Toss until coated'),JSON_OBJECT('Stir in Parmesan cheese'),JSON_OBJECT('Season with salt and pepper'),JSON_OBJECT('Serve hot')), 25, 4, 445)";
}