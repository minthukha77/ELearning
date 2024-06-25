package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.stream.Collectors;

import com.blissstock.mappingSite.entity.CustomUser;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.AccountStatus;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

@Service
public class UserAccountControlServiceImpl implements UserAccountControlService {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountControlServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    SessionRegistry sessionRegistry;

    @Override
    public void verifyUser(UserAccount userAccount) {
        logger.info("verify user {}", userAccount.getAccountId());
        userAccount.setAccountStatus(AccountStatus.VERIFIED.getValue());
        userService.updateUserAccount(userAccount);
    }

    @Override
    public void verifyUser(UserInfo userInfo) {
        verifyUser(userInfo.getUserAccount());
    }

    @Override
    public void suspendUser(UserAccount userAccount) {
        logger.info("suspend user {}", userAccount.getAccountId());
        userAccount.setAccountStatus(AccountStatus.SUSPENDED.getValue());
        userService.updateUserAccount(userAccount);
        destroyUserSession(userAccount);
    }

    @Override
    public void suspendUser(UserInfo userInfo) {
        suspendUser(userInfo.getUserAccount());
    }

    @Override
    public void reactivateUser(UserAccount userAccount) {
        logger.info("reactivate user {}", userAccount.getAccountId());
        if (userAccount.getRole().equals("ROLE_STUDENT"))
            userAccount.setAccountStatus(AccountStatus.REGISTERED.getValue());
        else
            userAccount.setAccountStatus(AccountStatus.VERIFIED.getValue());
        userService.updateUserAccount(userAccount);
    }

    @Override
    public void reactivateUser(UserInfo userInfo) {
        reactivateUser(userInfo.getUserAccount());
    }

    @Override
    public void deleteUser(UserAccount userAccount) {
        UserInfo userInfo = userInfoRepository.findById(userAccount.getAccountId()).get();
        deleteUser(userInfo);
        
    }

    @Override
    public void deleteUser(UserInfo userInfo) {
        logger.info("delete user {}", userInfo.getUid());
        userInfoRepository.delete(userInfo);
        userAccountRepository.delete(userInfo.getUserAccount());
        destroyUserSession(userInfo.getUserAccount());

    }

    @Override
    public void approveTeacher(UserAccount userAccount) {
        logger.info("approve [user] {}", userAccount.getAccountId());
        userAccount.setAccountStatus(AccountStatus.SUSPENDED.getValue());
        userService.updateUserAccount(userAccount);

    }

    @Override
    public void approveTeacher(UserInfo userInfo) {
        approveTeacher(userInfo.getUserAccount());

    }

    @Override
    public void destroyUserSession(UserAccount userAccount) {
        logger.info("size {}", sessionRegistry.getAllPrincipals());
        // Get Principle according to the userAccount
        List<CustomUser> principals = sessionRegistry.getAllPrincipals().stream().filter((e) -> {
            if (e instanceof CustomUser) {
                CustomUser customUser = (CustomUser) e;
                logger.debug("getUserName {}", customUser.getUsername());
                return customUser.getUsername().equals(userAccount.getMail());
            }
            logger.debug("not an instance");
            return false;
        }).map((e) -> (CustomUser) e).collect(Collectors.toList());

        // Return when there is no matched principals
        if (principals.isEmpty()) {
            logger.warn("no session with email {} ", userAccount.getMail());
            return;
        }

        // Expired all session
        for (CustomUser customUser : principals) {
            sessionRegistry.getAllSessions(customUser, false).stream().forEach(e -> {
                e.expireNow();
            });
        }
        logger.info("successfully destroy user {}", userAccount.getAccountId());
    }

}
