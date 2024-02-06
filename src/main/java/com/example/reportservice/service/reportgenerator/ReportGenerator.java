package com.example.reportservice.service.reportgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ReportGenerator {
    void generateReport(InputStream inputStream, OutputStream outputStream) throws IOException;
}
