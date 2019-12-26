package com.example.triviaappmodel.activityscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import mk.codeacademy.triviaquizz.R;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewScore adapter;

    //TODO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        intent.getStringExtra("NICK");

        String name = intent.getStringExtra("NICK");

        Player player = new Player();
        player.setName(name);

        ArrayList<Player> list=new ArrayList<>();
        list.add(player);

        recyclerView=findViewById(R.id.recyclerView_score);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerViewScore(list);
        recyclerView.setAdapter(adapter);

        adapter.addPlaer(player);

    }
}
