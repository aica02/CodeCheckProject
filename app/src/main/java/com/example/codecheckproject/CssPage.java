package com.example.codecheckproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class CssPage extends AppCompatActivity {

    TextView tvCode, tvTimer, tvScore;
    Button btn1, btn2, btn3, btn4;
    ProgressBar progressBar;

    // 50 CSS questions
    String[] questions = {
            "Which property is used to change the background color?",
            "How do you make text bold in CSS?",
            "Which property controls the text size?",
            "Which property adds space inside an element's border?",
            "Which property adds space outside an element's border?",
            "How do you change the font of an element?",
            "Which property changes the text color?",
            "How do you make a list not display bullets?",
            "How do you select an element with id='header'?",
            "How do you select elements with class='menu'?",
            "Which property sets the element's width?",
            "Which property sets the element's height?",
            "How do you center an element horizontally?",
            "Which property makes text uppercase?",
            "Which property makes text italic?",
            "Which property adds a shadow to text?",
            "How do you apply a linear gradient as background?",
            "Which property changes the element's opacity?",
            "How do you hide an element?",
            "Which property changes the spacing between letters?",
            "How do you make the text underline?",
            "Which property changes the cursor when hovering?",
            "How do you make a div float to the right?",
            "Which property controls the stacking order?",
            "Which property sets the element's border radius?",
            "Which property sets the border style?",
            "Which property sets the border color?",
            "Which property sets the border width?",
            "How do you make the text color red?",
            "Which property sets the element's margin?",
            "Which property sets the element's padding?",
            "How do you apply a shadow to a box?",
            "Which property controls the display type?",
            "Which property changes the element's position?",
            "How do you make a div fixed at the top?",
            "Which property changes the background image?",
            "How do you repeat a background image?",
            "Which property sets the font weight?",
            "Which property sets the font style?",
            "Which property sets text alignment?",
            "Which property controls the line height?",
            "Which property makes text wrap or not?",
            "Which property sets the min width?",
            "Which property sets the max width?",
            "How do you make an element flexible in a flex container?",
            "Which property controls flex direction?",
            "Which property controls flex wrap?",
            "Which property sets gap between flex items?",
            "Which property sets the grid columns?",
            "Which property sets the grid rows?",
            "Which property sets the grid gap?"
    };

    String[][] options = {
            {"background-color","color","bgcolor","background-image"},
            {"font-weight:bold","text-decoration:bold","font-style:bold","text-transform:bold"},
            {"font-size","text-size","font-style","text-style"},
            {"padding","margin","border-spacing","space"},
            {"margin","padding","spacing","border-margin"},
            {"font-family","font-style","text-font","font-weight"},
            {"color","background-color","text-color","font-color"},
            {"list-style:none","list-type:none","list-style-type:none","list:none"},
            {"#header","#.header","header","#header-id"},
            {".menu","#menu","menu",".menu-class"},
            {"width","height","size","length"},
            {"height","width","size","length"},
            {"margin:auto","text-align:center","align:center","center"},
            {"text-transform:uppercase","font-variant:uppercase","font-style:uppercase","text-style:uppercase"},
            {"font-style:italic","font-weight:italic","text-decoration:italic","font-variant:italic"},
            {"text-shadow","box-shadow","shadow-text","font-shadow"},
            {"background: linear-gradient","background-image: gradient","gradient","background-style:linear"},
            {"opacity","visibility","transparent","filter"},
            {"display:none","visibility:hidden","opacity:0","hidden:true"},
            {"letter-spacing","word-spacing","text-spacing","line-spacing"},
            {"text-decoration:underline","font-style:underline","text-transform:underline","font-decoration:underline"},
            {"cursor","pointer","hover","mouse"},
            {"float:right","align:right","position:right","text-align:right"},
            {"z-index","stack-order","layer-index","position-index"},
            {"border-radius","border-shape","border-round","border-corner"},
            {"border-style","border-type","border-width","border-format"},
            {"border-color","color","border-style","border-shade"},
            {"border-width","border-size","border-style","border-height"},
            {"color:red","text-color:red","font-color:red","font-style:red"},
            {"margin","padding","spacing","border"},
            {"padding","margin","spacing","border"},
            {"box-shadow","text-shadow","shadow-box","border-shadow"},
            {"display","position","float","visibility"},
            {"position","top","z-index","align"},
            {"position:fixed; top:0","float:top","top:0; fixed","position:absolute; top:0"},
            {"background-image","background-color","bg-image","image"},
            {"background-repeat","repeat-background","bg-repeat","image-repeat"},
            {"font-weight","font-style","text-weight","text-style"},
            {"font-style","font-weight","font-variant","font-decoration"},
            {"text-align","align-text","align","justify"},
            {"line-height","text-spacing","letter-spacing","font-size"},
            {"white-space","text-wrap","word-wrap","overflow-wrap"},
            {"min-width","width","max-width","element-width"},
            {"max-width","width","min-width","element-width"},
            {"flex-grow","flex-shrink","flex-basis","flex-direction"},
            {"flex-direction","flex-wrap","flex-flow","flex-align"},
            {"flex-wrap","flex-direction","flex-flow","flex-align"},
            {"gap","margin","padding","space"},
            {"grid-template-columns","grid-columns","columns","column-template"},
            {"grid-template-rows","grid-rows","rows","row-template"},
            {"grid-gap","gap","grid-spacing","grid-space"}
    };

    String[] answers = {
            "background-color","font-weight:bold","font-size","padding","margin","font-family","color",
            "list-style:none","#header",".menu","width","height","margin:auto","text-transform:uppercase",
            "font-style:italic","text-shadow","background: linear-gradient","opacity","display:none","letter-spacing",
            "text-decoration:underline","cursor","float:right","z-index","border-radius","border-style","border-color",
            "border-width","color:red","margin","padding","box-shadow","display","position","position:fixed; top:0",
            "background-image","background-repeat","font-weight","font-style","text-align","line-height","white-space",
            "min-width","max-width","flex-grow","flex-direction","flex-wrap","gap","grid-template-columns",
            "grid-template-rows","grid-gap"
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
        setContentView(R.layout.activity_css_page);
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
        tvScore.setText("Score: " + totalScore);

        index++;
        loadQuestion();
    }

    void startTimer() {
        timer = new CountDownTimer(TIME_PER_QUESTION * 1000, 1000) {
            public void onTick(long ms) {
                tvTimer.setText("Time-Left: " + (ms / 1000));
            }

            public void onFinish() {
                Toast.makeText(CssPage.this, "Time's up!", Toast.LENGTH_SHORT).show();
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
