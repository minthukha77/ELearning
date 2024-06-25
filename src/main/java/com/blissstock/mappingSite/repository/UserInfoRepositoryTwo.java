package com.blissstock.mappingSite.repository;


import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepositoryTwo extends JpaRepository<UserInfo, Long> {
    
    @Query(nativeQuery = true, value="select * from user_info where user_name=:userName and user_account_account_id=:accountId")
    UserInfo findByNameAndAccount(@Param("userName") String userName, @Param("accountId") Long accountId);

    UserInfo findByUserAccount(UserAccount userAccount);

    // List<UserInfo> findByCourse(CourseInfo courseInfo);
 }
