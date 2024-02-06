package com.example.reportservice.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void givenProductTxList_AddsToList() {
        var productTxA = getSampleProductTx();
        List<ProductTx> productTxList = new ArrayList<>();
        productTxList.add(productTxA);

        var client = new Client(
            "CL",
            "4321",
            "0002",
            "0001",
            productTxList);

        var productTxB = getSampleProductTx();
        var productTxC = getSampleProductTx();
        var productTxD = getSampleProductTx();
        List<ProductTx> newProductTxList = Arrays.asList(productTxB, productTxC, productTxD);

        client.addProductTx(newProductTxList);

        List<ProductTx> actualList = client.getProductTxList();
        assertEquals(4, actualList.size());
        assertEquals(productTxA, actualList.getFirst());
        assertEquals(productTxB, actualList.get(1));
        assertEquals(productTxC, actualList.get(2));
        assertEquals(productTxD, actualList.get(3));
    }

    private static ProductTx getSampleProductTx() {
        String exchangeCode = "SGX";
        String productGroupCode = "FU";
        String symbol = "NK";
        LocalDate expirationDate = LocalDate.parse("2010-09-10");
        long quantityLong = 1;
        long quantityShort = 0;

        return new ProductTx(exchangeCode, productGroupCode, symbol, expirationDate, quantityLong, quantityShort);
    }
}