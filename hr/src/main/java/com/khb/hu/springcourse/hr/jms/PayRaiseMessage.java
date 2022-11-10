package com.khb.hu.springcourse.hr.jms;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayRaiseMessage that = (PayRaiseMessage) o;
        return employeeId == that.employeeId && Double.compare(that.newSalary, newSalary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, newSalary);
    }
}
