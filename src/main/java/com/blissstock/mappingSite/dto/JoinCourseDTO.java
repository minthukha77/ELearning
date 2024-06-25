package com.blissstock.mappingSite.dto;

import javax.validation.constraints.NotBlank;

import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
public class JoinCourseDTO {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    // private static final Logger logger = LoggerFactory.getLogger(
    // JoinCourseDTO.class);

    private Long joinId;

    @NotBlank(message = "Uid must not be blank")
    private Long uid;

    @NotBlank(message = "Course Id must not be blank")
    private Long courseId;

    // public JoinCourseUser toJoinCourseUser(
    // JoinCourseDTO joinCourseDTO) {
    // System.out.println("to join course user is called");
    // JoinCourseUser joinCourseUser = new JoinCourseUser();
    // // todo return joincoure user
    // try {

    // System.out.println(joinCourseDTO.getCourseId());
    // System.out.println(joinCourseDTO.getUid());
    // Optional<UserInfo> getUserInfo =
    // userRepository.findById(joinCourseDTO.getUid());

    // Optional<CourseInfo> getCourseInfo =
    // courseRepository.findById(joinCourseDTO.getCourseId());
    // // System.out.println(getCourseInfo.isPresent());
    // if (getCourseInfo.isPresent() && getUserInfo.isPresent()) {
    // CourseInfo courseInfo = getCourseInfo.get();
    // UserInfo userInfo = getUserInfo.get();
    // joinCourseUser.setCourseInfo(courseInfo);
    // joinCourseUser.setUserInfo(userInfo);
    // return joinCourseUser;

    // } else {
    // logger.warn("Course or user not found");
    // throw new Exception("Course and user info not found");
    // }
    // } catch (Exception e) {

    // }
    // return null;
    // }
}
