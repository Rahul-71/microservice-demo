package com.microservices.department_service.controller;

import com.microservices.department_service.client.EmployeeClient;
import com.microservices.department_service.model.Department;
import com.microservices.department_service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        log.info("Adding new department: {}", department);
        Department save = repository.save(department);
        log.info("Department saved with id: {}", save.getDepartmentId());
        return ResponseEntity.created(null).body(save);
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAllDepartments() {
        log.info("Finding all departments");
        List<Department> departments = repository.findAll();
        log.info("Found {} departments", departments.size());
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable Long departmentId) {
        log.info("Finding department by id: {}", departmentId);
        Department department = repository.findById(departmentId).orElse(null);
        if (department == null) {
            log.info("Department not found with id: {}", departmentId);
        } else {
            log.info("Department found with id: {}", departmentId);
        }
        return ResponseEntity.ok(department);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        log.info("Department find");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployeeList(employeeClient.findByDepartment(department.getDepartmentId()))
        );
        return departments;
    }

}
