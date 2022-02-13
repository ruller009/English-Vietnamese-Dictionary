package com.ruller.officalapp.Topic.SimpleQuiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.R;
import com.ruller.officalapp.Topic.IrregularVerbActivity;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_one, btn_two, btn_three, btn_four, exit;
    TextView tv_question, point_tv;

    private Question question = new Question();

    private String answer;
    private int questionLength = question.questions.length;
    int point = 0;
    int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_content);


        btn_one = (Button)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_four = (Button)findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);
        exit = findViewById(R.id.quiz_exit);
        exit.setOnClickListener(this);

        tv_question = (TextView)findViewById(R.id.tv_question);
        point_tv = findViewById(R.id.point);
        String string_point = String.valueOf(point);
        point_tv.setText("Point : "+string_point);

        NextQuestion(state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                if(btn_one.getText() == answer){
                    Toast.makeText(QuizActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if(state != 4) state += 1;
                    else state = 0;
                    NextQuestion(state);
                    point += 1;
                    String string_point = String.valueOf(point);
                    point_tv.setText("Point : "+string_point);
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_two:
                if(btn_two.getText() == answer){
                    Toast.makeText(QuizActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if(state != 4) state += 1;
                    else state = 0;
                    NextQuestion(state);
                    point += 1;
                    String string_point = String.valueOf(point);
                    point_tv.setText("Point : "+string_point);
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_three:
                if(btn_three.getText() == answer){
                    Toast.makeText(QuizActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if(state != 4) state += 1;
                    else state = 0;
                    NextQuestion(state);
                    point += 1;
                    String string_point = String.valueOf(point);
                    point_tv.setText("Point : "+string_point);
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_four:
                if(btn_four.getText() == answer){
                    Toast.makeText(QuizActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if(state != 4) state += 1;
                    else state = 0;
                    NextQuestion(state);
                    point += 1;
                    String string_point = String.valueOf(point);
                    point_tv.setText("Point : "+string_point);
                }else{
                    GameOver();
                }

                break;

            case R.id.quiz_exit:
                System.exit(0);
        }
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                        finish();
                    }
                });
        alertDialogBuilder.show();

    }

    private void NextQuestion(int num){
        tv_question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        btn_four.setText(question.getchoice4(num));

        answer = question.getCorrectAnswer(num);
    }

}