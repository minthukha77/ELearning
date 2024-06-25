package com.blissstock.mappingSite.model;

public class StudentChoiceModel {
    public int index;
    public String choice;
    public boolean correct;
    public boolean student_choice;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isStudent_choice() {
        return student_choice;
    }

    public void setStudent_choice(boolean student_choice) {
        this.student_choice = student_choice;
    }

    public StudentChoiceModel(int index, String choice, boolean correct, boolean student_choice) {
        this.index = index;
        this.choice = choice;
        this.correct = correct;
        this.student_choice = student_choice;
    }
}
