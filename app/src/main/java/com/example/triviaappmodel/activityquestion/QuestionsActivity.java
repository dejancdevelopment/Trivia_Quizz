package com.example.triviaappmodel.activityquestion;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.triviaappmodel.Adapter.ItemAdapter;
import com.example.triviaappmodel.activityresult.ResultActivty;
import com.example.triviaappmodel.call.CallApi;
import com.example.triviaappmodel.Model.QuestionClass;
import com.example.triviaappmodel.Model.ResponseCall;
import com.example.triviaappmodel.call.RetrofitClient;

import mk.codeacademy.triviaquizz.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {

    ArrayList<QuestionClass> mQuestionList;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuestionList = new ArrayList<>();

        final Intent intent = getIntent();
        String noOfQuestion = intent.getStringExtra("Q_NUMBER");
        String category = intent.getStringExtra("SPIN_CATEGORY");
        String difficulty = intent.getStringExtra("SPIN_DIFF");
        String type = intent.getStringExtra("SPIN_TYPE");

        CallApi callApi = RetrofitClient.getInstance().getCallApi();
        callApi.getQuestions(noOfQuestion, category, difficulty, type).enqueue(new Callback<ResponseCall>() {
            @Override
            public void onResponse(Call<ResponseCall> call, Response<ResponseCall> response) {

                if (response.body() != null) {
                    mQuestionList=response.body().getQuestionClasses();

                }
                itemAdapter = new ItemAdapter(QuestionsActivity.this, mQuestionList);
                recyclerView.setAdapter(itemAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseCall> call, Throwable t) {

                Toast.makeText(QuestionsActivity.this, "Error " + t, Toast.LENGTH_LONG).show();

            }
        });

        final Button finishButton = findViewById(R.id.finishBtn);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int couter = 0;
                boolean[] user_input = itemAdapter.getUser_input();
                for (int i = 0; i < mQuestionList.size(); i++) {
                    if (user_input[i]) {
                        couter++;
                    }
                }

                Intent intentResult=new Intent(QuestionsActivity.this, ResultActivty.class);
                intentResult.putExtra("CORRECT",couter);
                intentResult.putExtra("FROM",mQuestionList.size());
                startActivity(intentResult);
            }

        });
    }
}
