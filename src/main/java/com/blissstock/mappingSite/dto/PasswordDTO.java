package com.blissstock.mappingSite.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blissstock.mappingSite.validation.ConstrainMessage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PasswordDTO {

  @NotBlank
  private String oldPassword;

  private String type;

  @Size(
    min = 8,
    max = 32,
    message = ConstrainMessage.PASSWORD_LENGTH_CONSTRAIN_MESSAGE
  )
  private String password;

  @Pattern(
    regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{8,}$",
    message = ConstrainMessage.PASSWORD_CONSTRAIN_MESSAGE
  )
  @Size(
    min = 8,
    max = 32,
    message = ConstrainMessage.PASSWORD_LENGTH_CONSTRAIN_MESSAGE
  )
  private String confirmPassword;

  //Constructors

  public PasswordDTO() {
  }

  public PasswordDTO(String oldPassword, String type, String password, String confirmPassword) {
    this.oldPassword = oldPassword;
    this.type = type;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getOldPassword() {
    return this.oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return this.confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

}
