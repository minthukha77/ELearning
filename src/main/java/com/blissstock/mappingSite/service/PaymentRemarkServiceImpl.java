package com.blissstock.mappingSite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blissstock.mappingSite.entity.PaymentRemark;
import com.blissstock.mappingSite.repository.PaymentRemarkRepository;
import com.blissstock.mappingSite.service.PaymentRemarkService;

@Service
@Transactional
public class PaymentRemarkServiceImpl implements PaymentRemarkService {

    @Autowired
    private PaymentRemarkRepository paymentRemarkRepository;

    @Override
    public List<PaymentRemark> getAllPaymentRemarks() {
        return paymentRemarkRepository.findAll();
    }

    @Override
    public PaymentRemark getPaymentRemarkById(Long paymentRemarkId) {
        return paymentRemarkRepository.findById(paymentRemarkId).orElse(null);
    }

    @Override
    public PaymentRemark savePaymentRemark(PaymentRemark paymentRemark) {
        return paymentRemarkRepository.save(paymentRemark);
    }

    @Override
    public void deletePaymentRemarkById(Long paymentRemarkId) {
        paymentRemarkRepository.deleteById(paymentRemarkId);
    }

}
