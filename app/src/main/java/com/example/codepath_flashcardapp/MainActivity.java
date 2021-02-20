package com.example.codepath_flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


//Flashcard app for codepath intro to programming course
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView_Flashcard_Answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.textView_Flashcard_Question).setVisibility(View.VISIBLE);
                findViewById(R.id.textView_Flashcard_Answer).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.textView_Flashcard_Question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.textView_Flashcard_Answer).setVisibility(View.VISIBLE);
                findViewById(R.id.textView_Flashcard_Question).setVisibility(View.INVISIBLE);
            }
        });
    }

}