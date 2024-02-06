package com.example.reportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProductTx {
    private String exchangeCode;

    private String productGroupCode;

    private String symbol;

    private LocalDate expirationDate;

    private long quantityLong;

    private long quantityShort;
}
