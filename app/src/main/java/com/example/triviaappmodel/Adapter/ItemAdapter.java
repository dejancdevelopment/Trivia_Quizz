package com.example.triviaappmodel.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaappmodel.Model.QuestionClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mk.codeacademy.triviaquizz.R;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuestionClass> mList;
    private Context context;
    private boolean[] user_input;
    private int[] selectedItems;
    private RadioGroup lastCheckedRadio = null;

    public ItemAdapter(Context context, ArrayList<QuestionClass> mList) {
        this.mList = mList;
        this.context = context;
        this.user_input = new boolean[mList.size()];
        this.selectedItems = new int[mList.size()];

        for (int position = 0; position < mList.size(); position++) {
            final ArrayList<String> questions = new ArrayList<>();

            if (mList.get(position).getIncorrect_answers().size() < 3) {
                continue;
            }

            final String qCorrect = mList.get(position).getCorrect_answer();
            final String qWrong = String.valueOf(mList.get(position).getIncorrect_answers().get(0));
            final String qWrong1 = String.valueOf(mList.get(position).getIncorrect_answers().get(1));
            final String qWrong2 = String.valueOf(mList.get(position).getIncorrect_answers().get(2));

            questions.add(qCorrect);
            questions.add(qWrong);
            questions.add(qWrong1);
            questions.add(qWrong2);

            Collections.shuffle(questions);

            questions.add(0, "Chose Answer");
            mList.get(position).setIncorrect_answers(questions);
        }

    }

    public boolean[] getUser_input() {
        return user_input;
    }

    @Override
    public int getItemViewType(int position) {

        if (mList.get(position).getIncorrect_answers().size() == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boolean_question, parent, false);
            return new MyBooleanHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
            return new MyMulypleHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final String correct_Answer = mList.get(holder.getAdapterPosition()).getCorrect_answer();

        if (getItemViewType(holder.getAdapterPosition()) == 1) {
            ((MyBooleanHolder) holder).category.setText(mList.get(holder.getAdapterPosition()).getCategory());
            ((MyBooleanHolder) holder).question.setText(mList.get(holder.getAdapterPosition()).getQuestion());

            RadioGroup radioGroup = ((MyBooleanHolder) holder).radioGroup;
            radioGroup.check(selectedItems[holder.getAdapterPosition()]);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    String checkBoxString = checkedId == R.id.radio_true ? "true" : "false";
                    selectedItems[holder.getAdapterPosition()] = checkedId;
                    user_input[holder.getAdapterPosition()] = correct_Answer.equalsIgnoreCase(checkBoxString);
                }

            });
        } else {
            ((MyMulypleHolder) holder).question.setText(Html.fromHtml(mList.get(position).getCategory()));
            ((MyMulypleHolder) holder).category.setText(mList.get(position).getQuestion());

            final String qCorrect = mList.get(position).getCorrect_answer();
            List<String> questions = mList.get(holder.getAdapterPosition()).getIncorrect_answers();
            final ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, questions);
            ((MyMulypleHolder) holder).spinner.setAdapter(arrayAdapter);


            ((MyMulypleHolder) holder).spinner.setSelection(selectedItems[holder.getAdapterPosition()]);
            ((MyMulypleHolder) holder).spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedItems[holder.getAdapterPosition()] = position;
                    if (((MyMulypleHolder) holder).spinner.getSelectedItem().equals(qCorrect)) {

                        for (int i = 0; i < mList.size(); i++) {
                            if (mList.get(i).getCorrect_answer().equals(qCorrect)) {

                                user_input[i] = true;
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyBooleanHolder extends RecyclerView.ViewHolder {

        RadioButton radio_true, radio_false;
        RadioGroup radioGroup;
        TextView question;
        TextView category;

        public MyBooleanHolder(@NonNull View itemView) {
            super(itemView);

            radioGroup = itemView.findViewById(R.id.radio_btn_group);
            radio_true = itemView.findViewById(R.id.radio_true);
            radio_false = itemView.findViewById(R.id.radio_false);
            question = itemView.findViewById(R.id.question_text);
            category = itemView.findViewById(R.id.category_text);

        }

    }


    private class MyMulypleHolder extends RecyclerView.ViewHolder {

        TextView question, category;
        Spinner spinner;

        public MyMulypleHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.category_text);
            category = itemView.findViewById(R.id.question_text);
            spinner = itemView.findViewById(R.id.answer_spinner);


        }
    }

}
