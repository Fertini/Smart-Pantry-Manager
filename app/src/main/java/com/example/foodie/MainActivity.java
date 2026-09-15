package com.example.foodie;

import android.os.Bundle;

import com.example.foodie.fragments.RecipeListFragment;
import com.example.foodie.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodie.fragments.PantryListFragment;
import com.example.foodie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and set the activity layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigationView = binding.bottomNavigation;

        // Load the fragment only on first creation
        if (savedInstanceState == null) {
            loadFragment(new PantryListFragment());
            bottomNavigationView.setSelectedItemId(R.id.nav_pantry);
        }

        // selecting fragments based on button selections
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_pantry) {
                selectedFragment = new PantryListFragment();
            } else if (item.getItemId() == R.id.nav_recipes) {
                selectedFragment = new RecipeListFragment();
            } else if (item.getItemId() == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true; // returning true to show that the item has been selected
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}