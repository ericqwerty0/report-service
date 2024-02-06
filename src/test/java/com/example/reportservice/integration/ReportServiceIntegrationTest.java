package com.example.reportservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReportServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDailySummaryReportCsv_returnExpectedCsvFile() throws Exception {
        var url = "/dailySummaryReportCsv";
        var expectedContentDisposition = "attachment; filename=Output.csv";
        var expectedFileContent = """
            Client_Information,Product_Information,Total_Transaction_Amount
            CL-4321-0002-0001,SGX-FU-NK-20100910,3""";

        var resultActions = mockMvc
            .perform(
                MockMvcRequestBuilders.get(url)
            );

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, expectedContentDisposition));
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));
        resultActions.andExpect(content().string(expectedFileContent));
    }
}
