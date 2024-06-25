package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
  @Query(nativeQuery = true, value = "SELECT * FROM user_info WHERE user_account_mail=:email")
  public UserInfo findUserInfoByEmail(@Param("email") String email);

  @Query(nativeQuery = true, value = "select * from User_info where account_id in (select DISTINCT User_info.account_id from User_info join User_account on  User_info.account_id= User_account.account_id where User_account.role='ROLE_STUDENT' and User_info.account_id Not in (select join_course_user.Uid_fkey from join_course_user where join_course_user.course_id_fkey=:courseId))")
  public List<UserInfo> findStudentsToEnroll(@Param("courseId") Long courseId);

  @Query(nativeQuery = true, value = "select * from User_info where account_id = :account_id")
  public UserInfo findStudentById(@Param("account_id") Long account_id);

  @Query(nativeQuery = true, value = "select * from user_info where user_name=:userName and user_account_account_id=:accountId")
  UserInfo findByNameAndAccount(
      @Param("userName") String userName,
      @Param("accountId") Long accountId);

  @Query(value = "select * from user_info, user_account where user_info.account_id = user_account.account_id and user_account.role = 'ROLE_STUDENT' and lower(user_name) like :userName%", nativeQuery = true)
  public List<UserInfo> findByName(@Param("userName") String userName);

  @Query(value = "select * from user_info, user_account, test_student where user_info.account_id = user_account.account_id and test_student.test_test_id = :test_id and test_student.user_info_account_id = user_info.account_id and user_account.role = 'ROLE_STUDENT' and lower(user_name) like :userName%", nativeQuery = true)
  public List<UserInfo> findByNameandTestId(@Param("userName") String userName, @Param("test_id") Long test_id);
}
