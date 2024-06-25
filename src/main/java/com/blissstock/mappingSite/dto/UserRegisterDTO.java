package com.blissstock.mappingSite.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.interfaces.Confirmable;
import com.blissstock.mappingSite.utils.DateFormatter;
import com.blissstock.mappingSite.validation.ConstrainMessage;
import com.blissstock.mappingSite.validation.constrains.PasswordData;
import com.blissstock.mappingSite.validation.constrains.PasswordMatch;
import com.blissstock.mappingSite.validation.constrains.ValidDob;
import com.blissstock.mappingSite.validation.constrains.ValidEmail;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper = true)
@PasswordMatch
// @PasswordMatch only work with PasswordData class
public class UserRegisterDTO extends PasswordData implements Confirmable {

  @ValidEmail
  private String email;

  private Long uid;

  @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String name;

  @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{8,}$", message = ConstrainMessage.PASSWORD_CONSTRAIN_MESSAGE)
  @Size(min = 8, max = 32, message = ConstrainMessage.PASSWORD_LENGTH_CONSTRAIN_MESSAGE)
  private String password;

  @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{8,}$", message = ConstrainMessage.PASSWORD_CONSTRAIN_MESSAGE)
  @Size(min = 8, max = 32, message = ConstrainMessage.PASSWORD_LENGTH_CONSTRAIN_MESSAGE)
  private String confirmPassword;

  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String gender = "male";

  @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String phone;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  // @ValidDob
  private Date dob = new Date();

  // @Max(value = 99999, message = ConstrainMessage.INVALID_FORMAT_CONSTRAIN_MESSAGE)
  private int zipCode;

  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String city;

  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String division;

  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String address;

  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String education;

  @AssertTrue(message = ConstrainMessage.TERM_CONSTRAIN_MESSAGE)
  private boolean acceptTerm;

  // zmt
  // @NotBlank(message = ConstrainMessage.EMPTY_CONSTRAIN_MESSAGE)
  private String role;
  private Boolean isMailVerified = false;

  @Override
  public LinkedHashMap<String, String> toMap() {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    String city = getCity();
    String edu = getEducation();
        
    if (city == null) {
      if (edu == null) {
      
    map.put("Email", this.email);
    map.put("Name", this.name);
    map.put("Phone Number", this.phone);
    // map.put("Gender", this.gender);
    // map.put("Date of Birth", DateFormatter.format(this.dob));
    // map.put("Zip Code", this.zipCode + "");
    // map.put("City", this.city);
    // map.put("Division", this.division);
    // map.put("Address", this.address);
    // map.put("Education", this.education);
    return map;
  }
  else 
    {
    map.put("Email", this.email);
    map.put("Name", this.name);
    map.put("Phone Number", this.phone);
    // map.put("Gender", this.gender);
    // map.put("Date of Birth", DateFormatter.format(this.dob));
    // map.put("Zip Code", this.zipCode + "");
    // map.put("City", this.city);
    // map.put("Division", this.division);
    // map.put("Address", this.address);
    map.put("Education", this.education);
    return map;
    }
  } 
  else {   
    map.put("Email", this.email);
    map.put("Name", this.name);
    map.put("Phone Number", this.phone);
    map.put("Gender", this.gender);
    map.put("Date of Birth", DateFormatter.format(this.dob));
    map.put("Zip Code", this.zipCode + "");
    map.put("City", this.city);
    map.put("Division", this.division);
    map.put("Address", this.address);
    map.put("Education", this.education);
    return map;
    } 
  }

  // This Function has side use with
  public static UserInfo toUserInfo(
      UserRegisterDTO userRegisterDTO,
      UserInfo userInfo) {
    userInfo.setUserName(userRegisterDTO.getName());
    userInfo.setPhoneNo(userRegisterDTO.getPhone());
    userInfo.setGender(userRegisterDTO.getGender());
    userInfo.setBirthDate(userRegisterDTO.getDob());
    userInfo.setPostalCode(userRegisterDTO.getZipCode() + "");
    userInfo.setCity(userRegisterDTO.getCity());
    userInfo.setDivision(userRegisterDTO.getDivision());
    userInfo.setAddress(userRegisterDTO.getAddress());
    userInfo.setEducation(userRegisterDTO.getEducation());

    if (userRegisterDTO instanceof TeacherRegisterDTO) {
      TeacherRegisterDTO teacherRegisterDTO = (TeacherRegisterDTO) userRegisterDTO;
      userInfo.setNrc(teacherRegisterDTO.getNrc());
      userInfo.setSelfDescription(teacherRegisterDTO.getSelfDescription());
    }

    return userInfo;
  }

  public static UserInfo toUserInfo(UserRegisterDTO userRegisterDTO) {
    return toUserInfo(userRegisterDTO, new UserInfo());
  }

  public static UserRegisterDTO fromUserInfo(UserInfo userInfo) {
    TeacherRegisterDTO registerDTO = new TeacherRegisterDTO();
    registerDTO.setEmail(userInfo.getUserAccount().getMail());
    registerDTO.setName(userInfo.getUserName());
    // registerDTO.setPhone(userInfo.getPhoneNo());
    registerDTO.setPhone(userInfo.getPhoneNo());
    registerDTO.setGender(userInfo.getGender());
    registerDTO.setDob(userInfo.getBirthDate());
    registerDTO.setZipCode(Integer.parseInt(userInfo.getPostalCode()));
    registerDTO.setCity(userInfo.getCity());
    registerDTO.setDivision(userInfo.getDivision());
    registerDTO.setAddress(userInfo.getAddress());
    registerDTO.setEducation(userInfo.getEducation());
    registerDTO.setNrc(userInfo.getNrc());
    registerDTO.setSelfDescription(userInfo.getSelfDescription());

    return registerDTO;
  }

  public static List<UserRegisterDTO> fromUserInfoList(List<UserInfo> userInfoList) {

    List<UserRegisterDTO> registerDTOList = new ArrayList<UserRegisterDTO>();
    for (UserInfo userInfo : userInfoList) {

      UserRegisterDTO registerDTO = new UserRegisterDTO();
      registerDTO.setUid(userInfo.getUid());
      registerDTO.setEmail(userInfo.getUserAccount().getMail());
      registerDTO.setName(userInfo.getUserName());
      registerDTO.setPhone(userInfo.getPhoneNo());
      registerDTO.setPhone(userInfo.getPhoneNo());
      registerDTO.setGender(userInfo.getGender());
      registerDTO.setDob(userInfo.getBirthDate());
      registerDTO.setZipCode(Integer.parseInt(userInfo.getPostalCode()));
      registerDTO.setCity(userInfo.getCity());
      registerDTO.setDivision(userInfo.getDivision());
      registerDTO.setAddress(userInfo.getAddress());
      registerDTO.setEducation(userInfo.getEducation());
      registerDTOList.add(registerDTO);

    }
    return registerDTOList;
  }

  // todo find changing to static to non brick the System
  public static UserAccount toUserAccount(
      UserRegisterDTO userRegisterDTO,
      Date registeredDate) {
    UserAccount userAccount = new UserAccount();
    userAccount.setMail(userRegisterDTO.getEmail());
    userAccount.setPassword(userRegisterDTO.getPassword());
    // get uer role
    String userRole;
    if (userRegisterDTO instanceof TeacherRegisterDTO) {
      userRole = UserRole.TEACHER.getValue();
    } else {
      userRole = UserRole.STUDENT.getValue();
    }
    if (userRegisterDTO.getRole()!=null && userRegisterDTO.getRole().equals("admin")) {
      userRole = UserRole.ADMIN.getValue();
    }
    userAccount.setMailVerified(userRegisterDTO.getMailVerified());
    userAccount.setRole(
        userRole);
    if (registeredDate != null) {
      userAccount.setRegisteredDate(registeredDate);
    }

    return userAccount;
  }

  public static UserAccount toUserAccount(
      UserRegisterDTO userRegisterDTO) {
    return toUserAccount(userRegisterDTO, null);
  }

  // Constructors

  public UserRegisterDTO() {
  }

  public UserRegisterDTO(String email, String name, String password, String confirmPassword, String gender,
      String phone, Date dob, int zipCode, String city, String division, String address, String education,
      boolean acceptTerm) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.gender = gender;
    this.phone = phone;
    this.dob = dob;
    this.zipCode = zipCode;
    this.city = city;
    this.division = division;
    this.address = address;
    this.education = education;
    this.acceptTerm = acceptTerm;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getDob() {
    return this.dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public int getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDivision() {
    return this.division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEducation() {
    return this.education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public boolean isAcceptTerm() {
    return this.acceptTerm;
  }

  public boolean getAcceptTerm() {
    return this.acceptTerm;
  }

  public void setAcceptTerm(boolean acceptTerm) {
    this.acceptTerm = acceptTerm;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean getMailVerified() {
    return this.isMailVerified;
  }

  public void setRole(Boolean isMailVerified) {
    this.isMailVerified = isMailVerified;
  }

}
