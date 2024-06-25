package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.PaymentReceive;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentReceive, Long> {
    // @Query(value = "SELECT
    // o.payment_receive_date,o.payment_status,r.requested_user_name,r.requested_course_name,r.requested_course_fees
    // FROM payment_receive o,requested_course r WHERE
    // o.course_id_fkey=r.course_id_fkey AND
    // o.uid_fkey=r.uid_fkey",nativeQuery=true)
    // public List<PaymentReceive> findPayments();

    @Query(nativeQuery = true, value = "select * from payment_receive where join_id=:joinId")
    public PaymentReceive findByJoin(@Param("joinId") Long joinId);

    public List<PaymentReceive> findByPaymentStatus(String paymentStatus);
}
