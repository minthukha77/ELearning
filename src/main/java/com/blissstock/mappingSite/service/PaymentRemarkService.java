package com.blissstock.mappingSite.service;

import java.util.List;
import com.blissstock.mappingSite.entity.PaymentRemark;

public interface PaymentRemarkService {
    List<PaymentRemark> getAllPaymentRemarks();
    PaymentRemark getPaymentRemarkById(Long paymentRemarkId);
    PaymentRemark savePaymentRemark(PaymentRemark paymentRemark);
    void deletePaymentRemarkById(Long paymentRemarkId);
}
