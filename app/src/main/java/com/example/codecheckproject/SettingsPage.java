package com.example.codecheckproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsPage extends AppCompatActivity {

    private Switch nightModeSwitch;
    private Button helpBtn, backBtn;
    private SharedPreferences sharedPreferences;
    private boolean isNightModeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_page);
        getWindow().setWindowAnimations(0);
        UIHelper.hideSystemUI(this);


        nightModeSwitch = findViewById(R.id.nightModeSwitch);
        helpBtn = findViewById(R.id.helpBtn);
        backBtn = findViewById(R.id.backBtn);

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        isNightModeOn = sharedPreferences.getBoolean("nightMode", false);

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            nightModeSwitch.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            nightModeSwitch.setChecked(false);
        }

        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked == isNightModeOn) {
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("nightMode", isChecked);
            editor.apply();

            isNightModeOn = isChecked;

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


        helpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsPage.this, HelpPage.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsPage.this, HomePage.class);
            startActivity(intent);
        });
    }

    private void restartApp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
