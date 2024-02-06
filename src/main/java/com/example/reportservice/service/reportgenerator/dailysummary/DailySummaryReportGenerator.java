package com.example.reportservice.service.reportgenerator.dailysummary;

import com.example.reportservice.model.Client;
import com.example.reportservice.service.reportgenerator.ReportGenerator;
import com.example.reportservice.util.reader.InputReader;
import com.example.reportservice.util.parser.ParseStrategy;
import com.example.reportservice.util.writer.OutputWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Component
public class DailySummaryReportGenerator implements ReportGenerator {
    private final InputReader inputReader;

    private final ParseStrategy<Client> parserStrategy;

    private final OutputWriter<Client> outputWriter;

    @Autowired
    public DailySummaryReportGenerator(InputReader inputReader, ParseStrategy<Client> parserStrategy, OutputWriter<Client> outputWriter) {
        this.inputReader = inputReader;
        this.parserStrategy = parserStrategy;
        this.outputWriter = outputWriter;
    }

    @Override
    public void generateReport(InputStream inputStream, OutputStream outputStream) throws IOException {
        List<String> lines = inputReader.readLines(inputStream);

        List<Client> clientList = parseLines(lines);

        outputWriter.write(outputStream, clientList);
    }

    private List<Client> parseLines(List<String> lines) {
        Map<String, Client> clientMap = new LinkedHashMap<>();
        for (String line : lines) {
            Client client = parserStrategy.parse(line);
            var clientKey = getClientKey(client);

            clientMap.merge(clientKey, client, (exist, c) -> {
                exist.addProductTx(c.getProductTxList());
                return exist;
            });
        }

        return new ArrayList<>(clientMap.values());
    }

    private static String getClientKey(Client client) {
        return String.join("-",
            client.getClientType(),
            client.getClientNumber(),
            client.getAccountNumber(),
            client.getSubAccountNumber()
        );
    }
}
