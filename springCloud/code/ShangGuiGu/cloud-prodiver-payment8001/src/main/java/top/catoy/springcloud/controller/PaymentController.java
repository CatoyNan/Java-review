package top.catoy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.catoy.springcloud.entities.CommonResult;
import top.catoy.springcloud.entities.Payment;
import top.catoy.springcloud.service.PaymentService;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("result:%s",result);
        if (result > 0) {
            return new CommonResult(200,"success",result);
        } else {

            return new CommonResult(500,"fail",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentByid(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentByid(id);
        if (payment == null) {
            return new CommonResult(500,"fail",null);
        } else {
            return new CommonResult(200, "success", payment);
        }
    }

}
