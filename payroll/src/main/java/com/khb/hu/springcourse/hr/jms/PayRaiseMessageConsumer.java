package com.khb.hu.springcourse.hr.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PayRaiseMessageConsumer {

    @JmsListener(destination = "payraise", containerFactory = "listenerContainerFactory")
    public void onPayRaiseMessage(PayRaiseMessage payRaiseMessage){
        System.out.format("New salary for employee %d is %f%n",
                payRaiseMessage.getEmployeeId(), payRaiseMessage.getNewSalary());
    }
}
