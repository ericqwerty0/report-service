package com.example.reportservice.util.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader implements InputReader {

    @Override
    public List<String> readLines(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<>();

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                lines.add(line);
            }
        }

        return lines;
    }
}