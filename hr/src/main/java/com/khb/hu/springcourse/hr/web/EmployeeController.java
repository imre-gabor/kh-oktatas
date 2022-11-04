package com.khb.hu.springcourse.hr.web;

import com.khb.hu.springcourse.hr.dto.EmployeeDto;
import com.khb.hu.springcourse.hr.mapper.EmployeeMapper;
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

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employee) {
        employee.setId(null);
        return employeeMapper.employeeToDto(
                employeeRepository.save(
                        employeeMapper.dtoToEmployee(employee)));
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return employeeMapper.employeeToDto(employee);
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
    public List<EmployeeDto> findAll() {
        return employeeMapper.employeesToDtos(employeeRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        employeeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto modify(@PathVariable int id, @RequestBody EmployeeDto employee){
        employee.setId(id);
        Employee modifiedEmployee = employeeService.modify(employeeMapper.dtoToEmployee(employee));
        if(modifiedEmployee != null) {
            return employeeMapper.employeeToDto(modifiedEmployee);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
