package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.databinding.UpdatePantryItemLayoutBinding;
import com.example.foodie.models.PantryItem;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class EditPantryItemFragment extends Fragment {
    private UpdatePantryItemLayoutBinding binding;
    private PantryItem pantryItem;

    public EditPantryItemFragment(){}

    // ========== Override Methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = UpdatePantryItemLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        PantryItemDAO.init(requireContext()); // initialising database

        // Get pantryItem from arguments
        if (getArguments() != null) {
            pantryItem = (PantryItem) getArguments().getSerializable("pantryItem");
        }

        // display pantryItem details
        if (pantryItem != null) {
            displayDetails();
        }

        // spinner
        selector();

        // --------- onclick listeneer for editing pantryItem -------- //
        binding.editPantryItemBtn.setOnClickListener(btn ->{

            if(isValid()) {

                pantryItem.setName(binding.pantryItemName.getText().toString().trim());
                pantryItem.setQuantity(Integer.parseInt(binding.pantryItemQuantity.getText().toString().trim()));
                pantryItem.setExpiryDate(binding.pantryItemExpiryDate.getText().toString());
                pantryItem.setUnit(binding.pantryItemUnit.getSelectedItem().toString());

                if(PantryItemDAO.updatePantryItem(pantryItem)){
                    Toast.makeText(getContext(), "Successfully updated item", Toast.LENGTH_SHORT);
                    // redirect user to view their changes
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, PantryItemDetailsFragment.newInstance(pantryItem))
                            .addToBackStack(null)
                            .commit();
                } else {
                    // failure message
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle("Operation Failed")
                            .setMessage("Failed to update pantryItem")
                            .setPositiveButton("Ok", null)
                            .show();
                }
            } else {
                // alert that one or more fields are empty
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Input Error")
                        .setMessage("One or more fields are empty")
                        .setPositiveButton("Ok", null)
                        .show();
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== Class Methods ========== //

    public void displayDetails(){
        binding.pantryItemName.setText(pantryItem.getName());
        binding.pantryItemQuantity.setText(String.valueOf(pantryItem.getQuantity()));
        binding.pantryItemUnit.setSelection(0);
        binding.pantryItemExpiryDate.setText(pantryItem.getExpiryDate());
    }

    public static EditPantryItemFragment newInstance(PantryItem pantryItem){
        EditPantryItemFragment fragment = new EditPantryItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("pantryItem", pantryItem);
        fragment.setArguments(args);
        return fragment;
    }

    public void selector(){
        Spinner spinner = binding.pantryItemUnit;

        List<String> units = new ArrayList<>();
        units.add("Select an option");
        units.add("kilogram(s)");
        units.add("gram(s)");
        units.add("litre(s)");
        units.add("millilitre(s)");
        units.add("cup(s)");
        units.add("can(s)");
        units.add("none");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                units
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Make a selection")
                        .setPositiveButton("Ok", null)
                        .show();
            }
        });
    }
    public boolean isValid(){
        boolean valid = true;

        String name = binding.pantryItemName.getText().toString().trim();
        String quantity = binding.pantryItemQuantity.getText().toString().trim();
        Object selectedUnit = binding.pantryItemUnit.getSelectedItem();
        String unit = selectedUnit == null ? "" : selectedUnit.toString().trim();

        // Name
        if (name.isEmpty()) {
            binding.pantryItemName.setError("Enter a name for the item");
            valid = false;
        }

        // Quantity
        if (quantity.isEmpty()) {
            binding.pantryItemQuantity.setError("Enter a quantity");
            valid = false;
        } else {
            try {
                int qty = Integer.parseInt(quantity);
                if (qty <= 0) {
                    binding.pantryItemQuantity.setError("Quantity must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                binding.pantryItemQuantity.setError("Quantity should only be a whole number");
                valid = false;
            }
        }

        // Unit
        if (unit.isEmpty() || unit.equals("Select an option")) {
            View selected = binding.pantryItemUnit.getSelectedView();
            if (selected instanceof TextView) {
                ((TextView) selected).setError("Select a unit of measurement");
            }
            Toast.makeText(getContext(), "Select a unit of measurement", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
}
