package com.example.reportservice.service;

import com.example.reportservice.service.datasource.DataSourceReader;
import com.example.reportservice.service.reportgenerator.ReportGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {
    ReportServiceImpl.class
})
class ReportServiceImplTest {

    @Autowired
    private ReportService reportService;

    @MockBean
    private DataSourceReader dataSourceReader;

    @MockBean
    private ReportGenerator reportGenerator;

    @Test
    void getDailySummaryReportCsv_callsDataSourceReaderAndReportGenerator() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        OutputStream outputStream = mock(OutputStream.class);

        when(dataSourceReader.readData()).thenReturn(inputStream);

        reportService.getDailySummaryReportCsv(outputStream);

        verify(dataSourceReader, times(1)).readData();
        verify(reportGenerator, times(1)).generateReport(inputStream, outputStream);
    }


    @Test
    void getDailySummaryReportCsv_throwRuntimeExceptionIfCatchIOException() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);

        when(dataSourceReader.readData()).thenThrow(new IOException());

        assertThatThrownBy(() -> reportService.getDailySummaryReportCsv(outputStream))
            .isInstanceOf(RuntimeException.class);
    }
}