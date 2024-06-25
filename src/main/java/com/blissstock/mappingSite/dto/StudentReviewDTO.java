package com.blissstock.mappingSite.dto;

import java.util.LinkedHashMap;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.blissstock.mappingSite.interfaces.StudentReview;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentReviewDTO implements StudentReview{

	  private Long reviewId;

    @Min(value = 1, message = "Please fill rating")
    @Max(value = 5, message = "Please fill rating")
	  private int star;

    @NotBlank(message="Please fill feedback")
	  private String feedback;


	  @Override
	  public LinkedHashMap<String, String> toMapReview() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("star", this.star + "");
		map.put("feedback", this.feedback);
		
		return map;
	  }
	
	//Constructors

	public StudentReviewDTO() {
	}

	public StudentReviewDTO(Long reviewId, int star, String feedback) {
		this.reviewId = reviewId;
		this.star = star;
		this.feedback = feedback;
	}

	public Long getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	
}
