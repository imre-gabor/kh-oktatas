package com.khb.hu.springcourse.hr.web;

import com.khb.hu.springcourse.hr.dto.CompanyDto;
import com.khb.hu.springcourse.hr.dto.EmployeeDto;
import com.khb.hu.springcourse.hr.mapper.CompanyMapper;
import com.khb.hu.springcourse.hr.mapper.EmployeeMapper;
import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import com.khb.hu.springcourse.hr.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public CompanyDto create(@RequestBody CompanyDto company) {
        company.setId(null);
        return companyMapper.companyToDto(
                companyRepository.save(
                        companyMapper.dtoToCompany(company)));
    }

    @GetMapping("/{id}")
    public CompanyDto findById(@PathVariable int id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return companyMapper.companyToDto(company);
    }

    @GetMapping
    public List<CompanyDto> findAll() {
        return companyMapper.companiesToDtos(companyRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        companyRepository.deleteById(id);
    }

    @PostMapping("/{id}/employees")
    public CompanyDto addEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto){
        return companyMapper.companyToDto(companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto)));
    }

}
