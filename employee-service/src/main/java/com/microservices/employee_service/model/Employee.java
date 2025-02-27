package com.microservices.employee_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private Long departmentId;
    private String employeeName;
    private int employeeAge;
    private String employeeDesignation;
}
