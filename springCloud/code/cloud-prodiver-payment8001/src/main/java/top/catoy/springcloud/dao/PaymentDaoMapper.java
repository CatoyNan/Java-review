package top.catoy.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import top.catoy.springcloud.entities.Payment;
@Mapper
public interface PaymentDaoMapper {
    public int create(Payment payment);

    public Payment getPaymentByid();
}
