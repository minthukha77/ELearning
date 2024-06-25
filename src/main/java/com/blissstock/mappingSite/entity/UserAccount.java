package com.blissstock.mappingSite.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.xml.bind.Marshaller.Listener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class UserAccount {

	@Column(name = "account_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

	

	@Column(name = "mail", length = 255)
	// @NotBlank(message="Please enter email address")
	private String mail;

	@Column(name = "isMailVerified", nullable = false)
	private boolean isMailVerified = false;

	@Column(name = "photo")
	private String photo;

	// @NotBlank(message="Please enter password.")
	@Column(name = "password", length = 64)
	private String password;

	@Column(name = "role", length = 25)
	private String role;

	@Column(name = "account_status", length = 10)
	private String accountStatus;

	// @NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "registered_date")
	private Date registeredDate;

	// mapping
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userAccount")
	@JsonIgnore
	private UserInfo userInfo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userAccount")
	@JsonIgnore
	private List<Token> tokens = new ArrayList<>();

	public UserAccount(Long accountId, String mail, boolean isMailVerified, String photo, String password, String role,
			String accountStatus, Date registeredDate, UserInfo userInfo) {
		this.accountId = accountId;
		this.mail = mail;
		this.isMailVerified = isMailVerified;
		this.photo = photo;
		this.password = password;
		this.role = role;
		this.accountStatus = accountStatus;
		this.registeredDate = registeredDate;
		this.userInfo = userInfo;
	}


}
