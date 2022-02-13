package com.ruller.officalapp.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.R;

public class ImgVocabDemoContent extends AppCompatActivity {

    Button fruit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_vocab_content);

        /*
        fruit_btn = findViewById(R.id.fruit_btn);
        fruit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgVocabDemoContent.this,Fruit.class);
                startActivity(intent);
            }
        });

         */
    }
}
