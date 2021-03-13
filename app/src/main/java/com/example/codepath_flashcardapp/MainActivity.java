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

import java.util.List;


//Flashcard app for codepath intro to programming course
public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int flashcardIndex;

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
        ImageView deleteCard = (ImageView)findViewById(R.id.imageView_deleteCard);
        ImageView editCard = (ImageView)findViewById(R.id.imageView_editCard);
        ImageView nextCard = (ImageView)findViewById(R.id.imageView_nextCard);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        flashcardIndex = 0;

        if (allFlashcards.size() > 0) {
            //existing flashcards
            flashcardQuestion.setText(allFlashcards.get(flashcardIndex).getQuestion());
            flashcardAnswer.setText(allFlashcards.get(flashcardIndex).getAnswer());
        }

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

        editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 101);
            }
        });

        deleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() > 0) {
                    flashcardDatabase.deleteCard(allFlashcards.get(flashcardIndex).getQuestion());
                    allFlashcards.remove(flashcardIndex++);
                }
                nextCard.callOnClick();
            }
        });

        nextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0) {
                    Snackbar.make(findViewById(R.id.textView_Flashcard_Question),
                            "You don't have any flashcards right now.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    flashcardQuestion.setText("This is where your question would be, if you had one.");
                    flashcardAnswer.setText("This is where your answer would be, if you had one.");
                    flashcardQuestion.setVisibility(View.VISIBLE);
                    flashcardAnswer.setVisibility(View.INVISIBLE);
                    flashcardAnswer.setClickable(false);
                    flashcardQuestion.setClickable(true);
                    return;
                }
                if (!(++flashcardIndex < allFlashcards.size())) {
                    flashcardIndex = 0;
                }
                flashcardQuestion.setText(allFlashcards.get(flashcardIndex).getQuestion());
                flashcardAnswer.setText(allFlashcards.get(flashcardIndex).getAnswer());
                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);
                flashcardAnswer.setClickable(false);
                flashcardQuestion.setClickable(true);
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
                flashcardDatabase.insertCard(new Flashcard(question, answer));
            }

            allFlashcards = flashcardDatabase.getAllCards();
            flashcardIndex = allFlashcards.size()-2;
            ((ImageView)findViewById(R.id.imageView_nextCard)).callOnClick();
            return;
        }
        if (requestCode == 101 && data != null) { // this 100 needs to match the 100 we used when we called startActivityForResult!
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
                Flashcard card = allFlashcards.get(flashcardIndex);
                card.setQuestion(question);
                card.setAnswer(answer);
                flashcardDatabase.updateCard(card);
            }

            allFlashcards = flashcardDatabase.getAllCards();
            flashcardIndex--;
            ((ImageView)findViewById(R.id.imageView_nextCard)).callOnClick();
            return;
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