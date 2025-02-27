package com.microservices.department_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>(); // Initialize directly


}
