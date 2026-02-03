package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MySQLPage extends AppCompatActivity {

    TextView tvCode, tvTimer, tvScore;
    Button btn1, btn2, btn3, btn4,btnPause, btnResume, btnQuit;
    ProgressBar progressBar;

    String[] questions = {
            "SELECT * FROM students;",
            "SELECT NAME FROM students;",
            "SELECT AGE FROM students WHERE NAME='Ana';",
            "SELECT * FROM students WHERE AGE>21;",
            "SELECT COUNT(*) FROM students;",
            "SELECT * FROM students ORDER BY AGE ASC;",
            "SELECT * FROM students ORDER BY AGE DESC;",
            "SELECT * FROM students WHERE AGE BETWEEN 20 AND 22;",
            "SELECT * FROM students WHERE NAME LIKE 'J%';",
            "SELECT AVG(AGE) FROM students;",
            "SELECT MIN(AGE) FROM students;",
            "SELECT MAX(AGE) FROM students;",
            "SELECT SUM(AGE) FROM students;",
            "SELECT NAME FROM students WHERE AGE<21;",
            "SELECT NAME FROM students WHERE AGE>=22;",
            "SELECT * FROM students WHERE NAME='Lucy';",
            "SELECT * FROM students WHERE AGE<>20;",
            "SELECT NAME, AGE FROM students WHERE AGE<=21;",
            "SELECT NAME FROM students ORDER BY NAME ASC;",
            "SELECT NAME FROM students ORDER BY NAME DESC;",
            "SELECT * FROM students LIMIT 3;",
            "SELECT * FROM students LIMIT 2,3;",
            "SELECT * FROM students WHERE NAME IN ('Ana','Peter');",
            "SELECT * FROM students WHERE NAME NOT IN ('Ana','Peter');",
            "SELECT NAME FROM students WHERE AGE>20 ORDER BY AGE;",
            "SELECT NAME FROM students WHERE AGE<22 ORDER BY AGE DESC;",
            "SELECT COUNT(NAME) FROM students WHERE AGE>21;",
            "SELECT COUNT(NAME) FROM students WHERE AGE<20;",
            "SELECT * FROM students WHERE NAME LIKE '%a';",
            "SELECT * FROM students WHERE NAME LIKE '_a%';",
            "SELECT * FROM students WHERE AGE BETWEEN 19 AND 21;",
            "SELECT NAME FROM students WHERE AGE=23;",
            "SELECT NAME FROM students WHERE AGE=19;",
            "SELECT * FROM students WHERE AGE>22;",
            "SELECT * FROM students WHERE AGE<20;",
            "SELECT NAME, AGE FROM students WHERE NAME='Mark';",
            "SELECT * FROM students WHERE AGE>=20 AND AGE<=22;",
            "SELECT NAME FROM students WHERE NAME LIKE 'L%';",
            "SELECT * FROM students WHERE NAME='Ana' OR NAME='John';",
            "SELECT * FROM students WHERE AGE>21 OR NAME='Ana';",
            "SELECT NAME FROM students ORDER BY AGE ASC LIMIT 2;",
            "SELECT NAME FROM students ORDER BY AGE DESC LIMIT 2;",
            "SELECT AGE FROM students WHERE NAME='Peter';",
            "SELECT AGE FROM students WHERE NAME='Lucy';",
            "SELECT * FROM students WHERE AGE BETWEEN 19 AND 23 ORDER BY AGE;",
            "SELECT * FROM students WHERE NAME LIKE '%e%';",
            "SELECT NAME FROM students WHERE AGE!=22;",
            "SELECT * FROM students WHERE AGE<=21;",
            "SELECT COUNT(*) FROM students WHERE AGE>=21;",
            "SELECT * FROM students WHERE NAME='John';"
    };

    String[][] options = {
            {"Displays all rows from the table","Deletes all rows","Updates all rows","Creates a new table"},
            {"Returns all NAMES","Returns all IDs","Returns all AGES","Returns nothing"},
            {"20","22","19","21"},
            {"Mark, Peter","Ana, Lucy","John, Lucy","Peter, John"},
            {"5","4","3","6"},
            {"John, Ana, Lucy, Mark, Peter","Ana, John, Lucy, Mark, Peter","Peter, Mark, Lucy, Ana, John","Lucy, Peter, Mark, John, Ana"},
            {"Peter, Mark, Lucy, Ana, John","John, Ana, Lucy, Mark, Peter","Lucy, Ana, John, Mark, Peter","Ana, Mark, Peter, John, Lucy"},
            {"Ana, Lucy, Mark","Peter, John","Ana, John, Peter","Lucy, Mark, John"},
            {"John","Jack","Jane","Lucy"},
            {"21","20","22","19"},
            {"19","20","21","22"},
            {"23","22","21","20"},
            {"105","100","95","110"},
            {"Ana, John","Mark, Peter","Lucy, Peter","John, Lucy"},
            {"Mark, Peter","Ana, John","Lucy, Ana","John, Lucy"},
            {"Lucy, 21","Peter, 23","Ana, 20","John, 19"},
            {"Mark, John, Lucy, Peter","Ana, Mark, Peter","John, Lucy","Ana, John, Lucy"},
            {"Ana, John, Lucy","Mark, Peter","Ana, Peter","John, Lucy"},
            {"Ana, John, Lucy, Mark, Peter","Peter, Mark, Lucy, Ana, John","John, Mark, Lucy, Ana, Peter","Ana, Lucy, John, Peter, Mark"},
            {"Peter, Mark, Lucy, John, Ana","Ana, John, Lucy, Mark, Peter","John, Ana, Lucy, Mark, Peter","Lucy, Peter, Mark, John, Ana"},
            {"Ana, Mark, John","John, Lucy, Peter","Ana, John, Lucy","Mark, Peter, Lucy"},
            {"John, Lucy, Peter","Ana, Mark, John","Peter, Mark, Lucy","Lucy, John, Ana"},
            {"Ana, Peter","John, Lucy","Mark, Peter","Ana, John"},
            {"Mark, John, Lucy","Ana, Peter","John, Peter","Lucy, Ana"},
            {"Lucy, Mark, Peter","Ana, John, Lucy","John, Lucy, Peter","Mark, Ana"},
            {"Lucy, Ana, Mark","Peter, Mark","Ana, John, Lucy","Lucy, John"},
            {"2","3","1","4"},
            {"1","2","3","4"},
            {"Ana, Peter","Mark, Lucy","John, Peter","Lucy, Ana"},
            {"Mark, Ana","John, Peter","Lucy, Ana","Peter, Mark"},
            {"Ana, John, Lucy","Mark, Peter","Ana, Peter","John, Lucy"},
            {"Peter","Ana","Mark","John"},
            {"John","Peter","Ana","Lucy"},
            {"Peter","Mark","Ana","Lucy"},
            {"John","Ana","Mark","Lucy"},
            {"Mark, 22","Ana, 20","Peter, 23","Lucy, 21"},
            {"Ana, Lucy, Mark","John, Peter","Ana, John, Peter","Lucy, Mark"},
            {"Lucy","Ana","Peter","John"},
            {"Ana, John","Mark, Peter","Lucy, Peter","John, Lucy"},
            {"Ana, Mark, Peter","Lucy, John","John, Peter","Ana, Lucy"},
            {"John, Ana","Peter, Mark","Lucy, Ana","Ana, John"},
            {"Peter, Mark","John, Ana","Lucy, Ana","Ana, Lucy"},
            {"23","20","22","19"},
            {"21","20","22","19"},
            {"John, Ana, Lucy, Mark, Peter","Peter, Mark, Lucy, Ana, John","Ana, Lucy, John, Peter, Mark","Lucy, John, Ana, Peter, Mark"},
            {"Peter","Ana","John","Lucy"},
            {"Ana, John, Lucy, Peter","Mark, Peter","Ana, John","Lucy, Ana"},
            {"Ana, John, Lucy","Mark, Peter","Ana, Peter","John, Lucy"},
            {"3","2","1","4"},
            {"John, 19","Ana, 20","Peter, 23","Lucy, 21"}
    };

    String[] answers = {
            "Displays all rows from the table",
            "Returns all NAMES",
            "20",
            "Mark, Peter",
            "5",
            "John, Ana, Lucy, Mark, Peter",
            "Peter, Mark, Lucy, Ana, John",
            "Ana, Lucy, Mark",
            "John",
            "21",
            "19",
            "23",
            "105",
            "Ana, John",
            "Mark, Peter",
            "Lucy, 21",
            "Mark, John, Lucy, Peter",
            "Ana, John, Lucy",
            "Ana, John, Lucy, Mark, Peter",
            "Peter, Mark, Lucy, John, Ana",
            "Ana, Mark, John",
            "John, Lucy, Peter",
            "Ana, Peter",
            "Mark, John, Lucy",
            "Lucy, Mark, Peter",
            "Lucy, Ana, Mark",
            "2",
            "1",
            "Ana, Peter",
            "Mark, Ana",
            "Ana, John, Lucy",
            "Peter",
            "John",
            "Peter",
            "John",
            "Mark, 22",
            "Ana, Lucy, Mark",
            "Lucy",
            "Ana, John",
            "Ana, Mark, Peter",
            "John, Ana",
            "Peter, Mark",
            "23",
            "21",
            "John, Ana, Lucy, Mark, Peter",
            "Peter",
            "Ana, John, Lucy, Peter",
            "Ana, John, Lucy",
            "3",
            "John, 19"
    };

    int[] randomQuestions;
    int index = 0;
    int totalScore = 0;
    int totalTimeTaken = 0;

    CountDownTimer timer;

    View pauseOverlay;
    boolean isPaused = false;
    long timeLeftMs;
    final int QUESTION_COUNT = 5;
    int TIME_PER_QUESTION;
    String difficulty = "easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sqlpage);
        UIHelper.hideSystemUI(this);


        tvCode = findViewById(R.id.tvCode);
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        pauseOverlay = findViewById(R.id.pauseOverlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnQuit = findViewById(R.id.btnQuit);
        progressBar = findViewById(R.id.progressBar);

        String selectedDifficulty = getIntent().getStringExtra("difficulty");
        difficulty = selectedDifficulty != null ? selectedDifficulty.toLowerCase() : "easy";

        switch (difficulty) {
            case "easy": TIME_PER_QUESTION = 30; break;
            case "medium": TIME_PER_QUESTION = 20; break;
            case "hard": TIME_PER_QUESTION = 10; break;
            default: TIME_PER_QUESTION = 30;
        }

        generateRandomQuestions();

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            checkAnswer(b.getText().toString());
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btnPause.setOnClickListener(v -> pauseGame());
        btnResume.setOnClickListener(v -> resumeGame());
        btnQuit.setOnClickListener(v -> finish());


        loadQuestion();
    }

    void generateRandomQuestions() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) list.add(i);
        Collections.shuffle(list);

        randomQuestions = new int[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) randomQuestions[i] = list.get(i);
    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent musicIntent = new Intent(this, Music.class);
        musicIntent.putExtra("music", R.raw.ingamebackground);
        startService(musicIntent);
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

        startTimer(TIME_PER_QUESTION * 1000);
    }

    void checkAnswer(String selected) {
        if (timer != null) timer.cancel();

        int q = randomQuestions[index];
        long timeLeft = Long.parseLong(tvTimer.getText().toString().replace("Time-Left: ", ""));
        int scoreThisQuestion = 0;
        totalTimeTaken += (TIME_PER_QUESTION - (int) timeLeft);

        if (selected.equals(answers[q])) {
            totalScore += 5 * timeLeft;
        }
        if (selected.equals(answers[q])) {
            SoundEffects.playCorrect();
            scoreThisQuestion = 5 * (int) timeLeft;
            Toast.makeText(this, "Correct! +" + scoreThisQuestion, Toast.LENGTH_SHORT).show();
        } else {
            SoundEffects.playWrong();
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        index++;
        tvScore.setText("Score: " + totalScore);
        loadQuestion();
    }

    void pauseGame() {
        isPaused = true;
        if (timer != null) timer.cancel();
        pauseOverlay.setVisibility(View.VISIBLE);
        stopService(new Intent(this, Music.class));
    }

    void resumeGame() {
        isPaused = false;
        pauseOverlay.setVisibility(View.GONE);
        startTimer(timeLeftMs);

        Intent music = new Intent(this, Music.class);
        music.putExtra("music", R.raw.ingamebackground);
        startService(music);
    }

    void startTimer(long duration) {
        timeLeftMs = duration;
        timer = new CountDownTimer(TIME_PER_QUESTION * 1000, 1000) {
            public void onTick(long ms) {
                timeLeftMs = ms;
                tvTimer.setText("Time-Left: " + (ms / 1000));
            }

            public void onFinish() {
                totalTimeTaken += TIME_PER_QUESTION;
                index++;
                loadQuestion();
            }
        }.start();
    }

    void goToScorePage() {
        Intent intent = new Intent(this, ScorePage.class);
        intent.putExtra("score", totalScore);
        intent.putExtra("timeTaken", totalTimeTaken);
        intent.putExtra("category", "MySQL");
        intent.putExtra("difficulty", difficulty); // always lowercase for consistency
        startActivity(intent);
        finish();
    }
}