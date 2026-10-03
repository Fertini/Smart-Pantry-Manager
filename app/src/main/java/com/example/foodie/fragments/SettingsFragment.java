package com.example.foodie.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.foodie.R;
import com.example.foodie.databinding.FragmentSettingsBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        // if setting is on then show expiring and expired items
        SwitchMaterial switchMaterial = binding.expiringSoonAlerts.findViewById(R.id.expiring_soon_alerts);

        // save switch stated in sharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", MODE_PRIVATE);
        switchMaterial.setChecked(sharedPreferences.getBoolean("value", true));

        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (switchMaterial.isChecked()){
                // when switch is checked
                SharedPreferences.Editor editor = getContext().getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", true);
                editor.apply();
                switchMaterial.setChecked(true);
                Toast.makeText(getContext(), "Alerts have been toggled on", Toast.LENGTH_LONG).show();
            } else {
                // when switch is not checked
                SharedPreferences.Editor editor = getContext().getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", false);
                editor.apply();
                switchMaterial.setChecked(false);

                Toast.makeText(getContext(), "Alerts have been toggled off", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}