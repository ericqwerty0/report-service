package com.example.reportservice.util.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FutureParseStrategyTest {
    private final FutureParseStrategy futureParseStrategy = new FutureParseStrategy();

    private static final String BUY_INPUT_LINE = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
    private static final String SELL_INPUT_LINE = "315CL  432100030001FCC   FUCME N1    20100910JPY01S 0000000000 0000000003000000000000DUSD000000000015DUSD000000000000DJPY20100819059475      000308000093300000000             O";

    @Test
    void givenBuyInputLine_ReturnParsedClient() {
        String clientType = "CL";
        String clientNumber = "4321";
        String accountNumber = "0002";
        String subAccountNumber = "0001";
        String exchangeCode = "SGX";
        String productGroupCode = "FU";
        String symbol = "NK";
        LocalDate expirationDate = LocalDate.parse("2010-09-10");
        long quantityLong = 1;
        long quantityShort = 0;

        var client = futureParseStrategy.parse(BUY_INPUT_LINE);

        assertEquals(clientType, client.getClientType());
        assertEquals(clientNumber, client.getClientNumber());
        assertEquals(accountNumber, client.getAccountNumber());
        assertEquals(subAccountNumber, client.getSubAccountNumber());

        assertEquals(1, client.getProductTxList().size());
        var productTx = client.getProductTxList().getFirst();
        assertEquals(exchangeCode, productTx.getExchangeCode());
        assertEquals(productGroupCode, productTx.getProductGroupCode());
        assertEquals(symbol, productTx.getSymbol());
        assertEquals(0, productTx.getExpirationDate().compareTo(expirationDate));
        assertEquals(productTx.getQuantityLong(), quantityLong);
        assertEquals(productTx.getQuantityShort(), quantityShort);
    }

    @Test
    void givenSellInputLine_ReturnParsedClient() {
        String clientType = "CL";
        String clientNumber = "4321";
        String accountNumber = "0003";
        String subAccountNumber = "0001";
        String exchangeCode = "CME";
        String productGroupCode = "FU";
        String symbol = "N1";
        LocalDate expirationDate = LocalDate.parse("2010-09-10");
        long quantityLong = 0;
        long quantityShort = 3;

        var client = futureParseStrategy.parse(SELL_INPUT_LINE);

        assertEquals(clientType, client.getClientType());
        assertEquals(clientNumber, client.getClientNumber());
        assertEquals(accountNumber, client.getAccountNumber());
        assertEquals(subAccountNumber, client.getSubAccountNumber());

        assertEquals(1, client.getProductTxList().size());
        var productTx = client.getProductTxList().getFirst();
        assertEquals(exchangeCode, productTx.getExchangeCode());
        assertEquals(productGroupCode, productTx.getProductGroupCode());
        assertEquals(symbol, productTx.getSymbol());
        assertEquals(0, productTx.getExpirationDate().compareTo(expirationDate));
        assertEquals(productTx.getQuantityLong(), quantityLong);
        assertEquals(productTx.getQuantityShort(), quantityShort);
    }
}