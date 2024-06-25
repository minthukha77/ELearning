package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.PaymentHistory;
import com.blissstock.mappingSite.entity.PaymentRemark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRemarkRepository extends JpaRepository<PaymentRemark, Long> {
    
}
