package com.blissstock.mappingSite.dto;

import java.util.LinkedHashMap;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherRegisterDTO extends UserRegisterDTO{
  
  @NotBlank(message = "This field is required")
  private String nrc;

/*   @NotBlank(message = "This field is required")
  private String award; */

  @NotBlank(message = "This field is required")
  private String selfDescription;

  @Override
  public LinkedHashMap<String,String> toMap(){
    LinkedHashMap<String,String> map = super.toMap();
    map.put("NRC",this.nrc);
    //map.put("award",this.award);
    map.put("Self Description",this.selfDescription);
    return map;
  }

/* 
  @NotBlank(message = "This field is required")
  private String primaryServiceName;

  @NotBlank(message = "This field is required")
  private String primaryAccountName;

  @NotBlank(message = "This field is required")
  @Pattern(regexp = "[0-9]+", message = "Account Number cannot contain character")
  private String primaryAccountNumber;

  @NotBlank(message = "This field is required")
  private String secondaryServiceName;
  
  @NotBlank(message = "This field is required")
  private String secondaryAccountName;
  
  @NotBlank(message = "This field is required")
  @Pattern(regexp = "/[0-9]+", message = "Account Number cannot contain character")
  @NotBlank(message = "This field is required")
  private String secondaryAccountNumber; */

  //Constructors

  public TeacherRegisterDTO() {
  }

  public TeacherRegisterDTO(String nrc, String award, String selfDescription) {
    this.nrc = nrc;
    //this.award = award;
    this.selfDescription = selfDescription;
  }

  public String getNrc() {
    return this.nrc;
  }

  public void setNrc(String nrc) {
    this.nrc = nrc;
  }

  /* public String getAward() {
    return this.award;
  }

  public void setAward(String award) {
    this.award = award;
  } */

  public String getSelfDescription() {
    return this.selfDescription;
  }

  public void setSelfDescription(String selfDescription) {
    this.selfDescription = selfDescription;
  }


}
