package com.khb.hu.springcourse.hr.web;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import com.khb.hu.springcourse.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> findById(@PathVariable int id) {
//        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
//        if(optionalEmployee.isPresent()){
//            return ResponseEntity.ok(optionalEmployee.get());
//        } else {
////            return ResponseEntity
////                    .status(HttpStatus.NOT_FOUND)
//                    //.body(sdsdf)
//                    //.headers(headers -> headers.add("", ""))
////                    .build();
//
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        employeeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Employee modify(@PathVariable int id, @RequestBody Employee employee){
        employee.setId(id);
        Employee modifiedEmployee = employeeService.modify(employee);
        if(modifiedEmployee != null) {
            return modifiedEmployee;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
