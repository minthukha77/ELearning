package com.blissstock.mappingSite.model;

import com.blissstock.mappingSite.entity.Test;
import com.blissstock.mappingSite.entity.UserInfo;

public class TestStudentWithMarkedCountModel {
    public Long id;
    public Test test;
    public UserInfo userInfo;
    public int total_free_answer;
    public int total_unmark_answer;

    public TestStudentWithMarkedCountModel(Long id, Test test, UserInfo userInfo, int total_free_answer,
            int total_unmark_answer) {
        this.id = id;
        this.test = test;
        this.userInfo = userInfo;
        this.total_free_answer = total_free_answer;
        this.total_unmark_answer = total_unmark_answer;
    }
}
