package com.example.reportservice.service.reportgenerator.dailysummary;

import com.example.reportservice.model.Client;
import com.example.reportservice.model.ProductTx;
import com.example.reportservice.util.parser.ParseStrategy;
import com.example.reportservice.util.reader.InputReader;
import com.example.reportservice.util.writer.OutputWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DailySummaryReportGeneratorTest {
    private DailySummaryReportGenerator reportGenerator;

    @Mock
    private InputReader inputReader;

    @Mock
    private ParseStrategy<Client> parseStrategy;

    @Mock
    private OutputWriter<Client> outputWriter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportGenerator = new DailySummaryReportGenerator(inputReader, parseStrategy, outputWriter);
    }

    @Test
    void givenInputStreamAndOutputStream_returnCorrectClientList() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("line1\nline2".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        var client = getSampleClient();

        ArgumentCaptor<List<Client>> clientListCaptor = ArgumentCaptor.forClass(List.class);

        when(inputReader.readLines(any())).thenReturn(List.of("line1", "line2"));
        when(parseStrategy.parse(anyString())).thenReturn(client);
        doNothing().when(outputWriter).write(any(OutputStream.class), clientListCaptor.capture());

        reportGenerator.generateReport(inputStream, outputStream);

        verify(parseStrategy, times(2)).parse(anyString());
        verify(outputWriter, times(1)).write(any(OutputStream.class), any(List.class));

        List<Client> capturedClientList = clientListCaptor.getValue();
        assertEquals(1, capturedClientList.size());
        assertEquals(2, capturedClientList.getFirst().getProductTxList().size());
    }

    private static Client getSampleClient() {
        var productTx = new ProductTx("SGX", "FU", "NK", LocalDate.parse("2023-01-01"), 0, 5);
        List<ProductTx> productTxList = new ArrayList<>();
        productTxList.add(productTx);

        return new Client("CL", "4321", "0002", "0001", productTxList);
    }
}