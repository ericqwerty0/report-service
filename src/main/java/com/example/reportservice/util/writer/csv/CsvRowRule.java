package com.example.reportservice.util.writer.csv;

import java.util.List;

public interface CsvRowRule<T> {
    String formatHeader();
    List<String> formatRow(List<T> data);
}
