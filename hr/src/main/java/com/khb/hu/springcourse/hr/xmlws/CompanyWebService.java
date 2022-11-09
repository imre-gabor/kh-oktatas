package com.khb.hu.springcourse.hr.xmlws;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.service.CompanyService;
import com.khb.hu.springcourse.hr.xmlws.dto.CompanyDto;
import com.khb.hu.springcourse.hr.xmlws.mapper.CompanyWsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@Service
public class CompanyWebService {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyWsMapper companyWsMapper;


    public List<CompanyDto> findByExamplePaged(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size,
            @WebParam(name = "sort") String sort,
            @WebParam(name = "example") CompanyDto company){
        Page<Company> resultPage = companyService.findByExampleWithSpecificationPaged(
                companyWsMapper.dtoToCompany(company),
                PageRequest.of(page, size, Sort.by(sort))
        );
        return companyWsMapper.companiesToDtos(resultPage.getContent());
    }
}
