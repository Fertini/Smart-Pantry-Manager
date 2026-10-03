package com.example.foodie.fragments.pantryItemFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodie.R;
import com.example.foodie.databinding.PantryLayoutBinding;
import com.example.foodie.fragments.recipeFragments.SuggestedRecipesFragment;
import com.example.foodie.managers.PantryAdapter;
import com.example.foodie.managers.AlertViewModel;
import com.example.foodie.models.PantryItem;
import com.example.foodie.database.PantryItemDAO;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PantryListFragment extends Fragment {
    private PantryLayoutBinding binding;
    private PantryAdapter adapter;

    // ========== override methods ========== //
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        binding = PantryLayoutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // initialising an instance of the database
        PantryItemDAO.init(requireContext());

        // initialising the recycleView
        setupRecyclerView();

        // loading pantry Items from the database
        loadPantryItemsFromDatabase();

        // check if alerts should be created
        AlertViewModel alertsViewModel = new ViewModelProvider(requireActivity()).get(AlertViewModel.class); // bool value to check if alerts should still be sent, to prevent duplicate notifications

        // receive and check settings and display alerts
        SharedPreferences sharedPreferences = requireContext()
                .getSharedPreferences("save", Context.MODE_PRIVATE);
        // read the persisted value on startup
        boolean alertsEnabled = sharedPreferences.getBoolean("value", true);
        if(alertsEnabled && alertsViewModel.shouldShowAlerts()){
            getExpiring();
        }

        // read changes in real time
        getParentFragmentManager().setFragmentResultListener(
                "requestKey", getViewLifecycleOwner(), (requestKey, bundle) -> {
                    boolean result = bundle.getBoolean("alert_setting");
                    if(result && alertsViewModel.shouldShowAlerts()){
                        getExpiring();
                    }
                });



        // ------- btn onclick listeners --------- //
        binding.addPantryItemBtn.setOnClickListener(btn -> {
            // redirect user to the add pantry item screen
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddPantryItemFragment())
                    .addToBackStack(null)
                    .commit();
        });

        binding.suggestedRecipesBtn.setOnClickListener(btn -> {
            // redirect user to the suggested recipes screen
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SuggestedRecipesFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    // ========== class methods ========== //
    private void setupRecyclerView(){ // setting up recycler view and displaying item details on click
        List<PantryItem> pantryList = new ArrayList<>();
        adapter = new PantryAdapter(pantryList, pantryItem -> {
            PantryItemDetailsFragment pantryItemDetailsFragment = PantryItemDetailsFragment.newInstance(pantryItem);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, pantryItemDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });

        binding.pantryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.pantryRecyclerView.setAdapter(adapter);
    }

    private void loadPantryItemsFromDatabase(){
        List<PantryItem> pantryItems = PantryItemDAO.getAll();
        adapter.updateList(pantryItems);
    }

    public void getExpiring(){
        LocalDate date;
        ArrayList<PantryItem> expiring = new ArrayList<>();
        ArrayList<PantryItem> expired = new ArrayList<>();
        List<PantryItem> pantry = PantryItemDAO.getAll();

        // checking what is expiring or expired
        for (PantryItem item : pantry){
            date = LocalDate.parse(item.getExpiryDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            long difference = ChronoUnit.DAYS.between(LocalDate.now(), date);

            if(difference <= 0){
                expired.add(item);
            } else if (difference <= 3){
                expiring.add(item);
            }
        }

        if (!expiring.isEmpty()){
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Expiring Soon")
                    .setMessage(expiring.size() + " items are expiring soon")
                    .setNegativeButton("Close", null)
                    .show();
        }

        if (!expired.isEmpty()){
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Expired")
                    .setMessage(expired.size() + " items are expired. Would you like to remove them from the pantry")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        for (PantryItem item : expired){
                            PantryItemDAO.deletePantryItem(item.getPantryItemId());
                        }
                        Toast.makeText(getContext(), "Successfully removed items", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

}
