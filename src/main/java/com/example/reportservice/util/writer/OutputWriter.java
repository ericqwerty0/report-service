package com.example.reportservice.util.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface OutputWriter<T> {
    void write(OutputStream outputStream, List<T> data) throws IOException;
}
