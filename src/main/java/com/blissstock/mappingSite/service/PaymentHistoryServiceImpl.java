package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blissstock.mappingSite.entity.PaymentHistory;
import com.blissstock.mappingSite.repository.PaymentHistoryRepository;
//import com.blissstock.mappingSite.service.PaymentHistoryService;

@Service
@Transactional
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public List<PaymentHistory> getAllPaymentHistory() {
        return paymentHistoryRepository.findAll();
    }

    @Override
    public Optional<PaymentHistory> getPaymentHistoryById(Long id) {
        return paymentHistoryRepository.findById(id);
    }

    @Override
    public PaymentHistory savePaymentHistory(PaymentHistory paymentHistory) {
        return paymentHistoryRepository.save(paymentHistory);
    }

    @Override
    public void deletePaymentHistory(Long id) {
        paymentHistoryRepository.deleteById(id);
    }
}
