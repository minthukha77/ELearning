package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.UserAccount;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    @Query(value ="SELECT i.user_name,i.phone_no,u.account_status,u.mail FROM user_info i,user_account u WHERE i.account_id=u.account_id AND u.role=:role;",nativeQuery=true)
    public List<UserAccount> findByUserRoleU(@Param("role") String role);

    List<UserAccount> findByRole(String role);
    UserAccount findByMail(/* @Param("mail") */String mail);
}