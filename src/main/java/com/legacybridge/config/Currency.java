package com.legacybridge.config;

public enum Currency {
    EURO("EUR", "€"),
    DOLLAR("USD", "$"),
    PESO("COL", "COL $");

    private final String currency;
    private final String symbol;

    Currency(String currency, String symbol) {
        this.currency = currency;
        this.symbol = symbol;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getSymbol() {
        return this.symbol;
    }

}
