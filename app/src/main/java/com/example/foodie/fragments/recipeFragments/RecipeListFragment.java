package com.example.foodie.fragments.recipeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodie.databinding.RecipesLayoutBinding;
import com.example.foodie.managers.RecipeAdapter;
import com.example.foodie.models.Recipe;
import com.example.foodie.database.RecipeDAO;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment {
    private static Recipe recipeClicked;
    private RecipesLayoutBinding binding;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;

    public RecipeListFragment(){}

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
    private void setupRecyclerView(){
        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(recipeList, recipe -> recipeClicked = recipe);

        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeRecyclerView.setAdapter(adapter);
    }

    private void loadRecipesFromDatabase(){
        List<Recipe> recipes = RecipeDAO.getAll();
        adapter.updateList(recipes);
    }



    public static Recipe getRecipeClicked(){
        return recipeClicked;
    }
}
