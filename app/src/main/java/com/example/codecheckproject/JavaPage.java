package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class JavaPage extends AppCompatActivity {

    TextView tvCode, tvTimer, tvScore;
    Button btn1, btn2, btn3, btn4;
    ProgressBar progressBar;

    String[] questions = { "int a = 5; a += 3; System.out.println(a);", "int x = 10; x--; System.out.println(x);", "int a = 4; int b = 2; System.out.println(a * b + 1);", "int x = 5; System.out.println(++x);", "int a = 7; int b = 2; System.out.println(a / b);", "int x = 3; x *= 2; System.out.println(x);", "int a = 10; int b = 3; System.out.println(a % b);", "int x = 5; System.out.println(x++ + x);", "int a = 2; int b = 3; System.out.println(a == b);", "int x = 4; System.out.println(x > 3 && x < 10);", "int x = 1; System.out.println(x++);", "int x = 1; System.out.println(++x);", "int a = 6; System.out.println(a / 2);", "int a = 6; System.out.println(a % 4);", "boolean b = true; System.out.println(!b);", "int a = 3; a += a; System.out.println(a);", "int x = 9; x -= 4; System.out.println(x);", "int a = 2; System.out.println(a * a);", "int a = 10; a /= 2; System.out.println(a);", "int x = 8; System.out.println(x > 5);", "int a = 1; int b = 2; System.out.println(a < b);", "int a = 5; int b = 5; System.out.println(a == b);", "int x = 7; System.out.println(x >= 7);", "int x = 0; System.out.println(x == 0);", "int a = 4; a--; System.out.println(a);", "int a = 4; --a; System.out.println(a);", "int x = 3; System.out.println(x + 2 * 2);", "int x = 10; System.out.println(x / 3);", "int x = 10; System.out.println(x % 3);", "int x = 5; System.out.println(x * 0);", "int x = 1; System.out.println(x == 1 && x > 0);", "int x = 1; System.out.println(x == 1 || x > 5);", "boolean b = false; System.out.println(b || true);", "boolean b = false; System.out.println(!b);", "int a = 5; System.out.println(a >= 5);", "int a = 3; System.out.println(a <= 2);", "int x = 9; System.out.println(x != 9);", "int x = 9; System.out.println(x != 8);", "int a = 1; System.out.println(a + 1 + 1);", "int a = 2; System.out.println(a * 3 - 1);" };
    String[][] options = new String[50][4];
    String[] answers = new String[50];

    int[] randomQuestions;
    int index = 0;
    int totalScore = 0;
    CountDownTimer timer;

    final int QUESTION_COUNT = 5;
    final int TIME_PER_QUESTION = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_page);
        UIHelper.hideSystemUI(this);

        tvCode = findViewById(R.id.tvCode);
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        progressBar = findViewById(R.id.progressBar);

        setupAnswers();
        generateRandomQuestions();

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            checkAnswer(b.getText().toString());
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);

        loadQuestion();
    }

    void setupAnswers() {
        for (int i = 0; i < 50; i++) {
            options[i][0] = "true";
            options[i][1] = "false";
            options[i][2] = "Error";
            options[i][3] = "0";
            answers[i] = "true";
        }

        answers[0] = "8";
        answers[1] = "9";
        answers[2] = "9";
        answers[3] = "6";
        answers[4] = "3";
    }

    void generateRandomQuestions() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) list.add(i);
        Collections.shuffle(list);

        randomQuestions = new int[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) {
            randomQuestions[i] = list.get(i);
        }
    }

    void loadQuestion() {
        if (index >= QUESTION_COUNT) {
            goToScorePage();
            return;
        }

        int q = randomQuestions[index];

        tvCode.setText(questions[q]);

        List<String> shuffled = new ArrayList<>();
        Collections.addAll(shuffled, options[q]);
        Collections.shuffle(shuffled);

        btn1.setText(shuffled.get(0));
        btn2.setText(shuffled.get(1));
        btn3.setText(shuffled.get(2));
        btn4.setText(shuffled.get(3));

        progressBar.setProgress((index + 1) * 100 / QUESTION_COUNT);
        startTimer();
    }

    void checkAnswer(String selected) {
        if (timer != null) timer.cancel();

        int q = randomQuestions[index];

        long timeLeft = Long.parseLong(tvTimer.getText().toString().replace("Time-Left: ", ""));
        int scoreThisQuestion = 0;

        if (selected.equals(answers[q])) {
            scoreThisQuestion = 5 * (int) timeLeft;
            Toast.makeText(this, "Correct! +" + scoreThisQuestion, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        totalScore += scoreThisQuestion;

        index++;
        loadQuestion();
        tvScore.setText("Score: "+totalScore);
    }

    void startTimer() {
        timer = new CountDownTimer(TIME_PER_QUESTION * 2000, 2000) {
            public void onTick(long ms) {
                tvTimer.setText("Time-Left: " + (ms / 2000));
            }

            public void onFinish() {
                Toast.makeText(JavaPage.this, "Time's up!", Toast.LENGTH_SHORT).show();
                index++;
                loadQuestion();
            }
        }.start();
    }

    void goToScorePage() {
        Intent intent = new Intent(this, ScorePage.class);
        intent.putExtra("score", totalScore);
        startActivity(intent);
        finish();
    }
}
