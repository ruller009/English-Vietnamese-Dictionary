package com.ruller.officalapp.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.R;

public class ImgVocabShowing extends AppCompatActivity {

    TextView img_vocab_topic, img_vocab_word;
    ImageView img_vocab_img;
    ImageButton left,right;
    int state = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_vocab_showing);

        Intent intent = getIntent();
        String topic_value = intent.getStringExtra(ImgVocabContent.IMG_VOCAB_TOPIC);
        Log.i("values", topic_value);

        //
        img_vocab_topic = findViewById(R.id.img_vocab_topic);
        img_vocab_topic.setText(topic_value);

        //
        img_vocab_word = findViewById(R.id.img_vocab_word);
        img_vocab_img = findViewById(R.id.img_vocab_img);
        if (topic_value.equals("Fruit")) {
            img_vocab_word.setText("Apple");
            img_vocab_img.setImageResource(R.drawable.apple);
        }
        else if(topic_value.equals("Vegetables")) {
            img_vocab_word.setText("Potato");
            img_vocab_img.setImageResource(R.drawable.potato);
        }
        else if(topic_value.equals("Nuts")) {
            img_vocab_word.setText("Coffee");
            img_vocab_img.setImageResource(R.drawable.coffee);
        }
        else if(topic_value.equals("Food")) {
            img_vocab_word.setText("Cheese");
            img_vocab_img.setImageResource(R.drawable.cheese);
        }
        else if(topic_value.equals("Seafood")) {
            img_vocab_word.setText("Lobster");
            img_vocab_img.setImageResource(R.drawable.lobster);
        }

        //
        left = findViewById(R.id.img_vocab_arrow_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic_value.equals("Fruit"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Apple");
                        img_vocab_img.setImageResource(R.drawable.apple);
                    }else if (state==2) {
                        img_vocab_word.setText("Banana");
                        img_vocab_img.setImageResource(R.drawable.banana);
                    }else if (state == 3){
                        img_vocab_word.setText("Water Melon");
                        img_vocab_img.setImageResource(R.drawable.watermelon);
                    }else if( state ==4){
                        img_vocab_word.setText("Cherry");
                        img_vocab_img.setImageResource(R.drawable.cherry);
                    }else if(state == 5){
                        img_vocab_word.setText("Orange");
                        img_vocab_img.setImageResource(R.drawable.orange);
                    }
                }

                else if(topic_value.equals("Vegetables"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Potato");
                        img_vocab_img.setImageResource(R.drawable.potato);
                    }else if (state==2) {
                        img_vocab_word.setText("Artichoke");
                        img_vocab_img.setImageResource(R.drawable.artichoke);
                    }else if (state == 3){
                        img_vocab_word.setText("Carrot");
                        img_vocab_img.setImageResource(R.drawable.carrot);
                    }else if( state ==4){
                        img_vocab_word.setText("Cucumber");
                        img_vocab_img.setImageResource(R.drawable.cucumber);
                    }else if(state == 5){
                        img_vocab_word.setText("Onion");
                        img_vocab_img.setImageResource(R.drawable.onion);
                    }
                }

                else if(topic_value.equals("Nuts"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Coffee");
                        img_vocab_img.setImageResource(R.drawable.coffee);
                    }else if (state==2) {
                        img_vocab_word.setText("Corn");
                        img_vocab_img.setImageResource(R.drawable.corn);
                    }else if (state == 3){
                        img_vocab_word.setText("Peanut");
                        img_vocab_img.setImageResource(R.drawable.peanut);
                    }else if( state ==4){
                        img_vocab_word.setText("Rice");
                        img_vocab_img.setImageResource(R.drawable.rice);
                    }else if(state == 5){
                        img_vocab_word.setText("Wheat");
                        img_vocab_img.setImageResource(R.drawable.wheat);
                    }
                }

                else if(topic_value.equals("Food"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Cheese");
                        img_vocab_img.setImageResource(R.drawable.cheese);
                    }else if (state==2) {
                        img_vocab_word.setText("Butter");
                        img_vocab_img.setImageResource(R.drawable.butter);
                    }else if (state == 3){
                        img_vocab_word.setText("Milk");
                        img_vocab_img.setImageResource(R.drawable.milk);
                    }else if( state ==4){
                        img_vocab_word.setText("Cream");
                        img_vocab_img.setImageResource(R.drawable.cream);
                    }else if(state == 5){
                        img_vocab_word.setText("Yogurt");
                        img_vocab_img.setImageResource(R.drawable.yogurt);
                    }
                }

                else if(topic_value.equals("Seafood"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Lobster");
                        img_vocab_img.setImageResource(R.drawable.lobster);
                    }else if (state==2) {
                        img_vocab_word.setText("Crayfish");
                        img_vocab_img.setImageResource(R.drawable.crayfish);
                    }else if (state == 3){
                        img_vocab_word.setText("Eel");
                        img_vocab_img.setImageResource(R.drawable.eel);
                    }else if( state ==4){
                        img_vocab_word.setText("Mussel");
                        img_vocab_img.setImageResource(R.drawable.mussel);
                    }else if(state == 5){
                        img_vocab_word.setText("Shrimp");
                        img_vocab_img.setImageResource(R.drawable.shrimp);
                    }
                }
            }
        });

        //
        left = findViewById(R.id.img_vocab_arrow_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic_value.equals("Fruit"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Apple");
                        img_vocab_img.setImageResource(R.drawable.apple);
                    }else if (state==2) {
                        img_vocab_word.setText("Banana");
                        img_vocab_img.setImageResource(R.drawable.banana);
                    }else if (state == 3){
                        img_vocab_word.setText("Water Melon");
                        img_vocab_img.setImageResource(R.drawable.watermelon);
                    }else if( state ==4){
                        img_vocab_word.setText("Cherry");
                        img_vocab_img.setImageResource(R.drawable.cherry);
                    }else if(state == 5){
                        img_vocab_word.setText("Orange");
                        img_vocab_img.setImageResource(R.drawable.orange);
                    }
                }

                else if(topic_value.equals("Vegetables"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Potato");
                        img_vocab_img.setImageResource(R.drawable.potato);
                    }else if (state==2) {
                        img_vocab_word.setText("Artichoke");
                        img_vocab_img.setImageResource(R.drawable.artichoke);
                    }else if (state == 3){
                        img_vocab_word.setText("Carrot");
                        img_vocab_img.setImageResource(R.drawable.carrot);
                    }else if( state ==4){
                        img_vocab_word.setText("Cucumber");
                        img_vocab_img.setImageResource(R.drawable.cucumber);
                    }else if(state == 5){
                        img_vocab_word.setText("Onion");
                        img_vocab_img.setImageResource(R.drawable.onion);
                    }
                }

                else if(topic_value.equals("Nuts"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Coffee");
                        img_vocab_img.setImageResource(R.drawable.coffee);
                    }else if (state==2) {
                        img_vocab_word.setText("Corn");
                        img_vocab_img.setImageResource(R.drawable.corn);
                    }else if (state == 3){
                        img_vocab_word.setText("Peanut");
                        img_vocab_img.setImageResource(R.drawable.peanut);
                    }else if( state ==4){
                        img_vocab_word.setText("Rice");
                        img_vocab_img.setImageResource(R.drawable.rice);
                    }else if(state == 5){
                        img_vocab_word.setText("Wheat");
                        img_vocab_img.setImageResource(R.drawable.wheat);
                    }
                }

                else if(topic_value.equals("Food"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Cheese");
                        img_vocab_img.setImageResource(R.drawable.cheese);
                    }else if (state==2) {
                        img_vocab_word.setText("Butter");
                        img_vocab_img.setImageResource(R.drawable.butter);
                    }else if (state == 3){
                        img_vocab_word.setText("Milk");
                        img_vocab_img.setImageResource(R.drawable.milk);
                    }else if( state ==4){
                        img_vocab_word.setText("Cream");
                        img_vocab_img.setImageResource(R.drawable.cream);
                    }else if(state == 5){
                        img_vocab_word.setText("Yogurt");
                        img_vocab_img.setImageResource(R.drawable.yogurt);
                    }
                }

                else if(topic_value.equals("Seafood"))
                {
                    if(state!=1){
                        state-=1;
                    }else{
                        state=5;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Lobster");
                        img_vocab_img.setImageResource(R.drawable.lobster);
                    }else if (state==2) {
                        img_vocab_word.setText("Crayfish");
                        img_vocab_img.setImageResource(R.drawable.crayfish);
                    }else if (state == 3){
                        img_vocab_word.setText("Eel");
                        img_vocab_img.setImageResource(R.drawable.eel);
                    }else if( state ==4){
                        img_vocab_word.setText("Mussel");
                        img_vocab_img.setImageResource(R.drawable.mussel);
                    }else if(state == 5){
                        img_vocab_word.setText("Shrimp");
                        img_vocab_img.setImageResource(R.drawable.shrimp);
                    }
                }
            }
        });

        //
        right = findViewById(R.id.img_vocab_arrow_forward);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic_value.equals("Fruit"))
                {
                    if(state!=5){
                        state+=1;
                    }else{
                        state=1;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Apple");
                        img_vocab_img.setImageResource(R.drawable.apple);
                    }else if (state==2) {
                        img_vocab_word.setText("Banana");
                        img_vocab_img.setImageResource(R.drawable.banana);
                    }else if (state == 3){
                        img_vocab_word.setText("Water Melon");
                        img_vocab_img.setImageResource(R.drawable.watermelon);
                    }else if( state ==4){
                        img_vocab_word.setText("Cherry");
                        img_vocab_img.setImageResource(R.drawable.cherry);
                    }else if(state == 5){
                        img_vocab_word.setText("Orange");
                        img_vocab_img.setImageResource(R.drawable.orange);
                    }
                }

                else if(topic_value.equals("Vegetables"))
                {
                    if(state!=5){
                        state+=1;
                    }else{
                        state=1;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Potato");
                        img_vocab_img.setImageResource(R.drawable.potato);
                    }else if (state==2) {
                        img_vocab_word.setText("Artichoke");
                        img_vocab_img.setImageResource(R.drawable.artichoke);
                    }else if (state == 3){
                        img_vocab_word.setText("Carrot");
                        img_vocab_img.setImageResource(R.drawable.carrot);
                    }else if( state ==4){
                        img_vocab_word.setText("Cucumber");
                        img_vocab_img.setImageResource(R.drawable.cucumber);
                    }else if(state == 5){
                        img_vocab_word.setText("Onion");
                        img_vocab_img.setImageResource(R.drawable.onion);
                    }
                }

                else if(topic_value.equals("Nuts"))
                {
                    if(state!=5){
                        state+=1;
                    }else{
                        state=1;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Coffee");
                        img_vocab_img.setImageResource(R.drawable.coffee);
                    }else if (state==2) {
                        img_vocab_word.setText("Corn");
                        img_vocab_img.setImageResource(R.drawable.corn);
                    }else if (state == 3){
                        img_vocab_word.setText("Peanut");
                        img_vocab_img.setImageResource(R.drawable.peanut);
                    }else if( state ==4){
                        img_vocab_word.setText("Rice");
                        img_vocab_img.setImageResource(R.drawable.rice);
                    }else if(state == 5){
                        img_vocab_word.setText("Wheat");
                        img_vocab_img.setImageResource(R.drawable.wheat);
                    }
                }

                else if(topic_value.equals("Food"))
                {
                    if(state!=5){
                        state+=1;
                    }else{
                        state=1;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Cheese");
                        img_vocab_img.setImageResource(R.drawable.cheese);
                    }else if (state==2) {
                        img_vocab_word.setText("Butter");
                        img_vocab_img.setImageResource(R.drawable.butter);
                    }else if (state == 3){
                        img_vocab_word.setText("Milk");
                        img_vocab_img.setImageResource(R.drawable.milk);
                    }else if( state ==4){
                        img_vocab_word.setText("Cream");
                        img_vocab_img.setImageResource(R.drawable.cream);
                    }else if(state == 5){
                        img_vocab_word.setText("Yogurt");
                        img_vocab_img.setImageResource(R.drawable.yogurt);
                    }
                }

                else if(topic_value.equals("Seafood"))
                {
                    if(state!=5){
                        state+=1;
                    }else{
                        state=1;
                    }

                    if(state == 1){
                        img_vocab_word.setText("Lobster");
                        img_vocab_img.setImageResource(R.drawable.lobster);
                    }else if (state==2) {
                        img_vocab_word.setText("Crayfish");
                        img_vocab_img.setImageResource(R.drawable.crayfish);
                    }else if (state == 3){
                        img_vocab_word.setText("Eel");
                        img_vocab_img.setImageResource(R.drawable.eel);
                    }else if( state ==4){
                        img_vocab_word.setText("Mussel");
                        img_vocab_img.setImageResource(R.drawable.mussel);
                    }else if(state == 5){
                        img_vocab_word.setText("Shrimp");
                        img_vocab_img.setImageResource(R.drawable.shrimp);
                    }
                }
            }
        });

    }
}
