package com.khb.hu.springcourse.wsclient;

import com.khb.hu.springcourse.hr.wsclient.CompanyDto;
import com.khb.hu.springcourse.hr.wsclient.CompanyWebServiceService;

public class Main {

    public static void main(String[] args) {
        CompanyDto example = new CompanyDto();
        example.setName("Cég");
        new CompanyWebServiceService().getCompanyWebServicePort().findByExamplePaged(0, 10, "id",
                example).forEach(c -> System.out.format("id: %d, name: %s%n", c.getId(), c.getName()));
    }
}
