package com.blissstock.mappingSite.model;

import java.util.List;

public class QuestionAndCorrectAnswerAndStudentAnswer {
    private long id;
    private Integer student_answer_id;
    private String student_answer;
    private String student_answer_link;
    private String question_text;
    private String question_materials;
    private String question_materials_type;
    private List<StudentChoiceModel> choices;
    private String question_type;
    private Integer maximum_mark;
    private Integer acquired_mark;

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

    public List<StudentChoiceModel> getChoices() {
        return choices;
    }

    public void setChoices(List<StudentChoiceModel> choices) {
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

    public Integer getAcquired_mark() {
        return acquired_mark;
    }

    public void setAcquired_mark(Integer acquired_mark) {
        this.acquired_mark = acquired_mark;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public String getStudent_answer_link() {
        return student_answer_link;
    }

    public void setStudent_answer_link(String student_answer_link) {
        this.student_answer_link = student_answer_link;
    }

    public QuestionAndCorrectAnswerAndStudentAnswer(long id, Integer student_answer_id, String student_answer,
            String student_answer_link,
            String question_text, String question_materials, String question_materials_type,
            List<StudentChoiceModel> choices, String question_type, Integer maximum_mark, Integer acquired_mark) {
        this.id = id;
        this.student_answer_id = student_answer_id;
        this.student_answer = student_answer;
        this.student_answer_link = student_answer_link;
        this.question_text = question_text;
        this.question_materials = question_materials;
        this.question_materials_type = question_materials_type;
        this.choices = choices;
        this.question_type = question_type;
        this.maximum_mark = maximum_mark;
        this.acquired_mark = acquired_mark;
    }

    public Integer getStudent_answer_id() {
        return student_answer_id;
    }

    public void setStudent_answer_id(Integer student_answer_id) {
        this.student_answer_id = student_answer_id;
    }

}
