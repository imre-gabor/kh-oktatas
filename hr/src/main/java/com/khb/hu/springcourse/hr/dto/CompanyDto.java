package com.khb.hu.springcourse.hr.dto;

import com.khb.hu.springcourse.hr.model.Address;
import com.khb.hu.springcourse.hr.model.Employee;

import java.util.List;


public class CompanyDto {
    private Integer id;
    private String name;

    private List<EmployeeDto> employees;
    private List<AddressDto> addresses;

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

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }
}
