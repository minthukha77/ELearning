package com.blissstock.mappingSite.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDTO {

	private Long reviewId;
	private String reviewStatus;
	// @NotBlank(message="Please choose review type")
	private int reviewType;

	@Min(value = 1, message = "Please fill rating")
	@Max(value = 5, message = "Please fill rating")
	private int star;

	@NotBlank(message = "Please fill feedback")
	private String feedback;

}
