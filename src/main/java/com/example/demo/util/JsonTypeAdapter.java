package com.example.demo.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

public class JsonTypeAdapter extends TypeAdapter<BigDecimal> {

    @Override
    public void write(JsonWriter jsonWriter, BigDecimal s) throws IOException {

    }

    @Override
    public BigDecimal read(JsonReader reader) {
        String stringValue = "";
        try {
            stringValue = reader.nextString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(stringValue.contains(",")) {
            String value = stringValue.replace(",", ".");
            return new BigDecimal(value);
        } else if(stringValue.contains(".")) {
            return new BigDecimal(stringValue);
        }
        return null;
    }
}
