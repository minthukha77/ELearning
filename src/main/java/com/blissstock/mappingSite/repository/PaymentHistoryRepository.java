package com.blissstock.mappingSite.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.blissstock.mappingSite.entity.PaymentForTeacher;
import com.blissstock.mappingSite.entity.PaymentHistory;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    PaymentHistory findByCourseId(Long courseId);
    
}
