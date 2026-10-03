package com.example.foodie.fragments.recipeFragments;

import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.databinding.RecipesLayoutBinding;
import com.example.foodie.R;
import com.example.foodie.managers.BasicMatcher;
import com.example.foodie.managers.RecipeAdapter;
import com.example.foodie.models.PantryItem;
import com.example.foodie.models.Recipe;
import com.example.foodie.database.RecipeDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuggestedRecipesFragment extends Fragment {
    private RecipesLayoutBinding binding;
    private RecipeAdapter adapter;

    public SuggestedRecipesFragment(){}

    // ========== Override methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = RecipesLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // initialising an instance of the database
        RecipeDAO.init(requireContext());

        // initialising the recycleView
        setupRecyclerView();

        // loading recipes from the database
        loadRecipesFromDatabase();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //
    private void setupRecyclerView(){ // setting up recycler view and displaying recipe details on click
        List<Recipe> recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(recipeList, recipe -> {

            // redirect user the recipe details screen
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, RecipeDetailsFragment.newInstance(recipe))
                    .addToBackStack(null)
                    .commit();

        });

        if(recipeList.isEmpty()){ // if there are no matching recipes
//            binding.recipeRecyclerView.addView( view ->{
//
//            });
        }

        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeRecyclerView.setAdapter(adapter);
    }

    private void loadRecipesFromDatabase(){
        List<Recipe>DBrecipeList = RecipeDAO.getAll();
        List<Recipe> recipeList = new ArrayList<>();

        for (Recipe recipe : DBrecipeList){
            if(hasIngredients(recipe)){
                recipeList.add(recipe);
            }
        }

        adapter.updateList(recipeList);
    }

    private boolean hasIngredients(Recipe recipe) {
        List<PantryItem> DBpantry = PantryItemDAO.getAll();
        List<String> requiredIngredients = recipe.getIngredients();

        // Track which pantry items have been matched
        Set<PantryItem> matchedItems = new HashSet<>();

        for (String ingredient : requiredIngredients) {
            PantryItem bestMatch = null;
            int bestDistance = Integer.MAX_VALUE;

            // Find best match for this ingredient
            for (PantryItem pantryItem : DBpantry) {
                int distance = BasicMatcher.getLevenshteinDistance(
                        pantryItem.getQuantity()+pantryItem.getUnit()+pantryItem.getName().toLowerCase(),
                        ingredient.toLowerCase()
                );

                // only assign if the match is better than the previous match,
                // if the item has not been matched only if it is within the distance threshold
                if (distance < bestDistance && distance <= 4 && !matchedItems.contains(pantryItem)) {
                    bestMatch = pantryItem;
                    bestDistance = distance;
                }
            }

            if (bestMatch == null) {
                return false; // pantry is missing an ingredient
            }

            matchedItems.add(bestMatch); // Mark as used to avoid duplicate matching
        }

        return true; // All ingredients are in the pantry
    }
}
