package com.blissstock.mappingSite.model;

import java.util.List;

public class QuestionAndCorrectAnswer {
    private long id;
    private String question_text;
    private String question_materials;
    private String question_materials_type;
    private List<ChoiceModel> choices;
    private String question_type;
    private Integer maximum_mark;

    public QuestionAndCorrectAnswer(long id, String question_text, String question_materials,
            String question_materials_type, List<ChoiceModel> choices, String question_type, Integer maximum_mark) {
        this.id = id;
        this.question_text = question_text;
        this.question_materials = question_materials;
        this.question_materials_type = question_materials_type;
        this.choices = choices;
        this.question_type = question_type;
        this.maximum_mark = maximum_mark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getQuestion_materials() {
        return question_materials;
    }

    public void setQuestion_materials(String question_materials) {
        this.question_materials = question_materials;
    }

    public String getQuestion_materials_type() {
        return question_materials_type;
    }

    public void setQuestion_materials_type(String question_materials_type) {
        this.question_materials_type = question_materials_type;
    }

    public List<ChoiceModel> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceModel> choices) {
        this.choices = choices;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public Integer getMaximum_mark() {
        return maximum_mark;
    }

    public void setMaximum_mark(Integer maximum_mark) {
        this.maximum_mark = maximum_mark;
    }
}
