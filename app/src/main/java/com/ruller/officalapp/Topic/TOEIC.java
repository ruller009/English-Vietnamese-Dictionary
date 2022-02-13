package com.ruller.officalapp.Topic;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.IrregularVerbAdapter;
import com.ruller.officalapp.R;

import java.util.ArrayList;

public class TOEIC extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ArrayList<String> toeic_word;
    ToeicAdapter toeicAdapter;
    DataBaseHelper dataBaseHelper;
    //Button add_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toeic_main);
        //
        mRecyclerView = findViewById(R.id.toeic_recyclerview);
        //
        dataBaseHelper = new DataBaseHelper(this);
        toeic_word = new ArrayList<>();

        dataBaseHelper.createDataBase();
        storeDataInArrays();

        toeicAdapter = new ToeicAdapter(this, toeic_word);
        mRecyclerView.setAdapter(toeicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //int t = toeicAdapter.getItemCount();
        //String res = String.valueOf(t);
        //Log.i("counter",res);
    }

    void storeDataInArrays() {
        Cursor cursor = dataBaseHelper.readAllData_fromToeic();

        while (cursor.moveToNext()) {
            toeic_word.add(cursor.getString(0));
        }
    }
}
