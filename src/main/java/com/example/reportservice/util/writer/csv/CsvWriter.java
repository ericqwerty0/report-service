package com.example.reportservice.util.writer.csv;

import com.example.reportservice.util.writer.OutputWriter;

import java.io.*;
import java.util.List;

public class CsvWriter<T> implements OutputWriter<T> {
    private final CsvRowRule<T> csvRowRule;

    public CsvWriter(CsvRowRule<T> dataRowRule) {
        this.csvRowRule = dataRowRule;
    }

    @Override
    public void write(OutputStream outputStream, List<T> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writeHeader(writer);
            writeContent(data, writer);
        }
    }

    private void writeHeader(BufferedWriter writer) throws IOException {
        writer.append(csvRowRule.formatHeader());
        writer.newLine();
    }

    private void writeContent(List<T> data, BufferedWriter writer) throws IOException {
        List<String> rows = csvRowRule.formatRow(data);
        int rowCount = rows.size();

        for (int i = 0; i < rowCount; i++) {
            String row = rows.get(i);
            writer.write(row);

            if (i < rowCount - 1)
                writer.newLine();
        }
    }
}