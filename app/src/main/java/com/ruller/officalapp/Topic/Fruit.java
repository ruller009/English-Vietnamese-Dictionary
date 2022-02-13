package com.ruller.officalapp.Topic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.R;

public class Fruit extends AppCompatActivity {

    TextView word;
    ImageButton left, right;
    ImageView image;
    int state = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_topic);

        word = findViewById(R.id.fruit_word);
        image = findViewById(R.id.fruit_img);

        left = findViewById(R.id.fruit_arrow_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state!=1){
                    state-=1;
                }else{
                    state=6;
                }

                if(state == 1){
                    word.setText("Apple");
                    image.setImageResource(R.drawable.apple);
                }else if (state==2) {
                    word.setText("Banana");
                    image.setImageResource(R.drawable.banana);
                }else if (state == 3){
                    word.setText("Water Melon");
                    image.setImageResource(R.drawable.watermelon);
                }else if( state ==4){
                    word.setText("Cherry");
                    image.setImageResource(R.drawable.cherry);
                }else if(state == 5){
                    word.setText("Orange");
                    image.setImageResource(R.drawable.orange);
                }else if(state ==6){
                    word.setText("Mango");
                    image.setImageResource(R.drawable.mango);
                }
            }
        });

        //
        right = findViewById(R.id.fruit_arrow_forward);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state!=6){
                    state+=1;
                }else{
                    state=1;
                }

                if(state == 1){
                    word.setText("Apple");
                    image.setImageResource(R.drawable.apple);
                }else if (state==2) {
                    word.setText("Banana");
                    image.setImageResource(R.drawable.banana);
                }else if (state == 3){
                    word.setText("Water Melon");
                    image.setImageResource(R.drawable.watermelon);
                }else if( state ==4){
                    word.setText("Cherry");
                    image.setImageResource(R.drawable.cherry);
                }else if(state == 5){
                    word.setText("Orange");
                    image.setImageResource(R.drawable.orange);
                }else if(state ==6){
                    word.setText("Mango");
                    image.setImageResource(R.drawable.mango);
                }
            }
        });
    }
}

