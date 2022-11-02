package com.khb.hu.springcourse.hr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company(){}

    public Company(String name) {
        this(null, name);
    }

    public Company(Integer id) {
        this(id, null);
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addEmployee(Employee employee){
        employee.setCompany(this);
        if(this.employees == null)
            this.employees = new ArrayList<>();
        this.employees.add(employee);
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

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
