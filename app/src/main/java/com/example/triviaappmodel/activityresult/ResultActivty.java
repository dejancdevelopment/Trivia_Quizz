package com.example.triviaappmodel.activityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import mk.codeacademy.triviaquizz.R;

public class ResultActivty extends AppCompatActivity {

    private TextView corectTv,fromTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activty);

        Intent intent=getIntent();
        int correctAnswers=intent.getIntExtra("CORRECT",0);
        int fromListSize=intent.getIntExtra("FROM",0);

        corectTv=findViewById(R.id.correct_);
        corectTv.setText(String.valueOf(correctAnswers));
        fromTv=findViewById(R.id.from_);
        fromTv.setText(String.valueOf(fromListSize));

    }
}
