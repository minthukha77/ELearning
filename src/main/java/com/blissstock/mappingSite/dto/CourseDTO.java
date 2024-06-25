package com.blissstock.mappingSite.dto;

import java.sql.Date;

//import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseDTO {
	private Long courseId;

	private String coursePhoto;

	// @NotBlank(message="Please enter coursename")
	private String courseName;

	// @NotBlank(message="Please enter class type")
	private String classType;

	// @NotBlank(message="Please choose category")
	private String category;

	// @NotBlank(message="Please enter courselevel")
	private String level;

	// @NotBlank(message="Please enter about course")
	private String aboutCourse;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	// @NotNull(message="Please enter course fees")
	private int fees;

	// @NotBlank(message="Please enter class link")
	private String classLink;

	private boolean isCourseApproved = false;
}
