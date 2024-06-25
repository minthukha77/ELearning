package com.blissstock.mappingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blissstock.mappingSite.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query(value = "Select * from result where test_test_id = :test_id order by test_test_id desc", nativeQuery = true)
    public List<Result> getListByTestId(@Param("test_id") Long test_id);

    @Query(value = "Select * from result where test_test_id = :test_id and user_account_id = :user_id order by result_id desc", nativeQuery = true)
    public Result getResultByTestIdAndUser(@Param("test_id") Long test_id, @Param("user_id") Long user_id);
}
