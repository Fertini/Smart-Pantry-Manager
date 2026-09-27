package com.example.foodie.fragments.pantryItemFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodie.R;
import com.example.foodie.databinding.PantryLayoutBinding;
import com.example.foodie.managers.PantryAdapter;
import com.example.foodie.models.PantryItem;
import com.example.foodie.database.PantryItemDAO;

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

            requireActivity().getSupportFragmentManager().beginTransaction()
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
}
