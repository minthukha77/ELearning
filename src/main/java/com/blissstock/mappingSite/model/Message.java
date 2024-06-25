package com.blissstock.mappingSite.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Message {
  public static String PROFILE_EDIT_SUCCESSFULLY = "You have updated your profile successfully";
  public static String Payment_INFO_UPDATE_SUCCESSFULLY = "You have updated your payment info successfully";
  public static String ERROR = "Something went wrong, try again later";
  private boolean isError;
  private String text;
}
