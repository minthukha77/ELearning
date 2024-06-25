package com.blissstock.mappingSite.service;

import java.util.List;

import com.blissstock.mappingSite.entity.BankInfo;
import com.blissstock.mappingSite.entity.PaymentAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.exceptions.UserNotFoundException;

public interface PaymentInfoService {

    public List<BankInfo> getSupportedPaymentMethods();

    public List<PaymentAccount> getPaymentInfo(UserInfo userInfo);

    public void updatePayment(List<PaymentAccount> paymentAccounts, Long uid) throws UserNotFoundException;

    public void deletePaymentInfo(Long paymentId) throws UserNotFoundException;

}
