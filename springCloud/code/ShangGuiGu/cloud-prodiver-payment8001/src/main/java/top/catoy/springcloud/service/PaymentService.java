package top.catoy.springcloud.service;

import org.apache.ibatis.annotations.Param;
import top.catoy.springcloud.entities.Payment;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentByid(@Param("id") Long id);
}
