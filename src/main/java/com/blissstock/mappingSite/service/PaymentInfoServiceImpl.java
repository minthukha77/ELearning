package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.Optional;

import com.blissstock.mappingSite.entity.BankInfo;
import com.blissstock.mappingSite.entity.PaymentAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.exceptions.UserNotFoundException;
import com.blissstock.mappingSite.repository.BankInfoRepository;
import com.blissstock.mappingSite.repository.PaymentAccountRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

  @Autowired
  PaymentAccountRepository paymentAccountRepository;

  @Autowired
  BankInfoRepository bankInfoRepository;

  @Autowired
  UserService userService;

  private static Logger logger = LoggerFactory.getLogger(
      PaymentInfoServiceImpl.class);

  @Override
  public List<BankInfo> getSupportedPaymentMethods() {
    return bankInfoRepository.findAll();
  }

  @Override
  public List<PaymentAccount> getPaymentInfo(UserInfo userInfo) {
    return paymentAccountRepository.findByUserInfo(userInfo);
  }

  @Override
  public void deletePaymentInfo(Long paymentId) throws UserNotFoundException {
    Optional<PaymentAccount> paymentAccount = paymentAccountRepository.findById(paymentId);

    if (paymentAccount.isPresent()) {

      paymentAccountRepository.deletePaymentInfo(paymentId);

    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public void updatePayment(List<PaymentAccount> paymentAccounts, Long uid)
      throws UserNotFoundException {
    logger.info("Updating Payment");
    UserInfo userInfo = userService.getUserInfoByID(uid);
    if (userInfo == null) {
      throw new UserNotFoundException();
    }
    List<BankInfo> bankInfos = getSupportedPaymentMethods();

    for (PaymentAccount paymentAccount : paymentAccounts) {
      paymentAccount.setUserInfo(userInfo);
      for (BankInfo bankInfo : bankInfos) {
        logger.info("I was here outside the bank loop!");
        if (String.valueOf(bankInfo.getBankId()).equals(String.valueOf(paymentAccount.getCheckedBank()))) {
          logger.info("I was here inside the bank loop!");
          paymentAccount.setBankInfo(bankInfo);
        }
      }
    }
    logger.debug("The bank accounts are {} and {}", paymentAccounts.get(0).getBankInfo(), paymentAccounts.get(1).getBankInfo());
    paymentAccountRepository.saveAll(paymentAccounts);
  }
}
