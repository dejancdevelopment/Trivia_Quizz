package com.example.triviaappmodel.activitysettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import mk.codeacademy.triviaquizz.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle(getString(R.string.settings_title));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTheme(R.style.SettingsFragmentStyle);

        FrameLayout frameLayout = findViewById(R.id.fragment_frame);

        if (frameLayout != null) {

            if (savedInstanceState != null)
                return;

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, new SettingsFragment())
                    .commit();


        }

    }
}
