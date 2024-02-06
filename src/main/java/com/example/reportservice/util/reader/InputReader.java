package com.example.reportservice.util.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface InputReader {
    List<String> readLines(InputStream inputStream) throws IOException;
}
