package com.example.triviaappmodel.activityscore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mk.codeacademy.triviaquizz.R;

public class RecyclerViewScore extends RecyclerView.Adapter<RecyclerViewScore.MyHolder> {

    List<Player> mList;

    public RecyclerViewScore(List<Player> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        Player player=mList.get(holder.getAdapterPosition());

        holder.score.setText(player.getScore());
        holder.name.setText(player.getName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView score;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            score=itemView.findViewById(R.id.score_);
            name=itemView.findViewById(R.id.scoreName_);
        }
    }

    public void addPlaer(Player player) {

        mList.add(player);
        notifyItemInserted(mList.size());
    }
}
