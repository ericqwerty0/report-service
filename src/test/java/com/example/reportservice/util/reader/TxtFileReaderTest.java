package com.example.reportservice.util.reader;

import org.junit.jupiter.api.Test;

import com.example.reportservice.util.reader.TxtFileReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TxtFileReaderTest {
    private final TxtFileReader txtFileReader = new TxtFileReader();
    @Test
    void givenInputStream_ReturnsListOfLines() throws IOException {
        String inputText = """
        1231
        14214
        124124
        124124daaed""";
        InputStream inputStream = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));

        List<String> lines = txtFileReader.readLines(inputStream);

        assertEquals(4, lines.size());
        assertEquals("1231", lines.get(0));
        assertEquals("14214", lines.get(1));
        assertEquals("124124", lines.get(2));
        assertEquals("124124daaed", lines.get(3));
    }
}