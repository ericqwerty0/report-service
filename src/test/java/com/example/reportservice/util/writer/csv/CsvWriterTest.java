package com.example.reportservice.util.writer.csv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvWriterTest {

    @Test
    void givenTestData_returnOutputStreamWithCsvContent() throws IOException {
        List<TestData> testDataList = new ArrayList<>();
        testDataList.add(new TestData("WADKJ", "NA", 1));
        testDataList.add(new TestData("ADWAK", "AA", 100));

        CsvRowRule<TestData> csvRowRule = new TestDataCsvRowRule();
        CsvWriter<TestData> csvWriter = new CsvWriter<>(csvRowRule);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        csvWriter.write(outputStream, testDataList);

        String expectedCsvContent = """
            ClientNumber,ProductCode,TxAmount
            WADKJ,NA,1
            ADWAK,AA,100""";
        String actualCsvContent = outputStream.toString();

        assertEquals(expectedCsvContent, actualCsvContent);
    }

    @Getter
    @AllArgsConstructor
    static class TestData {
        private final String clientNumber;
        private final String productCode;
        private final long txAmount;
    }

    static class TestDataCsvRowRule implements CsvRowRule<TestData> {
        @Override
        public String formatHeader() {
            return "ClientNumber,ProductCode,TxAmount";
        }

        @Override
        public List<String> formatRow(List<TestData> data) {
            List<String> rows = new ArrayList<>();
            for (TestData testData : data) {
                String row = String.join(",",
                    testData.getClientNumber(),
                    testData.getProductCode(),
                    Long.valueOf(testData.getTxAmount()).toString()
                );
                rows.add(row);
            }
            return rows;
        }
    }
}