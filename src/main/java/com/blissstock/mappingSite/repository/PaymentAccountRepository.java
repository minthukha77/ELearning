package com.blissstock.mappingSite.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.blissstock.mappingSite.entity.PaymentAccount;
import com.blissstock.mappingSite.entity.UserInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentAccountRepository extends CrudRepository<PaymentAccount, Long> {
    List<PaymentAccount> findByUserInfo(UserInfo userInfo);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM payment_account p WHERE p.payment_account_id=:paymentId")
    void deletePaymentInfo(@Param("paymentId") Long paymentId);

}
