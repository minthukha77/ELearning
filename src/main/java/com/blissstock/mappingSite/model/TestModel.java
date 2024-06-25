package com.blissstock.mappingSite.model;

public class TestModel {
    Integer course_id;
    String description;
    String section_name;
    Integer minutes_allowed;
    Integer passing_score;
    String date;
    String start_time;
    String end_time;
    String exam_status;

    public TestModel(Integer course_id, String description, String sectionName, Integer minutes_allowed,
            Integer passing_scored_percent, String date, String start_time, String end_time,
            String exam_status) {
        this.course_id = course_id;
        this.description = description;
        this.section_name = sectionName;
        this.minutes_allowed = minutes_allowed;
        this.passing_score = passing_scored_percent;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.exam_status = exam_status;
    }
}
