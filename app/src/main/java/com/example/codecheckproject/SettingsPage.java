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


    private Button helpBtn, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_page);
        getWindow().setWindowAnimations(0);
        UIHelper.hideSystemUI(this);

        helpBtn = findViewById(R.id.helpBtn);
        backBtn = findViewById(R.id.backBtn);


        helpBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(SettingsPage.this, HelpPage.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
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
