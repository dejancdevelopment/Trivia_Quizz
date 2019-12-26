package com.example.triviaappmodel.activitystart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.triviaappmodel.activityselection.QuestionSelectionActivity;

import mk.codeacademy.triviaquizz.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton imageButton=findViewById(R.id.startQuiz_btn_);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(StartActivity.this, QuestionSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
