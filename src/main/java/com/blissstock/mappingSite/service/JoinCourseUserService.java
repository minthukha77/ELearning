package com.blissstock.mappingSite.service;

import java.util.List;

import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.UserInfo;

public interface JoinCourseUserService {
    List<JoinCourseUser> findByUserInfo(UserInfo userInfo);
}
