package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import org.springframework.security.core.Authentication;

public interface UserSessionService {
    public Authentication getAuthentication();
    public UserAccount getUserAccount();
    public UserInfo getUserInfo();
    public UserRole getRole();
    public String getEmail();
    public boolean isAuthenticated();
    public Long getId();

}
