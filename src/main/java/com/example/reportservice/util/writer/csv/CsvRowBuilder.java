package com.example.reportservice.util.writer.csv;

public class CsvRowBuilder {
    private CsvRowBuilder() {}

    public static String buildRow(String... args) {
        return String.join(",", args);
    }
}
