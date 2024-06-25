package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;

public interface UserAccountControlService {

    public void verifyUser(UserAccount userAccount);

    public void verifyUser(UserInfo userInfo);
    
    public void suspendUser(UserAccount userAccount);

    public void suspendUser(UserInfo userInfo);

    public void deleteUser(UserAccount userAccount);

    public void deleteUser(UserInfo userInfo);

    public void approveTeacher(UserAccount userAccount);

    public void approveTeacher(UserInfo userInfo);

    public void reactivateUser(UserInfo userInfo);

    public void reactivateUser(UserAccount userAccount);

    public void destroyUserSession(UserAccount userAccount);

}
