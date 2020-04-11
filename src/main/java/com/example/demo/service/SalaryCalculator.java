package com.example.demo.service;

import com.example.demo.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class SalaryCalculator {

    public Map<String, Optional<BigDecimal>> calculateSalariesForSpecifiedJob(List<Employee> employees) {
        return employees.stream()
                .collect(groupingBy(Employee::getJob,
                        Collectors.mapping(Employee::getSalary, Collectors.reducing((x, y) -> x.add(y)))));
    }
}