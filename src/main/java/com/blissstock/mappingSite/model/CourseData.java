package com.blissstock.mappingSite.model;

import java.util.Date;

import com.blissstock.mappingSite.entity.CourseInfo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CourseData {

    private Long courseId;

    private String courseName;

    private String classType;

    private String category;

    private String classLink;

    private String level;

    private String aboutCourse;

    private String prerequisite;

    private Integer maxStu;

    private Date startDate;

    private Date endDate;

    private int fees;

    private String teacherName;

    private Long teacherId;

    private String photo;

    private FileInfo coursePhoto;

    private CourseData() {

    };

    public static CourseData construct(CourseInfo courseInfo) {
        CourseData courseData = new CourseData();
        courseData.courseId = courseInfo.getCourseId();
        courseData.courseName = courseInfo.getCourseName();
        courseData.classType = courseInfo.getClassType();
        courseData.category = courseInfo.getCategory();
        courseData.classLink = courseInfo.getClassLink();
        courseData.level = courseInfo.getLevel();
        courseData.aboutCourse = courseInfo.getAboutCourse();
        courseData.prerequisite = courseInfo.getPrerequisite();
        courseData.maxStu = courseInfo.getMaxStu();
        courseData.startDate = courseInfo.getStartDate();
        courseData.endDate = courseInfo.getEndDate();
        courseData.fees = courseInfo.getFees();
        courseData.teacherName = courseInfo.getUserInfo().getUserName();
        courseData.teacherId = courseInfo.getUserInfo().getUid();
        courseData.photo = courseInfo.getUserInfo().getPhoto();
        
        return courseData;
    }

    public void setCoursePhoto(FileInfo info) {
        this.coursePhoto = info;
    }
}
