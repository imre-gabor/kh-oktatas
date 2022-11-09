package com.khb.hu.springcourse.payroll;

import com.khb.hu.springcourse.hr.jms.PayRaiseMessageConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {PayrollApplication.class, PayRaiseMessageConsumer.class})
public class PayrollApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

}
