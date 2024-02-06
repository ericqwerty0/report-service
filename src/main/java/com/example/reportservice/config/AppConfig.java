package com.example.reportservice.config;

import com.example.reportservice.model.Client;
import com.example.reportservice.util.parser.FutureParseStrategy;
import com.example.reportservice.util.parser.ParseStrategy;
import com.example.reportservice.util.reader.InputReader;
import com.example.reportservice.util.reader.TxtFileReader;
import com.example.reportservice.util.writer.csv.CsvWriter;
import com.example.reportservice.util.writer.csv.dailysummary.DailySummaryReportRowRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public InputReader getTxtFileReader() {
        return new TxtFileReader();
    }

    @Bean
    public ParseStrategy<Client> getFutureParseStrategy() {
        return new FutureParseStrategy();
    }

    @Bean
    public CsvWriter<Client> getDailySummaryReportCsvWriter() {
        return new CsvWriter<>(new DailySummaryReportRowRule());
    }
}
