package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.util.JsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CustomFileParser {

    private static final String[] HEADERS = {"id", "name", "surname", "job", "salary"};

    public List<Employee> parseJsonToEmployee(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapter(BigDecimal.class, new JsonTypeAdapter()).create();
        Type rootType = new TypeToken<Map<String, List<Employee>>>(){}.getType();
        Map<String, List<Employee>> employees = gson.fromJson(json, rootType);

        return employees.get("employees");
    }

    public List<Employee> parseCsvToEmployee(String fileName) {
        String path = getClass().getClassLoader().getResource(fileName).getPath();
        Reader in = null;
        Iterable<CSVRecord> csvRecords;
        List<Employee> employees = null;
        try {
            in = new FileReader(path);
            csvRecords = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .withDelimiter(';')
                    .parse(in);

            employees = StreamSupport.stream(csvRecords.spliterator(), false)
                    .map(record -> new Employee(
                            Long.parseLong(record.get("id").trim()),
                            escapeSigns(record.get("name")),
                            escapeSigns(record.get("surname")),
                            escapeSigns(record.get("job")),
                            new BigDecimal(escapeSigns(record.get("salary").replace(",", ".")))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public String escapeSigns(String word) {
        return word.replace("\"", "").trim();
    }
}
