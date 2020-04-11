package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.service.CustomFileParser;
import com.example.demo.service.CustomFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomFileReaderTest {

    private CustomFileReader fileReader;
    private CustomFileParser fileParser;

    @BeforeEach
    void setUp() {
        fileReader = new CustomFileReader();
        fileParser = new CustomFileParser();
    }

    @Test
    public void testIfMethodReadFileReturnsCorrectNumberOfLines() throws IOException {
        String json = fileReader.readFile("employees.json");
        List<Employee> actual = fileParser.parseJsonToEmployee(json);

        assertEquals(5, actual.size());
    }

    @Test
    public void testIfMethodReadFileThrowsNullPointerExceptionWhenPathIsNull() {
        assertThrows(NullPointerException.class, () -> fileReader.readFile(null));
    }
}
