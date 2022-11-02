package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findByExample(Company company) {
        return companyRepository.findAll(
                Example.of(company,
                        ExampleMatcher.matching()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
    }
}
