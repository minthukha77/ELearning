package com.blissstock.mappingSite.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.blissstock.mappingSite.model.FileInfo;

@Data
@Getter
@Setter
public class HomeCourseInfoDTO {
    private String teacherName;
    private String CourseName;
    private FileInfo coursePhoto;
    private int price;
    private Long courseID;
    private FileInfo profilePic;

}
