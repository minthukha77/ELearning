package com.blissstock.mappingSite.dto;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseTimeDTO {
   
	//private Long courseId;

	@NotBlank(message="Please choose days")
	private String courseDays;

	@Temporal(TemporalType.TIMESTAMP)
    private Date courseStartTime;

	@Temporal(TemporalType.TIMESTAMP)
    private Date courseEndTime;

}
