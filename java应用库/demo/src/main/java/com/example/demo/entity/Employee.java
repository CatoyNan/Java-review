package com.example.demo.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @description: 实体类
 * @author: xjn
 * @create: 2019-04-19 12:21
 **/
public class Employee {
    private String name;
    private LocalDate birthDay;
}
