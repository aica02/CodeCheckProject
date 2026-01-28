package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoriesPage extends AppCompatActivity {

    private Button javaBtn, cssBtn, mysqlBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        UIHelper.hideSystemUI(this);
        javaBtn = findViewById(R.id.javaBtn);
        cssBtn = findViewById(R.id.cssBtn);
        mysqlBtn = findViewById(R.id.mysqlBtn);
        backBtn = findViewById(R.id.backBtn);

        javaBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(CategoriesPage.this, DifficultyPage.class);
            intent.putExtra("category", "java");
            startActivity(intent);
        });

        cssBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(CategoriesPage.this, DifficultyPage.class);
            intent.putExtra("category", "css");
            startActivity(intent);
        });

        mysqlBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(CategoriesPage.this, DifficultyPage.class);
            intent.putExtra("category", "mysql");
            startActivity(intent);
        });


        backBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(CategoriesPage.this, HomePage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right );
        });
    }
}