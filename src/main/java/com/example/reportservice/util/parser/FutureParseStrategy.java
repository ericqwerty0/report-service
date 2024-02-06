package com.example.reportservice.util.parser;

import com.example.reportservice.model.Client;
import com.example.reportservice.model.ProductTx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FutureParseStrategy implements ParseStrategy<Client> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    @Override
    public Client parse(String line) {
        return getClient(line);
    }

    private static Client getClient(String line) {
        String clientType = line.substring(3, 7).trim();

        String clientNumber = line.substring(7, 11).trim();

        String accountNumber = line.substring(11, 15).trim();

        String subAccountNumber = line.substring(15, 19).trim();

        List<ProductTx> productTxList = new ArrayList<>();
        productTxList.add(getProductTx(line));

        return new Client(clientType, clientNumber, accountNumber, subAccountNumber, productTxList);
    }

    private static ProductTx getProductTx(String line) {
        String exchangeCode = line.substring(27, 31).trim();

        String productGroupCode = line.substring(25, 27).trim();

        String symbol = line.substring(31, 37).trim();

        LocalDate expirationDate = LocalDate.parse(line.substring(37, 45).trim(), formatter);

        long quantityLong = Long.parseLong(line.substring(52, 62).trim());

        long quantityShort = Long.parseLong(line.substring(63, 73).trim());

        return new ProductTx(
            exchangeCode,
            productGroupCode,
            symbol,
            expirationDate,
            quantityLong,
            quantityShort
        );
    }
}
