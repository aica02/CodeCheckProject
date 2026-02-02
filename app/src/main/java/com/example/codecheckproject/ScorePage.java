package com.example.codecheckproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

public class ScorePage extends AppCompatActivity {

    TextView finalScoreTV, timeTakenTV, categoryTV, difficultyTV;
    Button againBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        UIHelper.hideSystemUI(this);

        finalScoreTV = findViewById(R.id.finalScoreTV);
        timeTakenTV = findViewById(R.id.timeTakenTV);
        categoryTV = findViewById(R.id.categoryTV);

        againBtn = findViewById(R.id.againBtn);
        homeBtn = findViewById(R.id.homeBtn);

        int score = getIntent().getIntExtra("score", 0);
        int totalTime = getIntent().getIntExtra("timeTaken", 0);
        String category = getIntent().getStringExtra("category");
        String difficulty = getIntent().getStringExtra("difficulty");


        finalScoreTV.setText("FINAL SCORE: " + score);
        timeTakenTV.setText("TIME TAKEN: " + totalTime + "s");
        categoryTV.setText("CATEGORY: " + category.toUpperCase());
        if (difficultyTV != null) difficultyTV.setText("DIFFICULTY: " + capitalize(difficulty));

        SharedPreferences prefs = getSharedPreferences("leaderboard", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int size = prefs.getInt("size", 0);

        editor.putString("date_" + size,
                new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        editor.putInt("score_" + size, score);
        editor.putInt("time_" + size, totalTime);
        editor.putString("category_" + size, category);
        editor.putString("difficulty_" + size, difficulty.toLowerCase());
        editor.putInt("size", size + 1);
        editor.apply();

        againBtn.setOnClickListener(v -> {
            Intent intent;
            switch (category) {
                case "CSS":
                    intent = new Intent(this, CssPage.class);
                    break;
                case "MySQL":
                    intent = new Intent(this, MySQLPage.class);
                    break;
                default:
                    intent = new Intent(this, JavaPage.class);
                    break;
            }
            intent.putExtra("category", category);
            intent.putExtra("difficulty", difficulty.toLowerCase());
            startActivity(intent);
            finish();
        });

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(ScorePage.this, HomePage.class));
            finish();
        });
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
