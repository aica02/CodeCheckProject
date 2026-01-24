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

        javaBtn = findViewById(R.id.javaBtn);
        cssBtn = findViewById(R.id.cssBtn);
        mysqlBtn = findViewById(R.id.mysqlBtn);
        backBtn = findViewById(R.id.backBtn);

        javaBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriesPage.this, JavaPage.class);
            startActivity(intent);
        });

        cssBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriesPage.this, SettingsPage.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriesPage.this, HomePage.class);
            startActivity(intent);
        });
    }
}