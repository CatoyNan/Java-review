package top.catoy.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.springcloud.dao.PaymentDaoMapper;
import top.catoy.springcloud.entities.Payment;
import top.catoy.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDaoMapper paymentDaoMapper;

    @Override
    public int create(Payment payment) {
        return paymentDaoMapper.create(payment);
    }

    @Override
    public Payment getPaymentByid(Long id) {
        return paymentDaoMapper.getPaymentByid();
    }
}
