package com.blissstock.mappingSite.dto;

// import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoDTO {

    public String courseName;

    public String teacherName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date endDate;

    public int lowestFee;

    public int highestFee;

    public String logInUser;
    public Long logInUserId;

}
