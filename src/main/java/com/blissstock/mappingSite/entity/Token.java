package com.blissstock.mappingSite.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.blissstock.mappingSite.enums.TokenType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  private String tokenType;

  @CreatedDate
  private Long created;

  private boolean isUsed;

  public Token(String token, UserAccount userAccount, TokenType tokenType) {
    this(token,userAccount,tokenType,null);
  }

  public Token(String token, UserAccount userAccount, TokenType tokenType, Long created) {
    this.token = token;
    this.userAccount = userAccount;
    this.tokenType = tokenType.getValue();
    this.created = created;
    this.isUsed = false;
  }

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_account")
  private UserAccount userAccount;

  private Date expiryDate;



}
