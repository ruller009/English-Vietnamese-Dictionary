package com.ruller.officalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.ruller.officalapp.MyVocabulary.MyVocabularyContainer;
import com.ruller.officalapp.Topic.IELTS;
import com.ruller.officalapp.Topic.ImgVocab;
import com.ruller.officalapp.Topic.ImgVocabContent;
import com.ruller.officalapp.Topic.ImgVocabDemoContent;
import com.ruller.officalapp.Topic.IrregularVerbActivity;
import com.ruller.officalapp.Topic.TOEIC;
import com.ruller.officalapp.VietnameseEnglish.VietnamEngishActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    SearchView searchView;
    DataBaseHelper myDbHelper;
    Button irregular_verb_btn, myvocabulary_btn, vn_eng_btn, img_vocab_btn;
    Button scan_btn, toeic_btn, ielts_btn;

    static String WORD ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDbHelper = new DataBaseHelper(this);
        myDbHelper.createDataBase();
        myDbHelper.openDataBase();

        searchView = (SearchView) findViewById(R.id.search_view);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false); // set to click anywhere
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.contains("\"")||query.contains("'")||query.contains("(")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Không hợp lệ");
                    builder.show();
                }
                else{
                    String check = myDbHelper.searchword(query);
                    if (check == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Không tìm thấy" + "\n" + "Yêu cầu bạn nhập lại");
                        builder.show();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this,WordMeaningActivity.class);
                        intent.putExtra(WORD,query);
                        Log.i("word_search",query);
                        startActivity(intent);
                    }
                }
                //

                /*


                 */

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        // Look up Vietnamese - English Activity
        vn_eng_btn = (Button) findViewById(R.id.vn_eng_btn);
        vn_eng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VietnamEngishActivity.class);
                startActivity(intent);
            }
        });


        // Irregular Verb Activity
        irregular_verb_btn = (Button) findViewById(R.id.irregular_verb_btn);
        irregular_verb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IrregularVerbActivity.class);
                startActivity(intent);
            }
        });

        // IELTS Activity
        ielts_btn = findViewById(R.id.ielts_btn);
        ielts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IELTS.class);
                startActivity(intent);
            }
        });

        // TOEIC Activity
        toeic_btn = (Button) findViewById(R.id.toeic_btn);
        toeic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TOEIC.class);
                startActivity(intent);
            }
        });

        // Image Vocabulary Activity
        img_vocab_btn = findViewById(R.id.img_vocab_btn);
        img_vocab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImgVocabContent.class);
                startActivity(intent);
            }
        });

        // My Vocabulary Activity
        myvocabulary_btn = (Button) findViewById(R.id.myvocabulary_btn);
        myvocabulary_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyVocabularyContainer.class);
                startActivity(intent);
            }
        });



    }

}