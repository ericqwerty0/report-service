package com.example.reportservice.util.writer.csv.dailysummary;

import com.example.reportservice.model.Client;
import com.example.reportservice.model.ProductTx;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DailySummaryReportRowRuleTest {
    private final DailySummaryReportRowRule dailySummaryReportRowRule = new DailySummaryReportRowRule();

    @Test
    void formatHeader_returnHeaderRow() {
        var expectedHeaderRow = "Client_Information,Product_Information,Total_Transaction_Amount";

        var headerRow = dailySummaryReportRowRule.formatHeader();

        assertEquals(expectedHeaderRow, headerRow);
    }

    @Test
    void formatRow_givenClientListReturnDataRows() {
        List<Client> clientList = getClientList();

        List<String> rows = dailySummaryReportRowRule.formatRow(clientList);

        assertEquals(3, rows.size());
        assertEquals("CL-4321-0002-0001,SGX-FU-NK-20230101,10", rows.get(0));
        assertEquals("CL-4321-0002-0003,SGX-FU-NK.-20230101,25", rows.get(1));
        assertEquals("CL-4321-0002-0003,SGX-FU-NS-20230201,-8", rows.get(2));

    }

    private static List<Client> getClientList() {
        Client clientA = new Client("CL", "4321", "0002", "0001",
            Arrays.asList(
                new ProductTx("SGX", "FU", "NK", LocalDate.parse("2023-01-01"), 0, 5),
                new ProductTx("SGX", "FU", "NK", LocalDate.parse("2023-01-01"), 15, 0)
            )
        );

        Client clientB = new Client("CL", "4321", "0002", "0003",
            Arrays.asList(
                new ProductTx("SGX", "FU", "NK.", LocalDate.parse("2023-01-01"), 10, 0),
                new ProductTx("SGX", "FU", "NK.", LocalDate.parse("2023-01-01"), 15, 0),
                new ProductTx("SGX", "FU", "NS", LocalDate.parse("2023-02-01"), 0, 8)
            )
        );

        return Arrays.asList(clientA, clientB);
    }
}