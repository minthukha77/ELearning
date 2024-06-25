package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "SELECT i.*,u.* FROM user_info i,user_account u WHERE i.account_id=u.account_id AND u.role=:role", nativeQuery = true)
    public List<UserInfo> findByUserRoleI(@Param("role") String role);
    // public List<UserInfo> findByUserRoleI();
    // SELECT
    // o.payment_receive_date,o.payment_status,r.requested_user_name,r.requested_course_name,r.requested_course_fees
    // FROM payment_receive o,requested_course r WHERE
    // o.course_id_fkey=r.course_id_fkey AND o.uid_fkey=r.uid_fkey;
    // public List<UserInfo> findByUserRoleI(@Param("role") String role);
    // public List<UserInfo> findByUserRoleI();
    // public List<UserInfo> findByUserRoleI();

    // @Query(value ="SELECT i.user_name,i.user_account_mail,u.account_status FROM
    // user_info i, user_account u, join_course_user j WHERE j.course_id=50004 AND
    // j.uid=i.uid AND j.uid=u.account_id",nativeQuery=true )
    // @Query(value="SELECT i.user_name,i.user_account_mail,u.account_status FROM
    // join_course_user j INNER JOIN user_account u ON j.uid=u.account_id INNER JOIN
    // user_info i ON i.uid=j.uid WHERE j.course_id=50004",nativeQuery = true)
    // @Query(value = "SELECT i.user_name,i.user_account_mail FROM join_course_user
    // j INNER JOIN user_info i ON i.uid=j.uid WHERE j.course_id=50004;",nativeQuery
    // = true)
    // @Query(value = "SELECT uid FROM join_course_user WHERE
    // course_id=50004;",nativeQuery = true)
    // public List<UserInfo> findByCourse(@Param("course_id") Long course_id);
    @Query(value = "SELECT i.uid,i.user_name FROM user_info i, join_user_course j WHERE j.course_id=50004 AND i.uid=j.uid;", nativeQuery = true)
    public List<UserInfo> findByCourseI();

    @Query(nativeQuery = true, value = "delete from user_info  where uid =:userId")
    public UserInfo removeById(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM user_info WHERE user_account_mail=:email")
    public UserInfo findUserInfoByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "select * from user_info where user_name=:userName and user_account_account_id=:accountId")
    UserInfo findByNameAndAccount(
            @Param("userName") String userName,
            @Param("accountId") Long accountId);

    @Query(nativeQuery = true, value = "select * from user_info where account_id=:accountId limit 1")
    UserInfo findByAccount(
            @Param("accountId") Long accountId);

}
