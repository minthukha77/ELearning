package com.blissstock.mappingSite.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blissstock.mappingSite.entity.TestStudent;
import com.blissstock.mappingSite.entity.UserInfo;

public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {
    @Query(value = "Select * from test_student where test_test_id = :test_id", nativeQuery = true)
    public List<TestStudent> getStudentByTest(@Param("test_id") Long test_id);

    @Query(value = "select test_student.* from user_info, user_account, test_student where user_info.account_id = user_account.account_id and test_student.test_test_id = :test_id and test_student.user_info_account_id = user_info.account_id and user_account.role = 'ROLE_STUDENT' and lower(user_name) like :userName%", nativeQuery = true)
    public List<TestStudent> findByNameandTestId(@Param("userName") String userName, @Param("test_id") Long test_id);

    @Query(value = "Select * from test_student where user_info_account_id = :id and test_test_id = :test_id limit 1", nativeQuery = true)
    public TestStudent getStudentByID(@Param("id") Long id, @Param("test_id") Long test_id);

    @Query(value = "Select * from test_student where test_test_id = :test_id and user_info_account_id = :uid limit 1", nativeQuery = true)
    public TestStudent findByTestIdAndUid(@Param("test_id") Long test_id, @Param("uid") Long uid);
}
