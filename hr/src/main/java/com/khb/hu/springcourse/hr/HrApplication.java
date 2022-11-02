package com.khb.hu.springcourse.hr;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import com.khb.hu.springcourse.hr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		List<Employee> employees = Arrays.asList(
//				new Employee(null, "Kovács Géza", "developer",
//						900000, LocalDate.of(2015, 10, 1)),
//				new Employee(null, "Nagy Péter", "Dev",
//						900000, LocalDate.of(2015, 10, 1)),
//				new Employee(null, "Kis Gábor", "tester",
//						900000, LocalDate.of(2015, 10, 1))
//		);
//
//		employeeRepository.saveAll(employees);
//
//		salaryService.raiseSalaryByJob("de");
//
//		employeeRepository.findAll().forEach(employee ->
//			System.out.format("Employee with name %s has salary %f, works since %s%n",
//				employee.getName(), employee.getSalary(), employee.getWorkStart()
//			)
//		);
	}
}
