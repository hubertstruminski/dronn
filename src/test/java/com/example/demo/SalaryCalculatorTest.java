package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.service.SalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryCalculatorTest {

    private SalaryCalculator salaryCalculator;

    @BeforeEach
    void setUp() {
        salaryCalculator = new SalaryCalculator();
    }

    @Test
    public void testIfCalculateSalariesForSpecifiedJobReturnCorrectComputedSalaries() {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee(1, "Hubert", "Strumiński", "Developer",
                new BigDecimal(12345.0)));
        employeesList.add(new Employee(2, "Jan", "Nowak", "Developer",
                new BigDecimal(7845.0)));
        employeesList.add(new Employee(3, "Ania", "Kowalska", "Waiter",
                new BigDecimal(3245.0)));

        Map<String, Optional<BigDecimal>> actual = salaryCalculator.calculateSalariesForSpecifiedJob(employeesList);

        BigDecimal actualDeveloperSalary = actual.get("Developer").get().setScale(2, RoundingMode.CEILING);
        BigDecimal actualWaiterSalary = actual.get("Waiter").get().setScale(2, RoundingMode.CEILING);

        assertEquals(new BigDecimal(20190.0).setScale(2, RoundingMode.CEILING), actualDeveloperSalary);
        assertEquals(new BigDecimal(3245.0).setScale(2, RoundingMode.CEILING), actualWaiterSalary);
    }
}
