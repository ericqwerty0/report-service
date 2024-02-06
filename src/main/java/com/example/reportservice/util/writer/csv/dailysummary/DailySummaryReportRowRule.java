package com.example.reportservice.util.writer.csv.dailysummary;

import com.example.reportservice.model.Client;
import com.example.reportservice.model.ProductTx;
import com.example.reportservice.util.writer.csv.CsvRowBuilder;
import com.example.reportservice.util.writer.csv.CsvRowRule;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class DailySummaryReportRowRule implements CsvRowRule<Client> {
    private static final String[] HEADER = new String[] {"Client_Information", "Product_Information", "Total_Transaction_Amount" };
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String formatHeader() {
        return CsvRowBuilder.buildRow(HEADER);
    }

    @Override
    public List<String> formatRow(List<Client> clientList) {
        List<String> rows = new ArrayList<>();
        for (var client: clientList) {
            var clientInformation = buildClientInformationColumn(client);
            var productInformationAndTxAmountColumns = buildProductInformationAndTxAmountColumn(client.getProductTxList());

            for (var columns: productInformationAndTxAmountColumns)
                rows.add(CsvRowBuilder.buildRow(clientInformation, columns));
        }

        return rows;
    }

    private String buildClientInformationColumn(Client client) {
        return String.join("-",
            client.getClientType(),
            client.getClientNumber(),
            client.getAccountNumber(),
            client.getSubAccountNumber()
        );
    }

    private List<String> buildProductInformationAndTxAmountColumn(List<ProductTx> productTxList) {
        Map<String, Long> productInfoTotalTxAmountMap = new LinkedHashMap<>();
        List<String> productInformationAndTxAmount = new ArrayList<>();

        for(var p: productTxList) {
            var key = buildProductInformationColumn(p);
            var totalTxAmount = p.getQuantityLong() - p.getQuantityShort();
            productInfoTotalTxAmountMap.merge(key, totalTxAmount, Long::sum);
        }

        for (Map.Entry<String, Long> entry : productInfoTotalTxAmountMap.entrySet()) {
            var productInformation = entry.getKey();
            var totalTxAmount = entry.getValue();
            productInformationAndTxAmount.add(CsvRowBuilder.buildRow(productInformation, totalTxAmount.toString()));
        }

        return productInformationAndTxAmount;
    }

    private String buildProductInformationColumn(ProductTx productTx) {
        return String.join("-",
            productTx.getExchangeCode(),
            productTx.getProductGroupCode(),
            productTx.getSymbol(),
            productTx.getExpirationDate().format(formatter)
        );
    }
}
