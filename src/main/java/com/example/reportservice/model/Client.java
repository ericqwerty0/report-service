package com.example.reportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Client {
    private String clientType;

    private String clientNumber;

    private String accountNumber;

    private String subAccountNumber;

    private List<ProductTx> productTxList;

    public void addProductTx(List<ProductTx> list) {
        productTxList.addAll(list);
    }
}
