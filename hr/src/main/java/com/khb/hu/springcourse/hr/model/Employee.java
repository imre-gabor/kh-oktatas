package com.khb.hu.springcourse.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String job;
    private double salary;
    private LocalDate workStart;

    public Employee(){}

    public Employee(Integer id, String name, String job, double salary, LocalDate workStart) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.workStart = workStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDate workStart) {
        this.workStart = workStart;
    }
}
