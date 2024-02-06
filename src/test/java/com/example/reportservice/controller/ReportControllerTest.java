package com.example.reportservice.controller;

import com.example.reportservice.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    void getDailySummaryReportCsv_return200() throws Exception {
        var url = "/dailySummaryReportCsv";

        mockMvc
            .perform(
                MockMvcRequestBuilders.get(url)
            )
            .andExpect(status().isOk());
    }

    @Test
    void getDailySummaryReportCsv_returnCsvFile() throws Exception {
        var url = "/dailySummaryReportCsv";

        var resultActions = mockMvc
            .perform(
                MockMvcRequestBuilders.get(url)
            );

        resultActions.andExpect(header().string(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=Output.csv"));
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));
    }
}
