package com.blissstock.mappingSite.service;

import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import com.blissstock.mappingSite.dto.PasswordDTO;
import com.blissstock.mappingSite.dto.UserRegisterDTO;
import com.blissstock.mappingSite.entity.Token;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.AccountStatus;
import com.blissstock.mappingSite.enums.TokenType;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.UserAlreadyExistException;
import com.blissstock.mappingSite.repository.TokenRepository;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(
      UserServiceImpl.class);

  @Autowired
  UserRepository userRepo;
  @Autowired
  private final UserAccountRepository userAccountRepository;

  @Autowired
  private final UserInfoRepository userInfoRepository;

  @Autowired
  private final TokenRepository tokenRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private MailService mailService;

  public UserServiceImpl(
      UserAccountRepository userAccountRepository,
      UserInfoRepository userInfoRepository,
      TokenRepository tokenRepository) {
    this.userAccountRepository = userAccountRepository;
    this.userInfoRepository = userInfoRepository;
    this.tokenRepository = tokenRepository;
    // validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public UserInfo addUser(UserRegisterDTO userRegisterDTO)
      throws UserAlreadyExistException {
    UserInfo userInfo = UserRegisterDTO.toUserInfo(userRegisterDTO);
    UserAccount userAccount = UserRegisterDTO.toUserAccount(
        userRegisterDTO,
        GregorianCalendar.getInstance().getTime());
    // Encode Password
    userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
    // Set Status
    if (userAccount.getRole().equals(UserRole.STUDENT.getValue())) {
      userAccount.setAccountStatus(AccountStatus.REGISTERED.getValue());
    } else if (userAccount.getRole().equals(UserRole.TEACHER.getValue())) {
      userAccount.setAccountStatus(AccountStatus.REQUESTED.getValue());
    } else if (userAccount.getRole().equals(UserRole.ADMIN.getValue())) {
      userAccount.setAccountStatus(AccountStatus.REGISTERED.getValue());
    }

    if (this.isUserAccountPresent(userAccount.getMail())) {
      logger.warn("User with {} email already exists", userAccount.getMail());
      throw new UserAlreadyExistException();
    }

    /*
     * UserAccount savedUserAccount = userAccountRepository.save(userAccount);
     * userInfo.setUserAccount(savedUserAccount);
     */
    userInfo.setUserAccount(userAccount);
    UserInfo savedUserInfo = userInfoRepository.save(userInfo);
    // userAccountRepository.save(entity);
    return savedUserInfo;
  }

  @Override
  public void updateUser(UserRegisterDTO userRegisterDTO, Long id) {
    UserInfo existingUserInfo = userInfoRepository.findById(id).get();
    System.out.println(existingUserInfo);
    System.out.println(existingUserInfo.getUserAccount());
    if (existingUserInfo != null) {
      existingUserInfo = UserRegisterDTO.toUserInfo(userRegisterDTO, existingUserInfo);
    }
    userInfoRepository.save(existingUserInfo);
  }

  @Override
  public UserAccount getUserAccountByEmail(String email) {
    return userAccountRepository.findByMail(email);
  }

  @Override
  public boolean isUserAccountPresent(String email) {
    return userAccountRepository.findByMail(email) != null;
  }

  @Override
  public void createToken(
      UserAccount userAccount,
      String token,
      TokenType tokenType) {
    Token myToken = new Token(
        token,
        userAccount,
        tokenType,
        System.currentTimeMillis());
    tokenRepository.save(myToken);
  }

  @Override
  public Token getToken(String token, TokenType tokenType) {
    System.out.println("get token called");
    // TODO fix bug
    Token token1 = tokenRepository.getToken(token, tokenType.getValue());

    return token1;
  }

  @Override
  public UserAccount getUserAccountByToken(String verificationToken, String tokenType) {
    try {
      System.out.println("get account by token called");
      Long uid = tokenRepository.findByToken(verificationToken, tokenType);

      System.out.println("id is " + uid);

      if (uid == null) {
        System.out.println("uid is null");
        return null;
      }

      UserInfo userInfo = userRepo.findById(uid).orElse(null);
      UserAccount userAccount = userInfo.getUserAccount();

      return userAccount;

    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return null;
  }

  @Override
  public UserInfo getUserInfoByID(Long id) {
    return userInfoRepository.findById(id).get();
  }

  @Override
  public void updateUserAccount(UserAccount savedUserAccount) {
    userAccountRepository.save(savedUserAccount);
  }

  @Override
  public void updateToken(Token savedToken) {
    tokenRepository.save(savedToken);
  }

  @Override
  public void updatePassword(PasswordDTO passwordDTO, Long id) {
    UserAccount userAccount = userAccountRepository.findById(id).get();
    String newPassword = passwordEncoder.encode(passwordDTO.getPassword());
    userAccount.setPassword(newPassword);
    userAccountRepository.save(userAccount);

  }

  @Override
  public void updateUserInfo(UserInfo userInfo) {
    userInfoRepository.save(userInfo);
  }

  @Override
  public void setAsUsedToken(String token) {
    tokenRepository.setAsUsedtoken(token);
  }

}
