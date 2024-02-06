package com.example.reportservice.service.datasource;

import java.io.IOException;
import java.io.InputStream;

public interface DataSourceReader {
    InputStream readData() throws IOException;
}