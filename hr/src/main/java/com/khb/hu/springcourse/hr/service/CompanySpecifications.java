package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Company_;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecifications {

    public static Specification<Company> idEquals(Integer id){
        return (root, cq, cb) -> cb.equal(root.get(Company_.id), id);
    }

    public static Specification<Company> nameContains(String name){
        return (root, cq, cb) -> cb.like(root.get(Company_.name), "%" + name + "%");
    }
}
