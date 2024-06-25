package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(nativeQuery = true, value="select * from review where uid_fkey=:uid")
	public List<Review> findReviews(@Param("uid")long uid);
}
