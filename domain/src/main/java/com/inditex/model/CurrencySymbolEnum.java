package com.inditex.model;

public enum CurrencySymbolEnum {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    INR("INR"),
    RUB("RUB"),
    THB("THB"),
    TRY("TRY"),
    UAH("UAH"),
    CRC("CRC"),
    MNT("MNT"),
    KRW("KRW"),
    NGN("NGN"),
    ILS("ILS"),
    SAR("SAR"),
    PYG("PYG"),
    LAK("LAK");

    private final String symbol;

    CurrencySymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    public static boolean isValidCurrency(String currency) {
        for (CurrencySymbolEnum symbolEnum : CurrencySymbolEnum.values()) {
            if (symbolEnum.getSymbol().equals(currency)) {
                return true;
            }
        }
        return false;
    }

    public String getSymbol() {
        return symbol;
    }
}
