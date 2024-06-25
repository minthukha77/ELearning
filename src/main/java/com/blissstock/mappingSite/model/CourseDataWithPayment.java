package com.blissstock.mappingSite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.PaymentAccount;
import com.blissstock.mappingSite.entity.PaymentReceive;
import com.blissstock.mappingSite.entity.UserInfo;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CourseDataWithPayment {

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

    private List<PaymentReceive> paymentStatus;

    private String truePaymentStatus;

    

   

    private CourseDataWithPayment() {

    };

    public static CourseDataWithPayment construct(CourseInfo courseInfo, List<PaymentReceive> list, UserInfo userInfo) {
        CourseDataWithPayment courseData = new CourseDataWithPayment();
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
        
        courseData.paymentStatus = list;
        List<PaymentLists> payUserList2 = new ArrayList<>();
        for(PaymentReceive paymentReceive:courseData.paymentStatus)
        {
            
            String paymentStatus = paymentReceive.getPaymentStatus();

            
            payUserList2.add(new PaymentLists(paymentStatus));
            
            courseData.truePaymentStatus = paymentReceive.getPaymentStatus();
        }   
        
        return courseData;
    }

    public void setCoursePhoto(FileInfo info) {
        this.coursePhoto = info;
    }
}
