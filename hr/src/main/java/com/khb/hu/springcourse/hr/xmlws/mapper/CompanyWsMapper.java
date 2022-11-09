package com.khb.hu.springcourse.hr.xmlws.mapper;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.xmlws.dto.CompanyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyWsMapper {

    CompanyDto companyToDto(Company company);
    List<CompanyDto> companiesToDtos(List<Company> companies);

    Company dtoToCompany(CompanyDto companyDto);
}
