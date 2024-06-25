package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.BankInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
}
