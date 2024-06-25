package com.blissstock.mappingSite.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.blissstock.mappingSite.dto.TeacherRegisterDTO;
import com.blissstock.mappingSite.dto.UserRegisterDTO;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.interfaces.Profile;
import com.blissstock.mappingSite.utils.DateFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@NoArgsConstructor
@ToString(exclude = {"courseInfo","paymentAccount"})
@Entity
@Table(name = "user_info")
public class UserInfo implements Profile {

  @Column(name = "uid")
  @Id
  private Long uid;

  @Column(name = "photo")
  private String photo;

  @Column(name = "user_name", length = 100)
  private String userName;

  @Size(max = 15, min = 6, message = "Invalid Phone Number")
  @Column(name = "phone_no")
  private String phoneNo;

  @Column(name = "gender", length = 20)
  private String gender;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "city", length = 50)
  private String city;

  @Column(name = "division", length = 50)
  private String division;

  @Column(name = "address", length = 255)
  private String address;

  @Column(name = "education", length = 255)
  private String education;

  @Column(name = "nrc", length = 30)
  private String nrc;

  @Column(name = "self_description")
  private String selfDescription;

  // mapping
  @OneToOne(fetch = FetchType.EAGER)
  @MapsId
  @JoinColumn(name = "account_id")
  UserAccount userAccount;

  /*
   * @OneToMany(
   * fetch = FetchType.LAZY,
   * cascade = CascadeType.ALL,
   * mappedBy = "userInfo"
   * )
   * 
   * @JsonIgnore
   * private List<Certificate> certificateInfo = new ArrayList<>();
   */

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userInfo")
  @JsonIgnore
  private List<PaymentAccount> paymentAccount = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userInfo")
  @JsonIgnore
  private List<JoinCourseUser> join = new ArrayList<>();

  @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "userInfo"
  )
  @JsonIgnore
  private List<CourseInfo> courseInfo = new ArrayList<>();

  @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "userInfo"
    )
    @JsonIgnore
    private List<PaymentRemark> paymentRemark = new ArrayList<>();

  public static UserInfo fromRegisterDTO(UserRegisterDTO userRegisterDTO) {
    UserInfo userInfo = new UserInfo();
    userInfo.userName = userRegisterDTO.getName();
    userInfo.phoneNo = userRegisterDTO.getPhone();
    userInfo.gender = userRegisterDTO.getGender();
    userInfo.birthDate = userRegisterDTO.getDob();
    userInfo.postalCode = userRegisterDTO.getZipCode() + "";
    userInfo.city = userRegisterDTO.getCity();
    userInfo.division = userRegisterDTO.getDivision();
    userInfo.address = userRegisterDTO.getAddress();
    userInfo.education = userRegisterDTO.getName();

    if (userRegisterDTO instanceof TeacherRegisterDTO) {
      TeacherRegisterDTO teacherRegisterDTO = (TeacherRegisterDTO) userRegisterDTO;
      userInfo.nrc = teacherRegisterDTO.getNrc();
      userInfo.selfDescription = teacherRegisterDTO.getSelfDescription();
    }

    return userInfo;
  }

  @Override
  public LinkedHashMap<String, String> toMap(boolean isSensitive) {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    UserRole role = UserRole.strToUserRole(this.userAccount.getRole());
    map.put("Name", this.userName);
    if (!isSensitive) {
      map.put("Phone Number", this.phoneNo);
    }
    map.put("Gender", this.gender);
    if (!isSensitive) {
      map.put("Date of Birth", DateFormatter.format(this.birthDate));
      map.put("Zip Code", this.postalCode + "");
      map.put("City", this.city);
      map.put("Division", this.division);
      map.put("Address", this.address);
    }
    map.put("Education", this.education);
    if (role == UserRole.TEACHER) {
      map.put("Self Description", this.selfDescription);
    }
    return map;
  }
}
