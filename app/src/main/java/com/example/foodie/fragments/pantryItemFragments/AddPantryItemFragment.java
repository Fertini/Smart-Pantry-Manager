package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.database.PantryItemDAO;
import com.example.foodie.databinding.AddPantryItemLayoutBinding;
import com.example.foodie.models.PantryItem;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Locale;


public class AddPantryItemFragment extends Fragment {
    private AddPantryItemLayoutBinding binding;
    private PantryItem pantryItem;
    AddPantryItemFragment(){}

    // ========== Override Methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = com.example.foodie.databinding.AddPantryItemLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        PantryItemDAO.init(requireContext()); // initialising database

        // text watcher
        EditText date = requireActivity().findViewById(R.id.pantry_item_expiry_date);
        date.addTextChangedListener(textWatcher());

        // spinner
        selector();

        // ---------------- on click listeners for adding item --------------- //
        binding.addPantryItemBtn.setOnClickListener(btn -> { // click handler for adding new pantryItem
            if(isValid()){

                String name = binding.pantryItemName.getText().toString().trim();
                int quantity = Integer.parseInt(binding.pantryItemQuantity.getText().toString().trim());
                String expiry = binding.pantryItemExpiryDate.getText().toString();
                String unit = binding.pantryItemUnit.getSelectedItem().toString();
                pantryItem = new PantryItem(name, quantity, unit, expiry); // create new pantryItem

                boolean result = PantryItemDAO.addPantryItem(pantryItem); // add to database

                if (result){
                    // redirect user to see the pantryItem list
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new PantryListFragment())
                            .addToBackStack(null)
                            .commit();
                    Toast.makeText(getContext(), "Item added successfully", Toast.LENGTH_SHORT).show(); // success message

                } else {
                    // failure message
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle("Operation Failed")
                            .setMessage("Failed to save item")
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
    public TextWatcher textWatcher(){
        EditText date = new EditText(requireContext());
        // text watcher for date entry
        TextWatcher tw = new TextWatcher() {
            private boolean updating = false;
            private final Calendar cal = Calendar.getInstance();

            @Override
            public void afterTextChanged(Editable s) {
                if(updating) return;
                // check how many digits are before the cursor?
                int cursor = Math.max(date.getSelectionStart(), 0);
                int digitsBeforeCursor = 0;
                for (int i = 0; i < cursor && i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i))) digitsBeforeCursor++;
                }

                // keep digits only, max 8 digits for full date
                String digits = s.toString().replaceAll("\\D", "");
                if (digits.length() > 8) digits = digits.substring(0, 8);
                digitsBeforeCursor = Math.min(digitsBeforeCursor, digits.length());

                // Validate only when the date is complete
                if (digits.length() == 8) {
                    int day   = Integer.parseInt(digits.substring(0, 2));
                    int month = Integer.parseInt(digits.substring(2, 4));
                    int year  = Integer.parseInt(digits.substring(4, 8));

                    int minYear = Calendar.getInstance().get(Calendar.YEAR);
                    month = Math.max(1, Math.min(month, 12));
                    year  = Math.max(minYear, Math.min(year, 2100));

                    cal.clear();
                    cal.set(year, month - 1, 1);
                    day = Math.max(1, Math.min(day, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));

                    digits = String.format(Locale.ENGLISH, "%02d%02d%04d", day, month, year);
                }

                // Rebuild date with slashes (only add a slash when more digits follow)
                StringBuilder sb = new StringBuilder();
                int newCursor = 0, digitsSeen = 0;
                for (int i = 0; i < digits.length(); i++) {
                    if (i == 2 || i == 4) sb.append('/');
                    sb.append(digits.charAt(i));
                    digitsSeen++;
                    if (digitsSeen == digitsBeforeCursor) newCursor = sb.length();
                }
                if (digitsBeforeCursor == 0) newCursor = 0;

                // Apply to the Editable directly, without setText()
                String formatted = sb.toString();
                if (!formatted.equals(s.toString())) {
                    updating = true;
                    s.replace(0, s.length(), formatted);
                    updating = false;
                }
                date.setSelection(Math.min(newCursor, date.getText().length()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };

        return tw;
    }
    public boolean isValid(){
        boolean valid = true;

        String name = binding.pantryItemName.getText().toString().trim();
        String quantity = binding.pantryItemQuantity.getText().toString().trim();
        Object selectedUnit = binding.pantryItemUnit.getSelectedItem();
        String unit = selectedUnit == null ? "" : selectedUnit.toString().trim();
        String expiry = binding.pantryItemExpiryDate.getText().toString().trim();

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

        // Expiry
        if (expiry.isEmpty()) {
            binding.pantryItemExpiryDate.setError("Enter an expiry date");
            valid = false;
        }

        return valid;
    }
}
