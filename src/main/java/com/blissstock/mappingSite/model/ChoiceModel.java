package com.blissstock.mappingSite.model;

public class ChoiceModel {
    public int index;
    public String choice;
    public boolean correct;

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public ChoiceModel(int index, String choice, boolean correct) {
        this.index = index;
        this.choice = choice;
        this.correct = correct;
    }
}
