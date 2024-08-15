package com.chronoVesta.CurrencyConverter.client;

import com.chronoVesta.CurrencyConverter.dto.CurrencyDto;

import java.util.List;

public interface CurrencyConverter {

    public Double convertCurrency(String fromCurrency, String toCurrency, Double units);

}
