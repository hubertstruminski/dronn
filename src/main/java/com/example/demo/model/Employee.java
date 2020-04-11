package com.example.demo.model;

import java.math.BigDecimal;

public class Employee {

    private long id;
    private String name;
    private String surname;
    private String job;
    private BigDecimal salary;

    public Employee(long id, String name, String surname, String job, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
