package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.databinding.PantryItemLayoutBinding;
import com.example.foodie.models.PantryItem;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

        // onclick listeners
        binding.editPantryItemBtn.setOnClickListener(btn ->{

            // redirect user to the edit pantry item screen
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EditPantryItemFragment.newInstance(pantryItem))
                    .addToBackStack(null)
                    .commit();
        });

        binding.deletePantryItemBtn.setOnClickListener(btn ->{
            new MaterialAlertDialogBuilder(getContext()) // confirmation for deletion
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete item?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        PantryItemDAO.init(getContext());
                        boolean result = PantryItemDAO.deletePantryItem(pantryItem.getPantryItemId()); // delete in database

                        if (result) { // if pantry item is successfully deleted
                            getParentFragmentManager().beginTransaction() // redirect user to the pantry list
                                    .replace(R.id.fragment_container, new PantryListFragment())
                                    .addToBackStack(null)
                                    .commit();
                            Toast.makeText(getContext(), "Successfully deleted item", Toast.LENGTH_SHORT).show(); // success message

                        } else {
                            new MaterialAlertDialogBuilder(getContext()) // failure message
                                    .setTitle("Operation Failed")
                                    .setMessage("Failed to delete item")
                                    .setPositiveButton("Ok", null)
                                    .show(); // failure message
                        }
                    })
                    .setNegativeButton("No", null) // cancel operation
                    .show()
                    .setCanceledOnTouchOutside(false);
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== class methods =========== //
    public void displayDetails(){
        binding.pantryItemName.setText(pantryItem.getName());
        binding.pantryItemQuantity.setText(String.valueOf(pantryItem.getQuantity()));
        binding.pantryItemUnit.setText(pantryItem.getUnit());
        binding.pantryItemExpiryDate.setText(pantryItem.getExpiryDate());
    }

    public static PantryItemDetailsFragment newInstance(PantryItem pantryItem){
        PantryItemDetailsFragment fragment = new PantryItemDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("pantry_item", pantryItem);
        fragment.setArguments(args);
        return fragment;
    }



}
