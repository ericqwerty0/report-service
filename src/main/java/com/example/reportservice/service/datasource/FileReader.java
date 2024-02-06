package com.example.reportservice.service.datasource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileReader implements DataSourceReader {
    private static final String FILE_NAME = "Input.txt";

    @Override
    public InputStream readData() throws IOException {
        return new ClassPathResource(FILE_NAME).getInputStream();
    }
}