package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.databinding.PantryItemLayoutBinding;
import com.example.foodie.models.PantryItem;

public class PantryItemDetailsFragment extends Fragment {

    private PantryItemLayoutBinding binding;
    private final PantryItem pantryItem = PantryListFragment.getItemClicked();

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
        super.onViewCreated(view, savedInstanceState);

        // displaying pantry item details
        displayDetails();
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


}
