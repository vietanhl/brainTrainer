package com.example.viet_anhle.braintrainerapp;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answerList = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView result;
    int score=0;
    Button startButton;
    int numberOfQuestions = 0;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sum;
    TextView timer;
    Button playAgainButton;
    ConstraintLayout gamePageLayout;



    public void generateQuestion(){
        //random number 0-50, generating the question
        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);
        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;
        //clear the answers in the array
        answerList.clear();

        //if random number generates the correct answer
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answerList.add(a + b);
            } else {
                //incorrect answer will continue to generate until the answer does not equal the correct answer
                incorrectAnswer = rand.nextInt(101);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(101);
                }
                //add the incorrect answer to the array list
                answerList.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answerList.get(0)));
        button1.setText(Integer.toString(answerList.get(1)));
        button2.setText(Integer.toString(answerList.get(2)));
        button3.setText(Integer.toString(answerList.get(3)));
    }


    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gamePageLayout.setVisibility(View.VISIBLE);


    }
// when choosing the right answer method
    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            result.setText("Correct!");
            result.setVisibility(View.VISIBLE);
        } else {
            result.setText("Wrong!");
            result.setVisibility(View.VISIBLE);
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void playAgain (View view) {
        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        pointsTextView.setText("0/0");
        result.setText("");
        generateQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        //creating the timer
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                result.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                result.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sum = (TextView) findViewById(R.id.sum);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.result);
        startButton = (Button) findViewById(R.id.startButton);
        pointsTextView = (TextView) findViewById(R.id.points);
        timer = (TextView) findViewById(R.id.timer);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gamePageLayout = (ConstraintLayout) findViewById(R.id.gamePage);



       playAgain(findViewById(R.id.playAgainButton));
    }

}
