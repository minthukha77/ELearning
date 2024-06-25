package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.PaymentForTeacher;

import java.util.List;
import java.util.Optional;

public interface PaymentForTeacherService {

    List<PaymentForTeacher> getAllPaymentForTeachers();

    Optional<PaymentForTeacher> getPaymentForTeacherById(Long id);

    PaymentForTeacher savePaymentForTeacher(PaymentForTeacher paymentForTeacher);

    void deletePaymentForTeacher(Long id);

    List<PaymentForTeacher> getPaymentForTeacherByCourseId(Long courseId);

}