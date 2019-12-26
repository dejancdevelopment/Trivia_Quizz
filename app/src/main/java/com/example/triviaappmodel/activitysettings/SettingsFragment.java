package com.example.triviaappmodel.activitysettings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import mk.codeacademy.triviaquizz.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String NOTIFICATION_KEY="notification_KEY";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.settings_preferences,rootKey);

    }
}
