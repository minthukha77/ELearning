package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.Optional;

import com.blissstock.mappingSite.entity.PaymentHistory;

public interface PaymentHistoryService {

    List<PaymentHistory> getAllPaymentHistory();

    Optional<PaymentHistory> getPaymentHistoryById(Long id);

    PaymentHistory savePaymentHistory(PaymentHistory paymentHistory);

    void deletePaymentHistory(Long id);
}
