package com.blissstock.mappingSite.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StuPaymentDTO {
    private Long paymentReceiveId;

    @NotNull(message="Please upload slip")
    private String slip;

    //@NotBlank
	private String paymentStatus = "Pending";
    

    /*@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentReceiveDate;*/

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @PastOrPresent
	private Date paymentReceiveDate;
    
}
