package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class CompanyServiceIT {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void init(){
        employeeRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }


    @Test
    void findByExample_ById() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExample(new Company(companies.get(1).getId()));

        assertThat(found).containsExactly(companies.get(1));
    }

    @Test
    void findByExample_ByName() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExample(new Company("ny"));

        assertThat(found).containsExactly(companies.get(0), companies.get(1));
    }

    @Test
    void findByExample_ByIdAndName() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExample(new Company(companies.get(1).getId(),"ny"));

        assertThat(found).containsExactly(companies.get(1));
    }


    @Test
    void findByExampleWithSpecification_ById() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExampleWithSpecification(new Company(companies.get(1).getId()));

        assertThat(found).containsExactly(companies.get(1));
    }

    private List<Company> saveDefaultTestCompanies() {
        List<Company> companies = companyRepository.saveAll(Arrays.asList(
                new Company("company1"),
                new Company("company2"),
                new Company("comp3")));
        return companies;
    }

    @Test
    void findByExampleWithSpecification_ByName() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExampleWithSpecification(new Company("ny"));

        assertThat(found).containsExactly(companies.get(0), companies.get(1));
    }

    @Test
    void findByExampleWithSpecification_ByIdAndName() {
        List<Company> companies = saveDefaultTestCompanies();

        List<Company> found = companyService.findByExampleWithSpecification(new Company(companies.get(1).getId(),"ny"));

        assertThat(found).containsExactly(companies.get(1));
    }

    @Test
    void findByExampleWithSpecification_ByNameAndEmployeeName() {
        List<Company> companies = saveDefaultTestCompanies();

        saveNewEmployeeForCompany("John Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(0));

        saveNewEmployeeForCompany("Bruce Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(1));

        Company example = new Company("ny");
        example.setEmployees(Arrays.asList(new Employee(null, "John", null, 0.0, null)));
        List<Company> found = companyService.findByExampleWithSpecification(example);

        assertThat(found).containsExactly(companies.get(0));
    }

    @Test
    void findByExampleWithSpecification_ByNameAndEmployeeWorkStart() {
        List<Company> companies = saveDefaultTestCompanies();

        saveNewEmployeeForCompany("John Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(0));

        saveNewEmployeeForCompany("Bruce Wayne",
                LocalDate.of(2021, 10, 31),
                companies.get(1));

        Company example = new Company("ny");
        example.setEmployees(Arrays.asList(new Employee(null, "", null, 0.0, LocalDate.of(2021, 10, 20))));
        List<Company> found = companyService.findByExampleWithSpecification(example);

        assertThat(found).containsExactly(companies.get(0), companies.get(1));
    }

    @Test
    void findCompanyById_WithLazyFetchedEmployees(){ //cannot pass if EAGER fetch is defined at Company.employees
        List<Company> companies = saveDefaultTestCompanies();

        Company company = companyRepository.findById(companies.get(0).getId()).get();
        Assertions.assertThrows(LazyInitializationException.class, () -> {
            company.getEmployees().iterator();
        });
    }

    @Test
    void findCompanyById_WithEagerFetchedEmployees(){
        List<Company> companies = saveDefaultTestCompanies();
        Employee employee = saveNewEmployeeForCompany("John Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(0));

        Company company = companyRepository.findByIdWithEmployees(companies.get(0).getId()).get();

        assertThat(company.getEmployees().get(0).getId()).isEqualTo(employee.getId());
    }

    @Test
    void findByExampleWithSpecification_ByNameAndEmployeeName_LazyFetch() {
        List<Company> companies = saveDefaultTestCompanies();

        saveNewEmployeeForCompany("John Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(0));

        saveNewEmployeeForCompany("Bruce Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(1));

        Company example = new Company("ny");
        example.setEmployees(Arrays.asList(new Employee(null, "John", null, 0.0, null)));
        List<Company> found = companyService.findByExampleWithSpecification(example);

        assertThat(found).containsExactly(companies.get(0));
        Assertions.assertThrows(LazyInitializationException.class,
                () -> found.get(0).getEmployees().iterator());
    }

    @Test
    void findByExampleWithSpecification_ByNameAndEmployeeName_WithFetchingEmployees() {
        List<Company> companies = saveDefaultTestCompanies();

        Employee employee = saveNewEmployeeForCompany("John Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(0));

        saveNewEmployeeForCompany("Bruce Wayne",
                LocalDate.of(2021, 10, 1),
                companies.get(1));

        Company example = new Company("ny");
        example.setEmployees(Arrays.asList(new Employee(null, "John", null, 0.0, null)));
        List<Company> found = companyService.findByExampleWithSpecificationAndEntityGraph(example, "Company.allRelationships");

        assertThat(found.get(0).getEmployees().get(0).getId())
                .isEqualTo(employee.getId());
    }

    private Employee saveNewEmployeeForCompany(String employeeName, LocalDate workStart, Company company) {
        Employee employee = employeeRepository.save(
                new Employee(null, employeeName, "actor", 100000,
                        workStart));
        company.addEmployee(employee);
        return employeeRepository.save(employee);
    }
}