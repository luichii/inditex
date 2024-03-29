package com.inditex.core.model;

import java.time.LocalDateTime;

import static com.inditex.core.validation.CurrencyValidator.validateCurrency;
import static com.inditex.core.validation.DateValidator.validateDates;
import static com.inditex.core.validation.PriceValidator.validatePrice;

public class PriceInfo {
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Long productId;
    private Integer priority;
    private Float price;
    private String currency;

    public PriceInfo() {
    }

    public PriceInfo(Brand brand, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Long productId, Integer priority, Float price, String currency) {
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;

        validate();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
        validatePrice(price);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        validateCurrency(currency);
        this.currency = currency;
    }

    private void validate() {
        validateDates(startDate, endDate);
        validatePrice(price);
        validateCurrency(currency);
    }
}
