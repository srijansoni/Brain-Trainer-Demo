package com.srijansoni.braintrainerdemo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationofCorrectAnswer;
    int score = 0;
    TextView resultTextView;
    TextView scoreTextView;
    int numberOfQuestions= 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    TextView questionTextView;
    RelativeLayout relativeLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("3s");
        resultTextView.setText("");
        scoreTextView.setText("0/0");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100, 1000)
        {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();


    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b= rand.nextInt(21);

        questionTextView.setText(Integer.toString(a)+ " + "+ Integer.toString(b) + " ?");

        locationofCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;
        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i==locationofCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b)
                {
                    incorrectAnswer  = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(relativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgain);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);




    }
}
