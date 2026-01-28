package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DifficultyPage extends AppCompatActivity {

    private Button easymodeBtn, hardmodeBtn, mediummodeBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_page);
        UIHelper.hideSystemUI(this);

        easymodeBtn = findViewById(R.id.easyBtn);
        mediummodeBtn = findViewById(R.id.mediumBtn);
        hardmodeBtn = findViewById(R.id.hardBtn);
        backBtn = findViewById(R.id.backBtn);

        String category = getIntent().getStringExtra("category");

        View.OnClickListener listener = v -> {
            SoundEffects.playClick();
            String difficulty = "";

            if (v.getId() == R.id.easyBtn) difficulty = "easy";
            else if (v.getId() == R.id.mediumBtn) difficulty = "medium";
            else if (v.getId() == R.id.hardBtn) difficulty = "hard";

            Intent intent;
            switch (category != null ? category : "java") {
                case "java":
                    intent = new Intent(DifficultyPage.this, JavaPage.class);
                    break;
                case "css":
                    intent = new Intent(DifficultyPage.this, CssPage.class);
                    break;
                case "mysql":
                    intent = new Intent(DifficultyPage.this, MySQLPage.class);
                    break;
                default:
                    intent = new Intent(DifficultyPage.this, JavaPage.class);
            }

            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        };

        easymodeBtn.setOnClickListener(listener);
        mediummodeBtn.setOnClickListener(listener);
        hardmodeBtn.setOnClickListener(listener);

        backBtn.setOnClickListener(v -> {
            SoundEffects.playClick();
            Intent intent = new Intent(DifficultyPage.this, CategoriesPage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right);
        });
    }
}