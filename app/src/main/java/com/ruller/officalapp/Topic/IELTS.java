package com.ruller.officalapp.Topic;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;

import java.util.ArrayList;

public class IELTS extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ArrayList<String> ielts_word;
    IeltsAdapter ieltsAdapter;
    DataBaseHelper dataBaseHelper;
    //Button add_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ielts_main);
        //
        mRecyclerView = findViewById(R.id.ielts_recyclerview);
        //
        dataBaseHelper = new DataBaseHelper(this);
        ielts_word = new ArrayList<>();

        dataBaseHelper.createDataBase();
        storeDataInArrays();

        ieltsAdapter = new IeltsAdapter(this, ielts_word);
        mRecyclerView.setAdapter(ieltsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        int t = ieltsAdapter.getItemCount();
        String res = String.valueOf(t);
        Log.i("counter",res);
    }

    void storeDataInArrays() {
        Cursor cursor = dataBaseHelper.readAllData_fromIelts();

        while (cursor.moveToNext()) {
            ielts_word.add(cursor.getString(0));
        }
    }
}