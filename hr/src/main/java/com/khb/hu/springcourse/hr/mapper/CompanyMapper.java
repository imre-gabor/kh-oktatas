package com.khb.hu.springcourse.hr.mapper;

import com.khb.hu.springcourse.hr.dto.CompanyDto;
import com.khb.hu.springcourse.hr.dto.EmployeeDto;
import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto companyToDto(Company company);

    List<CompanyDto> companiesToDtos(List<Company> company);
    Company dtoToCompany(CompanyDto companyDto);

    @Mapping(target = "company", ignore = true)
    EmployeeDto employeeDto(Employee employee);
}
