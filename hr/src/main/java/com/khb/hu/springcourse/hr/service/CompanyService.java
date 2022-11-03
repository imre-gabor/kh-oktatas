package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.khb.hu.springcourse.hr.service.CompanySpecifications.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager em;

    public List<Company> findByExample(Company company) {
        return companyRepository.findAll(
                Example.of(company,
                        ExampleMatcher.matching()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
    }

    public List<Company> findByExampleWithSpecification(Company company) {
        Integer id = company.getId();
        String name = company.getName();

        Specification<Company> spec = Specification.where(null);
        if(id != null){
            spec = spec.and(idEquals(id));
        }

        if(StringUtils.hasLength(name)){
            spec = spec.and(nameContains(name));
        }

        if(!CollectionUtils.isEmpty(company.getEmployees())) {
            Employee employee = company.getEmployees().get(0);
            String employeeName = employee.getName();
            LocalDate employeeWorkStart = employee.getWorkStart();
            if(StringUtils.hasLength(employeeName)){
                spec = spec.and(hasEmployeeWithNamePrefix(employeeName));
            }
            if(employeeWorkStart != null){
                spec = spec.and(hasEmployeeStartedWorkingAtMonthOf(employeeWorkStart));
            }
        }

        return companyRepository.findAll(spec);
    }

    public List<Company> findByExampleWithSpecificationAndEntityGraph(Company company, String entityGraphName) {
        Integer id = company.getId();
        String name = company.getName();

        Specification<Company> spec = Specification.where(null);
        if(id != null){
            spec = spec.and(idEquals(id));
        }

        if(StringUtils.hasLength(name)){
            spec = spec.and(nameContains(name));
        }

        if(!CollectionUtils.isEmpty(company.getEmployees())) {
            Employee employee = company.getEmployees().get(0);
            String employeeName = employee.getName();
            LocalDate employeeWorkStart = employee.getWorkStart();
            if(StringUtils.hasLength(employeeName)){
                spec = spec.and(hasEmployeeWithNamePrefix(employeeName));
            }
            if(employeeWorkStart != null){
                spec = spec.and(hasEmployeeStartedWorkingAtMonthOf(employeeWorkStart));
            }
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Company> cq = cb.createQuery(Company.class);
        Root<Company> root = cq.from(Company.class);

        cq.where(spec.toPredicate(root, cq, cb));

        TypedQuery<Company> query = em.createQuery(cq);

        EntityGraph<?> entityGraph = em.getEntityGraph(entityGraphName);
        query.setHint(org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD.getKey(), entityGraph);

        return query.getResultList();

    }


}
