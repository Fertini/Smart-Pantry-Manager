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

import java.util.List;

public class RecipeListFragment extends Fragment {
    private static Recipe recipeClicked;
    private RecipesLayoutBinding binding;
    private RecipeAdapter adapter;
    private final List<Recipe> recipeList = RecipeDAO.getAll();

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

        // initialising an instance of the database
        RecipeDAO.init(requireContext());

        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeRecyclerView.setAdapter(new RecipeAdapter(recipeList, recipe -> recipeClicked = recipe));

        // loading recipes from the database
        getRecipes();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //

    // getting data
    public void getRecipes(){
        adapter.updateList(recipeList);
    }
    // getting tapped recipe
    public static Recipe getRecipeClicked(){
        return recipeClicked;
    }
}
