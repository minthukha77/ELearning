package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.dto.PasswordDTO;
import com.blissstock.mappingSite.dto.UserRegisterDTO;
import com.blissstock.mappingSite.entity.Token;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.TokenType;

public interface UserService {
  public UserInfo addUser(UserRegisterDTO userRegisterDTO) throws Exception;

  void createToken(UserAccount userAccount, String token, TokenType tokenType);


  public UserAccount getUserAccountByEmail(String email);

  public UserInfo getUserInfoByID(Long id);

  public UserAccount getUserAccountByToken(String token, String verificationToken);
  
  public Token getToken(String VerificationToken, TokenType tokenType);
  
  public boolean isUserAccountPresent(String email);

  public void updateUserAccount(UserAccount savedUserAccount);

  public void updateToken(Token savedToken);

  public void updatePassword(PasswordDTO passwordDTO, Long id);

  public void updateUser(UserRegisterDTO userRegisterDTO, Long id);

  public void updateUserInfo(UserInfo userInfo);

  public void setAsUsedToken(String token);

}
