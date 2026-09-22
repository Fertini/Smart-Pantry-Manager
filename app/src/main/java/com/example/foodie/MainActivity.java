package com.example.foodie;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodie.databinding.ActivityMainBinding;
import com.example.foodie.fragments.pantryItemFragments.PantryListFragment;
import com.example.foodie.fragments.recipeFragments.RecipeListFragment;
import com.example.foodie.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // ========== Override Methods ========== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and set the activity layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigation;

        // Load the fragment only on first creation.
        if (savedInstanceState == null) {
            loadFragment(new PantryListFragment());
            bottomNavigationView.setSelectedItemId(R.id.nav_pantry);
        }

        // selecting fragments based on button selections
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_pantry) {
                selectedFragment = new PantryListFragment();
                changeBtn(item);

            } else if (item.getItemId() == R.id.nav_recipes) {
                changeBtn(item);
                selectedFragment = new RecipeListFragment();

            } else if (item.getItemId() == R.id.nav_settings) {
                changeBtn(item);
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true; // returning true to show that the item has been selected
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }

    // ========= class methods ========== //

    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeBtn(MenuItem item){
        item.setOnMenuItemClickListener(menuItem ->{
            findViewById(item.getItemId()).setBackgroundColor(0x2EC4B6);
            return true;
        });
    }
}