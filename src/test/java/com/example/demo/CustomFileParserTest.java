package com.example.demo;
import com.example.demo.model.Employee;
import com.example.demo.service.CustomFileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomFileParserTest {

    private CustomFileParser fileParser;

    @BeforeEach
    void setUp() {
        fileParser = new CustomFileParser();
    }

    @Test
    public void testIfMethodParseJsonToEmployeeReturnsCorrectNumberElements() {
        List<Employee> convertedJsonList = fileParser.parseJsonToEmployee("{\n" +
                "  \"employees\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Mark\",\n" +
                "      \"surname\": \"Green\",\n" +
                "      \"job\": \"Teacher\",\n" +
                "      \"salary\": \"3540.20\"\n" +
                "    }]}");
        int actual = convertedJsonList.size();

        assertEquals(1, actual);
    }

    @Test
    public void testIfMethodParseCsvToEmployeeThrowsNullPointerExceptionWhenPathIsNull() {
        assertThrows(NullPointerException.class, () -> fileParser.parseCsvToEmployee(null));
    }

    @Test
    public void testIfMethodEscapeSignsReturnCorrectString() {
        String actual = fileParser.escapeSigns("    \"Hubert");
        String expected = "Hubert";

        assertEquals(expected, actual);
    }

    @Test
    public void testIfMethodEscapeSignsThrowNullPointerExceptionWhenStringPassedIsNull() {
        assertThrows(NullPointerException.class, () -> fileParser.escapeSigns(null));

    }
}
