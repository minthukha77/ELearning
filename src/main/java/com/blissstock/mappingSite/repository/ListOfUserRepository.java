package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListOfUserRepository  extends JpaRepository<UserInfo, Long> {
    // @Query(nativeQuery = true, value="select * from review where uid_fkey=:uid")
	// public List<UserInfo> findUsers(@Param("uid")long uid);
}
