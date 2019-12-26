package com.example.triviaappmodel.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseCall {

    @SerializedName("results")
    ArrayList<QuestionClass> questionClasses;

    public ResponseCall(ArrayList<QuestionClass> questionClasses) {
        this.questionClasses = questionClasses;
    }

    public ArrayList<QuestionClass> getQuestionClasses() {
        return questionClasses;
    }

    public void setQuestionClasses(ArrayList<QuestionClass> questionClasses) {
        this.questionClasses = questionClasses;
    }
}
