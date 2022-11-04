package com.khb.hu.springcourse.hr.mapper;

import com.khb.hu.springcourse.hr.dto.EmployeeDto;
import com.khb.hu.springcourse.hr.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employee);

    Employee dtoToEmployee(EmployeeDto employeeDto);
}
