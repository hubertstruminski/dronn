package com.example.demo.service;

import java.io.*;

public class CustomFileReader {

    public String readFile(String fileName) {
        String path = getClass().getClassLoader().getResource(fileName).getPath();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = fileReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
