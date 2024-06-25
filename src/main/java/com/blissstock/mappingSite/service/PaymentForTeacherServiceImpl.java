package com.blissstock.mappingSite.service;

//import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.PaymentForTeacher;
//import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.PaymentForTeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentForTeacherServiceImpl implements PaymentForTeacherService {

    @Autowired
    private PaymentForTeacherRepository paymentForTeacherRepository;

    // @Autowired
    // private CourseInfoRepository courseInfoRepository;

    @Override
    public List<PaymentForTeacher> getAllPaymentForTeachers() {
        return paymentForTeacherRepository.findAll();
    }

    @Override
    public Optional<PaymentForTeacher> getPaymentForTeacherById(Long id) {
        return paymentForTeacherRepository.findById(id);
    }

    @Override
    public PaymentForTeacher savePaymentForTeacher(PaymentForTeacher paymentForTeacher) {
        return paymentForTeacherRepository.save(paymentForTeacher);
    }

    @Override
    public void deletePaymentForTeacher(Long id) {
        paymentForTeacherRepository.deleteById(id);
    }
    
    @Override
    public List<PaymentForTeacher> getPaymentForTeacherByCourseId(Long courseId) {
        return paymentForTeacherRepository.findByCourseId(courseId);
    }
}
