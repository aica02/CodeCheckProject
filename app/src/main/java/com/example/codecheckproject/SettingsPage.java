package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsPage extends AppCompatActivity {

    private Button modeBtn, helpBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_page);

        modeBtn = findViewById(R.id.nightModeBtn);
        helpBtn = findViewById(R.id.helpBtn);
        backBtn = findViewById(R.id.backBtn);

        modeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsPage.this, JavaPage.class);
            startActivity(intent);
        });

        helpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsPage.this, HelpPage.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsPage.this, HomePage.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}