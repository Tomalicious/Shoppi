package com.example.shopr.domain;

public class ExceededStockAmountException extends Exception{

    public ExceededStockAmountException(String cause) {
        super(cause);
    }
}
