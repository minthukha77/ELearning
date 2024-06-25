package com.blissstock.mappingSite.service;

import java.util.List;

import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinCourseUserServiceImpl implements JoinCourseUserService{

    @Autowired
    JoinCourseUserRepository joinCourseUserRepository;

    @Override
    public List<JoinCourseUser> findByUserInfo(UserInfo userInfo) {
        return joinCourseUserRepository.findByUserInfo(userInfo);
    }
    
}
