package com.khb.hu.springcourse.hr.mapper;

import com.khb.hu.springcourse.hr.dto.CompanyDto;
import com.khb.hu.springcourse.hr.dto.EmployeeDto;
import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employee);

    Employee dtoToEmployee(EmployeeDto employeeDto);

    @Mapping(target = "employees", ignore = true)
    CompanyDto companyToDto(Company company);
}
