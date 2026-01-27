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

    String[] questions = {
            "int a = 5; a += 5; System.out.println(a);",
            "int x = 10; x--; System.out.println(x);",
            "int a = 4; int b = 2; System.out.println(a * b + 1);",
            "int x = 5; System.out.println(++x);",
            "int a = 7; int b = 2; System.out.println(a / b);",
            "int x = 3; x *= 2; System.out.println(x);",
            "int a = 10; int b = 3; System.out.println(a % b);",
            "int x = 5; System.out.println(x++ + x);",
            "int a = 2; int b = 3; System.out.println(a == b);",
            "int x = 4; System.out.println(x > 3 && x < 10);",
            "int x = 1; System.out.println(x++);",
            "int x = 1; System.out.println(++x);",
            "int a = 6; System.out.println(a / 2);",
            "int a = 6; System.out.println(a % 4);",
            "boolean b = true; System.out.println(!b);",
            "int a = 3; a += a; System.out.println(a);",
            "int x = 9; x -= 4; System.out.println(x);",
            "int a = 2; System.out.println(a * a);",
            "int a = 10; a /= 2; System.out.println(a);",
            "int x = 8; System.out.println(x > 5);",
            "int a = 1; int b = 2; System.out.println(a < b);",
            "int a = 5; int b = 5; System.out.println(a == b);",
            "int x = 7; System.out.println(x >= 7);",
            "int x = 0; System.out.println(x == 0);",
            "int a = 4; a--; System.out.println(a);",
            "int a = 4; --a; System.out.println(a);",
            "int x = 3; System.out.println(x + 2 * 2);",
            "int x = 10; System.out.println(x / 3);",
            "int x = 10; System.out.println(x % 3);",
            "int x = 5; System.out.println(x * 0);",
            "int x = 1; System.out.println(x == 1 && x > 0);",
            "int x = 1; System.out.println(x == 1 || x > 5);",
            "boolean b = false; System.out.println(b || true);",
            "boolean b = false; System.out.println(!b);",
            "int a = 5; System.out.println(a >= 5);",
            "int a = 3; System.out.println(a <= 2);",
            "int x = 9; System.out.println(x != 9);",
            "int x = 9; System.out.println(x != 8);",
            "int a = 1; System.out.println(a + 1 + 1);",
            "int a = 2; System.out.println(a * 3 - 1);",
            "int a = 7; System.out.println(a % 2);",
            "int x = 5; x += 2; System.out.println(x);",
            "int x = 10; x -= 3; System.out.println(x);",
            "int a = 8; System.out.println(a / 4);",
            "int a = 8; System.out.println(a % 5);",
            "boolean b = true; System.out.println(b && false);",
            "boolean b = true; System.out.println(b || false);",
            "int x = 4; System.out.println(x * 2);",
            "int a = 3; System.out.println(a + 4);",
            "int x = 6; System.out.println(x - 3);",
            "int x = 7; System.out.println(x * 0);",
            "int a = 2; int b = 5; System.out.println(a < b);",
            "int a = 2; int b = 5; System.out.println(a > b);",
            "boolean c = false; System.out.println(!c);",
            "int a = 3; int b = 3; System.out.println(a == b);",
            "int x = 10; System.out.println(x / 2);",
            "int x = 10; System.out.println(x % 4);"
    };

    String[][] options = {
            {"10","5","15","0"},
            {"9","10","11","8"},
            {"9","8","7","6"},
            {"6","5","7","8"},
            {"3","2","4","1"},
            {"6","3","5","9"},
            {"1","0","2","3"},
            {"11","10","12","9"},
            {"false","true","0","1"},
            {"true","false","0","1"},
            {"1","0","2","3"},
            {"2","1","0","3"},
            {"3","2","6","1"},
            {"2","3","1","4"},
            {"false","true","0","1"},
            {"6","3","5","4"},
            {"5","4","6","3"},
            {"4","2","3","1"},
            {"5","10","15","20"},
            {"true","false","0","1"},
            {"true","false","1","0"},
            {"true","false","1","1"},
            {"true","false","1","0"},
            {"true","false","0","1"},
            {"3","4","5","2"},
            {"3","2","4","1"},
            {"7","6","5","4"},
            {"3","4","5","6"},
            {"1","2","0","3"},
            {"1","0","2","3"},
            {"0","1","2","3"},
            {"true","false","1","0"},
            {"true","false","1","0"},
            {"true","false","1","0"},
            {"false","true","1","0"},
            {"true","false","1","0"},
            {"true","false","1","0"},
            {"3","4","5","6"},
            {"7","8","6","5"},
            {"2","3","4","5"},
            {"7","5","6","4"},
            {"2","1","3","0"},
            {"7","5","6","4"},
            {"false","true","1","0"},
            {"true","false","1","0"},
            {"8","9","10","7"},
            {"7","5","6","4"},
            {"3","4","5","6"},
            {"0","1","2","3"},
            {"true","false","0","1"},
            {"false","true","0","1"},
            {"false","true","0","1"},
            {"true","false","0","1"},
            {"5","6","7","4"},
            {"7","6","5","8"},
            {"true","false","0","1"},
            {"true","false","0","1"},
            {"5","6","7","8"},
            {"7","6","5","8"},
            {"true","false","0","1"},
            {"true","false","0","1"},
            {"3","4","5","6"},
            {"6","5","7","4"},
            {"3","2","4","1"},
            {"5","6","7","8"},
            {"5","6","7","8"},
            {"true","false","0","1"},
            {"true","false","0","1"},
            {"8","7","6","5"},
            {"7","6","5","8"}
    };

    String[] answers = {
            "10","9","9","6","3","6","1","11","false","true",
            "1","2","3","2","false","6","5","4","5","true",
            "true","true","true","true","3","3","7","3","1","0",
            "true","true","true","false","true","true","3","7","2","1",
            "false","true","8","7","3","3","0","true","5","7"
    };

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

    void generateRandomQuestions() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) list.add(i);
        Collections.shuffle(list);

        randomQuestions = new int[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) randomQuestions[i] = list.get(i);
    }

    void loadQuestion() {
        if (index >= QUESTION_COUNT) {
            goToScorePage();
            return;
        }

        int q = randomQuestions[index];
        tvCode.setText(questions[q]);

        List<String> shuffled = new ArrayList<>(Arrays.asList(options[q]));
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
        tvScore.setText("Score: " + totalScore);
    }

    void startTimer() {
        timer = new CountDownTimer(TIME_PER_QUESTION * 1000, 1000) {
            public void onTick(long ms) {
                tvTimer.setText("Time-Left: " + (ms / 1000));
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
