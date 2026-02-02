package com.example.codecheckproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

public class HomePage extends AppCompatActivity {

    private Button playBtn, settingsBtn, exitBtn,leaderboardsBtn;

    private ObjectAnimator blinkAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        UIHelper.hideSystemUI(this);
        leaderboardsBtn = findViewById(R.id.leaderboardsBtn);
        playBtn = findViewById(R.id.playBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        exitBtn = findViewById(R.id.exitBtn);



        playBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(HomePage.this, CategoriesPage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_fade_in, R.anim.scale_fade_out);
        });

        settingsBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(HomePage.this, SettingsPage.class);
            startActivity(intent);
        });

        exitBtn.setOnClickListener(v -> {
            stopService(new Intent(HomePage.this, Music.class));
            finishAffinity();
        });

        leaderboardsBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(HomePage.this, LeaderboardsPage.class);
            startActivity(intent);
            finish();
        });

    }

    protected void onStart() {
        super.onStart();
        TextView tapToEnter = findViewById(R.id.Choose);

        blinkAnimator = ObjectAnimator.ofFloat(tapToEnter, "alpha", 1f, 0f);
        blinkAnimator.setDuration(800);
        blinkAnimator.setRepeatMode(ValueAnimator.REVERSE);
        blinkAnimator.setRepeatCount(ValueAnimator.INFINITE);
        blinkAnimator.start();


    }


}
