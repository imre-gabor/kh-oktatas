package com.khb.hu.springcourse.hr.jms;

public class PayRaiseMessage {

    private int employeeId;
    private double newSalary;

    public PayRaiseMessage() {
    }

    public PayRaiseMessage(int employeeId, double newSalary) {
        this.employeeId = employeeId;
        this.newSalary = newSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }
}
