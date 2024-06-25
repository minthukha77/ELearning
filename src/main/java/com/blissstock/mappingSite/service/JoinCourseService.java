package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.blissstock.mappingSite.dto.JoinCourseDTO;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.exceptions.UserAlreadyExistException;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import com.blissstock.mappingSite.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JoinCourseService {
    private static final Logger logger = LoggerFactory.getLogger(
            UserServiceImpl.class);

    @Autowired
    private final JoinCourseUserRepository joinCourseUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CourseInfoRepository courseRepository;

    @Autowired

    public JoinCourseService(JoinCourseUserRepository joinCourseUserRepository) {
        this.joinCourseUserRepository = joinCourseUserRepository;
    }

    public JoinCourseUser enrollStudent(JoinCourseDTO joinCourseDTO) throws Exception {
        logger.info("course_id: {}  user_id:{}", joinCourseDTO.getCourseId(), joinCourseDTO.getUid());

        UserInfo userInfo = userRepository.findById(joinCourseDTO.getUid()).orElseThrow();
        CourseInfo courseInfo = courseRepository.findById(joinCourseDTO.getCourseId()).orElseThrow();

        if (this.isUserAlreadyJoined(userInfo, courseInfo)) {
            logger.warn("User with {} email already exists" + joinCourseDTO.getUid() + " cid"
                    + joinCourseDTO.getCourseId());
            throw new UserAlreadyExistException();
        }

        logger.info("enroll student");

        JoinCourseUser joinCourseUser = new JoinCourseUser();
        joinCourseUser.setUserInfo(userInfo);
        joinCourseUser.setCourseInfo(courseInfo);

        JoinCourseUser savedJoinCourseUser = joinCourseUserRepository.save(joinCourseUser);
        return savedJoinCourseUser;

    }

    public boolean isUserAlreadyJoined(UserInfo userInfo, CourseInfo courseInfo) {
        return joinCourseUserRepository.findByUserInfoAndCourseInfo(userInfo, courseInfo) != null;
    }

    public List<JoinCourseUser> getJoinCourseUser(Long uid, Long courseId) {
        return joinCourseUserRepository.findByCourseUser(courseId, uid);
    }

}
