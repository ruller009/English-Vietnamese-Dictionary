package com.ruller.officalapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class WordMeaningActivity extends AppCompatActivity {

    TextView textview_wordmeaning,title_wordmeaning;
    Button US_speaker_btn, UK_speaker_btn, learned, not_learned;
    private TextToSpeech TTS;
    DataBaseHelper dataBaseHelper;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_meaning);


        textview_wordmeaning = findViewById(R.id.textview_wordmeaning);
        title_wordmeaning = findViewById(R.id.title_wordmeaning);
        not_learned = findViewById(R.id.wordmeaning_not_learned);
        learned = findViewById(R.id.wordmeaning_learned);
        //*******************
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.createDataBase();


        Intent intent = getIntent();
        String word = intent.getStringExtra(MainActivity.WORD);
        title_wordmeaning.setText(word);

        String meaning = dataBaseHelper.searchword(word);
        textview_wordmeaning.setText(Html.fromHtml(meaning));

        state = dataBaseHelper.get_learned(word);
        if(state == 0){
            learned.setVisibility(View.GONE);
        }else {
            not_learned.setVisibility(View.GONE);
        }

        // set US TextToSpeech

        US_speaker_btn = findViewById(R.id.US_speaker_btn);
        US_speaker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS = new TextToSpeech(WordMeaningActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            TTS.setLanguage(Locale.US);
                            TTS.speak(word,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                });
            }
        });

        // set UK TextToSpeech
        UK_speaker_btn = findViewById(R.id.UK_speaker_btn);
        UK_speaker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS = new TextToSpeech(WordMeaningActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            TTS.setLanguage(Locale.UK);
                            TTS.speak(word,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                });
            }
        });

        not_learned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                not_learned.setVisibility(View.GONE);
                learned.setVisibility(View.VISIBLE);
                state = 1;
                dataBaseHelper.updateWordMeaning_learned(state,word);
            }
        });

        learned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                not_learned.setVisibility(View.VISIBLE);
                learned.setVisibility(View.GONE);
                state = 0;
                dataBaseHelper.updateWordMeaning_learned(state,word);
            }
        });

    }
}

