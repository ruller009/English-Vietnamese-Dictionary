package com.ruller.officalapp.Topic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.IrregularVerbAdapter;
import com.ruller.officalapp.MainActivity;
import com.ruller.officalapp.R;
import com.ruller.officalapp.Topic.SimpleQuiz.QuizActivity;

import java.util.ArrayList;

public class IrregularVerbActivity extends AppCompatActivity {
    //
    private RecyclerView mRecyclerView;
    ArrayList<String> present, past, perfect_present;
    IrregularVerbAdapter irregularVerbAdapter;
    DataBaseHelper dataBaseHelper;
    Button doing_quiz;
    //Button add_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irregular_verb_main);
        //
        mRecyclerView = findViewById(R.id.recyclerview);
        //
        dataBaseHelper = new DataBaseHelper(this);
        present = new ArrayList<>();
        past = new ArrayList<>();
        perfect_present = new ArrayList<>();

        dataBaseHelper.createDataBase();
        storeDataInArrays();

        irregularVerbAdapter = new IrregularVerbAdapter(this, present,past,perfect_present);
        mRecyclerView.setAdapter(irregularVerbAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        int t = irregularVerbAdapter.getItemCount();
        String res = String.valueOf(t);
        Log.i("counter",res);

        //
        doing_quiz = findViewById(R.id.quiz_training_btn);
        doing_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IrregularVerbActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = dataBaseHelper.readAllData();

        while (cursor.moveToNext()) {
            present.add(cursor.getString(0));
            past.add(cursor.getString(1));
            perfect_present.add(cursor.getString(2));
        }
    }


}
