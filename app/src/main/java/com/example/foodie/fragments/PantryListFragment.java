package com.example.foodie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
n
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodie.databinding.PantryItemLayoutBinding;
import com.example.foodie.managers.PantryAdapter;
import com.example.foodie.models.PantryItem;
import com.example.foodie.database.PantryItemDAO;

import java.util.ArrayList;
import java.util.List;

public class PantryListFragment extends Fragment {

    private PantryItemLayoutBinding binding;
    private PantryAdapter adapter;
    private List<PantryItem> pantryList;

    public PantryListFragment(){}

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

        // initialising the recycleView
        setupRecyclerView();

        // loading pantry Items from the database
        loadRecipesFromDatabase();
    }

    private void setupRecyclerView(){
        pantryList = new ArrayList<>();
        adapter = new PantryAdapter(pantryList, pantry -> {
            Toast.makeText(getContext(), "Clicked: " + pantry.getName(),
                    Toast.LENGTH_SHORT).show();
        });

        binding.pantryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.pantryRecyclerView.setAdapter(adapter);
    }

    private void loadRecipesFromDatabase(){
        List<PantryItem> pantryItems = PantryItemDAO.getAll();
        adapter.updateList(pantryItems);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

}
