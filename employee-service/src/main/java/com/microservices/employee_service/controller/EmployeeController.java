package com.microservices.employee_service.controller;

import com.microservices.employee_service.model.Employee;
import com.microservices.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return ResponseEntity.created(null).body(repository.save(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        LOGGER.info("Employee find");
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return ResponseEntity.ok(repository.findByDepartmentId(departmentId));
    }

}
