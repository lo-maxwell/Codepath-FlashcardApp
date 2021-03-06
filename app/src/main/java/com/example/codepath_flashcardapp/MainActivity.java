package com.example.codepath_flashcardapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


//Flashcard app for codepath intro to programming course
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = (TextView)findViewById(R.id.textView_Flashcard_Question);
        TextView flashcardAnswer = (TextView)findViewById(R.id.textView_Flashcard_Answer);
        TextView flashcardMC1 = (TextView)findViewById(R.id.textView_Flashcard_mc1);
        TextView flashcardMC2 = (TextView)findViewById(R.id.textView_Flashcard_mc2);
        TextView flashcardMC3 = (TextView)findViewById(R.id.textView_Flashcard_mc3);
        ImageView addCard = (ImageView)findViewById(R.id.imageView_addCard);

        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);
                flashcardAnswer.setClickable(false);
                flashcardQuestion.setClickable(true);
                resetMCColor();
            }
        });

        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                flashcardAnswer.setVisibility(View.VISIBLE);
                flashcardQuestion.setVisibility(View.INVISIBLE);
                flashcardQuestion.setClickable(false);
                flashcardAnswer.setClickable(true);
                resetMCColor();
            }
        });

        flashcardMC1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (((ColorDrawable)flashcardMC2.getBackground()).getColor()==getResources().getColor(R.color.green, null)) {
                    resetMCColor();
                } else {
                    flashcardMC1.setBackgroundColor(getResources().getColor(R.color.red, null));
                    flashcardMC2.setBackgroundColor(getResources().getColor(R.color.green, null));
                }
            }
        });

        flashcardMC2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (((ColorDrawable)flashcardMC2.getBackground()).getColor()==getResources().getColor(R.color.green, null)) {
                    resetMCColor();
                } else {
                    flashcardMC2.setBackgroundColor(getResources().getColor(R.color.green, null));
                }
            }
        });

        flashcardMC3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (((ColorDrawable)flashcardMC2.getBackground()).getColor()==getResources().getColor(R.color.green, null)) {
                    resetMCColor();
                } else {
                    flashcardMC3.setBackgroundColor(getResources().getColor(R.color.red, null));
                    flashcardMC2.setBackgroundColor(getResources().getColor(R.color.green, null));
                }
            }
        });

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("answer");

            if (question.equals("")) {
                Snackbar.make(findViewById(R.id.textView_Flashcard_Question),
                        "Missing question text",
                        Snackbar.LENGTH_SHORT)
                        .show();
            } else if (answer.equals("")) {
                Snackbar.make(findViewById(R.id.textView_Flashcard_Question),
                        "Missing answer text",
                        Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                ((TextView) findViewById(R.id.textView_Flashcard_Question)).setText(question);
                ((TextView) findViewById(R.id.textView_Flashcard_Answer)).setText(answer);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void resetMCColor(){
        TextView flashcardMC1 = (TextView)findViewById(R.id.textView_Flashcard_mc1);
        TextView flashcardMC2 = (TextView)findViewById(R.id.textView_Flashcard_mc2);
        TextView flashcardMC3 = (TextView)findViewById(R.id.textView_Flashcard_mc3);
        flashcardMC1.setBackgroundColor(getResources().getColor(R.color.flashcardMCBackground, null));
        flashcardMC2.setBackgroundColor(getResources().getColor(R.color.flashcardMCBackground, null));
        flashcardMC3.setBackgroundColor(getResources().getColor(R.color.flashcardMCBackground, null));
    }

}