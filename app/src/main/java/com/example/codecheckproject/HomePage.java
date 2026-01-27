package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    private Button playBtn, settingsBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        UIHelper.hideSystemUI(this);


        playBtn = findViewById(R.id.playBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        exitBtn = findViewById(R.id.exitBtn);

        playBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, CategoriesPage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_fade_in, R.anim.scale_fade_out);
        });

        settingsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, SettingsPage.class);
            startActivity(intent);
        });

        exitBtn.setOnClickListener(v -> {
            stopService(new Intent(HomePage.this, Music.class));
            finishAffinity();
        });
    }


}
