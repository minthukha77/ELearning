package com.blissstock.mappingSite.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseTestingDTO {
	private Long courseId;

	@NotBlank(message = "Please enter course name")
	private String courseName;

	@NotBlank(message = "Please enter class type")
	private String classType;

	@NotBlank(message = "Please choose category")
	private String category;

	@NotBlank(message = "Please choose course level")
	private String level;

	@NotBlank(message = "Please enter course name")
	private String aboutCourse;

	@NotBlank(message = "Please enter class type")
	private String classLink;

	@NotNull(message = "Please enter fee")
	private int fees;

	@NotNull(message = "Please enter student number")
	private int stuNum;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@PastOrPresent

	// @NotEmpty(message ="Please choose start Date")
	private Date startDate;

	// @NotNull
	// @DateTimeFormat(pattern = "yyyy-MM-dd")

	// @NotEmpty(message ="Please choose end Date")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@PastOrPresent
	private Date endDate;

	private boolean isCourseApproved = false;

}
