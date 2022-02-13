package com.ruller.officalapp.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.R;

public class ImgVocabContent extends AppCompatActivity {

    Button btn_fruits, btn_vegetables, btn_nuts, btn_food, btn_seafood;
    static String IMG_VOCAB_TOPIC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_vocab_content);

        // Fruits
        btn_fruits = findViewById(R.id.btn_fruits);
        btn_fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabContent.this,ImgVocabShowing.class);
                String img_vocab_topic = btn_fruits.getText().toString();

                Log.i("valuesss",img_vocab_topic);
                intent.putExtra(IMG_VOCAB_TOPIC,img_vocab_topic);
                startActivity(intent);
            }
        });

        // Vegetables
        btn_vegetables = findViewById(R.id.btn_vegetables);
        btn_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabContent.this,ImgVocabShowing.class);
                String img_vocab_topic = btn_vegetables.getText().toString();

                Log.i("valuesss",img_vocab_topic);
                intent.putExtra(IMG_VOCAB_TOPIC,img_vocab_topic);
                startActivity(intent);
            }
        });

        // Nuts
        btn_nuts = findViewById(R.id.btn_nuts);
        btn_nuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabContent.this,ImgVocabShowing.class);
                String img_vocab_topic = btn_nuts.getText().toString();

                Log.i("valuesss",img_vocab_topic);
                intent.putExtra(IMG_VOCAB_TOPIC,img_vocab_topic);
                startActivity(intent);
            }
        });

        // Food
        btn_food = findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabContent.this,ImgVocabShowing.class);
                String img_vocab_topic = btn_food.getText().toString();

                Log.i("valuesss",img_vocab_topic);
                intent.putExtra(IMG_VOCAB_TOPIC,img_vocab_topic);
                startActivity(intent);
            }
        });

        // Seafood
        btn_seafood = findViewById(R.id.btn_seafood);
        btn_seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabContent.this,ImgVocabShowing.class);
                String img_vocab_topic = btn_seafood.getText().toString();

                Log.i("valuesss",img_vocab_topic);
                intent.putExtra(IMG_VOCAB_TOPIC,img_vocab_topic);
                startActivity(intent);
            }
        });

    }
}

