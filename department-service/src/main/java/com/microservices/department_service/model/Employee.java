package com.microservices.department_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)  // This column will be the foreign key
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Department department;  // This is the actual entity reference

    private String employeeName;
    private int employeeAge;
    private String employeeDesignation;
}
