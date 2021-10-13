package com.casestudy.orderservice.service;

import com.casestudy.orderservice.entity.Payment;
import com.casestudy.orderservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment (Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrderId (Long Id) {
        return paymentRepository.findByOrderOrderId(Id);
    }

}
