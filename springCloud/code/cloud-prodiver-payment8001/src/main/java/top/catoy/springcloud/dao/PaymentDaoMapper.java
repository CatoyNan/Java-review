package top.catoy.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.springcloud.entities.Payment;
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentByid(@Param("id") Long id);
}
