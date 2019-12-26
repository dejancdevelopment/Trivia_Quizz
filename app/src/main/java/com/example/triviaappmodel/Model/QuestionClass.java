package com.example.triviaappmodel.Model;

import java.util.ArrayList;


public class QuestionClass {

    String category;

    String question;

    String correct_answer;

    ArrayList<String> incorrect_answers;

    public QuestionClass() {
    }

    public QuestionClass(String category, String question, String correct_answer, ArrayList<String> incorrect_answers) {
        this.category = category;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
