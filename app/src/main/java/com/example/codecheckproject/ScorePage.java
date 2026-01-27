package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScorePage extends AppCompatActivity {

    TextView finalScoreTV, timeTakenTV;
    Button againBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        UIHelper.hideSystemUI(this);

        finalScoreTV = findViewById(R.id.finalScoreTV);
        timeTakenTV = findViewById(R.id.timeTakenTV);

        againBtn = findViewById(R.id.againBtn);
        homeBtn = findViewById(R.id.homeBtn);

        int score = getIntent().getIntExtra("score", 0);
        finalScoreTV.setText("FINAL SCORE: " + score);

        int totalTime = getIntent().getIntExtra("timeTaken", -1);
        if (totalTime != -1) {
            timeTakenTV.setText("TIME TAKEN: " + totalTime + "s");
        } else {
            timeTakenTV.setText("");
        }

        againBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ScorePage.this, JavaPage.class);
            startActivity(intent);
            finish();
        });

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ScorePage.this, HomePage.class);
            startActivity(intent);
            finish();
        });
    }
}
