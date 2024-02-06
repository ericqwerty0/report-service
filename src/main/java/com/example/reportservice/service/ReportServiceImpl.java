package com.example.reportservice.service;

import com.example.reportservice.service.datasource.DataSourceReader;
import com.example.reportservice.service.reportgenerator.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ReportServiceImpl implements ReportService {
    private final DataSourceReader dataSourceReader;

    private final ReportGenerator reportGenerator;

    @Autowired
    public ReportServiceImpl(DataSourceReader dataSourceReader, ReportGenerator reportGenerator) {
        this.dataSourceReader = dataSourceReader;
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void getDailySummaryReportCsv(OutputStream outputStream) {
        try {
            var dataSource = dataSourceReader.readData();
            reportGenerator.generateReport(dataSource, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
