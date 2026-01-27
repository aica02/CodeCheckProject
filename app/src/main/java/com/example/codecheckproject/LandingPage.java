package com.example.codecheckproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LandingPage extends AppCompatActivity {

    private ImageView imageView;
    private ObjectAnimator animator;
    private ObjectAnimator blinkAnimator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing_page);
        UIHelper.hideSystemUI(this);

        imageView = findViewById(R.id.imageView);
        LinearLayout rootLayout = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPage.this, HomePage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_fade_in, R.anim.scale_fade_out);
        });


        startService(new Intent(this, Music.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        animator.setDuration(15000); // slow rotation
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

        Button enterButton = findViewById(R.id.enterButton);

        blinkAnimator = ObjectAnimator.ofFloat(enterButton, "alpha", 1f, 0f);
        blinkAnimator.setDuration(800);
        blinkAnimator.setRepeatMode(ValueAnimator.REVERSE);
        blinkAnimator.setRepeatCount(ValueAnimator.INFINITE);
        blinkAnimator.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (animator != null) {
            animator.cancel();
        }
    }


}
