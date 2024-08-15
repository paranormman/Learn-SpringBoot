package com.chronoVesta.CurrencyConverter.client.Impl;

import com.chronoVesta.CurrencyConverter.client.CurrencyConverter;
import com.chronoVesta.CurrencyConverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CurrencyConverterImpl implements CurrencyConverter {

    private final RestClient restClient;

    @Override
    public Double convertCurrency(String fromCurrency, String toCurrency, Double units) {
        CurrencyDto currencyData = restClient.get()
                                        .uri("v1/latest")
                                        .retrieve()
                                        .body(new ParameterizedTypeReference<>() {
                                        });
        return currencyData.getData().get(toCurrency)*units;
    }
}
