package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.databinding.PantryItemLayoutBinding;
import com.example.foodie.fragments.recipeFragments.RecipeDetailsFragment;
import com.example.foodie.models.PantryItem;

public class PantryItemDetailsFragment extends Fragment {

    private PantryItemLayoutBinding binding;
    private PantryItem pantryItem;

    public PantryItemDetailsFragment(){}

    // ========== override methods ========== //

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = PantryItemLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        // get pantry items from arguments
        if (getArguments() != null){
            pantryItem = (PantryItem) getArguments().getSerializable("pantry_item");
        }

        // displaying pantry item details
        if(pantryItem != null){
            displayDetails();
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== class methods =========== //
    public void displayDetails(){
        binding.pantryItemName.setText(pantryItem.getName());
        binding.pantryItemQuantity.setText(pantryItem.getQuantity());
        binding.pantryItemUnit.setText(pantryItem.getUnit());
        binding.pantryItemExpiryDate.setText(String.valueOf(pantryItem.getExpiryDate()));
    }

    public static PantryItemDetailsFragment newInstance(PantryItem pantryItem){
        PantryItemDetailsFragment fragment = new PantryItemDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("pantry_item", pantryItem);
        fragment.setArguments(args);
        return fragment;
    }

}
