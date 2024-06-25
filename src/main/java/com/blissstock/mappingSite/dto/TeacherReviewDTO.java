package com.blissstock.mappingSite.dto;

import java.util.LinkedHashMap;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.blissstock.mappingSite.interfaces.TeacherReview;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Data
public class TeacherReviewDTO implements TeacherReview{

	private Long reviewId;
	
    @Min(value = 1, message = "Please fill review type")
    @Max(value = 2, message = "Please fill review type")
	private int reviewType;

    @NotBlank(message="Please fill feedback")
	private String feedback;

	  private String reviewStatus;

    @Override
	  public LinkedHashMap<String, String> toMapTrReview() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("review type", this.reviewType + "");
		map.put("feedback", this.feedback);
		
		return map;
	  }

	//Constructors

	public TeacherReviewDTO() {
	}

	public TeacherReviewDTO(Long reviewId, int reviewType, String feedback, String reviewStatus) {
		this.reviewId = reviewId;
		this.reviewType = reviewType;
		this.feedback = feedback;
		this.reviewStatus = reviewStatus;
	}

	public Long getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewType() {
		return this.reviewType;
	}

	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getReviewStatus() {
		return this.reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}


}
