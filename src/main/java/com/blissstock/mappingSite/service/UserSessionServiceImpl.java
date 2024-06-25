package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.stream.Collectors;

import com.blissstock.mappingSite.entity.CustomUser;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  UserService userService;

  @Override
  public UserRole getRole() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    System.out.println(auth.getAuthorities());
    List<UserRole> userRoles = auth
      .getAuthorities()
      .stream()
      .map(e -> UserRole.strToUserRole(e.getAuthority()))
      .collect(Collectors.toList());
    if (userRoles.isEmpty()) {
      return UserRole.GUEST_USER;
    }
    return userRoles.get(0);
  }

  @Override
  public String getEmail() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    return auth.getName();
  }

  @Override
  public Long getId() {
    Long id = 0L;
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    if (auth != null) {
      Object principal = auth.getPrincipal();
      if (principal instanceof CustomUser) {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        id = customUser.getId();
      }
    }

    return id;
  }

  @Override
  public boolean isAuthenticated() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    if (auth == null) return false;
    return auth.isAuthenticated();
  }

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public UserAccount getUserAccount() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    String email = auth.getName();
    return userAccountRepository.findByMail(email);
  }

  @Override
  public UserInfo getUserInfo() {
    return userService.getUserInfoByID(getId());
  }
}
