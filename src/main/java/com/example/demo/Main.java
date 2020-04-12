package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.service.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    private CustomFileReader fileReader;
    private CustomFileParser fileParser;
    private SalaryCalculator salaryCalculator;

    public Main() {
        fileReader = new CustomFileReader();
        fileParser = new CustomFileParser();
        salaryCalculator = new SalaryCalculator();
    }

    public static void main(String[] args) {
        Main main = new Main();

        try {
            showCsvSalaryDetails(main);
            System.out.println("\n");
            showJsonSalaryDetails(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showJsonSalaryDetails(Main main) throws IOException {
        String json = main.fileReader.readFile("employees.json");

        List<Employee> employees = main.fileParser.parseJsonToEmployee(json);
        Map<String, Optional<BigDecimal>> jsonSalariesMap = main.salaryCalculator.calculateSalariesForSpecifiedJob(employees);

        System.out.println("Job salaries - JSON file");
        jsonSalariesMap.entrySet()
                .stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().get() + " zł"));
    }

    private static void showCsvSalaryDetails(Main main) throws IOException {
        List<Employee> employeeList = main.fileParser.parseCsvToEmployee("employees.csv");
        Map<String, Optional<BigDecimal>> csvSalariesMap = main.salaryCalculator.calculateSalariesForSpecifiedJob(employeeList);

        System.out.println("Job salaries - CSV file");
        csvSalariesMap.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().get() + " zł"));
    }
}
