package com.example.foodie.managers;

import androidx.lifecycle.ViewModel;

public class AlertViewModel extends ViewModel {
    private boolean alertsShown = false;

    // Returns true only the first time it's called
    public boolean shouldShowAlerts() {
        if (alertsShown) return false;
        alertsShown = true;
        return true;

    }

}
