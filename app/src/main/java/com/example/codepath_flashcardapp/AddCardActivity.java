package com.example.codepath_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        ImageView saveCard = (ImageView)findViewById(R.id.imageView_save);
        ImageView exitCard = (ImageView)findViewById(R.id.imageView_exit);

        saveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("question", ((EditText) findViewById(R.id.editText_Question)).getText().toString()); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("answer", ((EditText) findViewById(R.id.editText_Answer)).getText().toString()); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
                //overridePendingTransition(R.anim.tempanimleft, R.anim.tempanimright);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        exitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //overridePendingTransition(R.anim.tempanimright, R.anim.tempanimleft);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }
}
