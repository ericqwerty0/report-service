package com.example.reportservice.service;

import java.io.OutputStream;

public interface ReportService {
    void getDailySummaryReportCsv(OutputStream outputStream);
}
