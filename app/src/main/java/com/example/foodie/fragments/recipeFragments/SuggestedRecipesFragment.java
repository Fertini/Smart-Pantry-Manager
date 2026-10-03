package com.example.foodie.fragments.recipeFragments;

import android.os.Bundle;
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
import com.example.foodie.managers.RecipeAdapter;
import com.example.foodie.managers.TextCleaner;
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

        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeRecyclerView.setAdapter(adapter);
    }

    private void loadRecipesFromDatabase(){
        List<Recipe>DBrecipeList = RecipeDAO.getAll();
        List<Recipe> recipeList = new ArrayList<>();

        for (Recipe recipe : DBrecipeList){
            if(hasIngredients(recipe, PantryItemDAO.getAll())){
                recipeList.add(recipe);
            }
        }

        adapter.updateList(recipeList);
    }

    private boolean hasIngredients(Recipe recipe, List<PantryItem> pantry) {
        Set<PantryItem> used = new HashSet<>();

        for (String ingredient : recipe.getIngredients()) {
            PantryItem match = null;
            for (PantryItem item : pantry) {
                if (!used.contains(item) && TextCleaner.namesMatch(ingredient, item.getName())) {
                    match = item;
                    break;
                }
            }
            if (match == null) return false;
            used.add(match);
        }
        return true;
    }
}
