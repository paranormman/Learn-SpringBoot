package com.chronoVesta.CurrencyConverter.client.Impl;

import com.chronoVesta.CurrencyConverter.client.CurrencyConverter;
import com.chronoVesta.CurrencyConverter.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
public class CurrencyConverterImpl implements CurrencyConverter {

    private final RestClient restClient;
    Logger logger = LoggerFactory.getLogger(CurrencyConverterImpl.class);

    @Override
    public Double convertCurrency(String fromCurrency, String toCurrency, Double units) {
        CurrencyDto currencyData = restClient.get()
                                        .uri(uriBuilder -> uriBuilder
                                                .path("v1/latest")
                                                .queryParam("currencies", toCurrency)
                                                .queryParam("base_currency", fromCurrency)
                                                .build())
                                        .header("apikey", "fca_live_dWC5Ml9zsI79kAlZrgMhBFCJkE4PMzrCwCA6zQEN")
                                        .retrieve()
                                        .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                                            logger.error("Error - {} {} {}", response.getBody(), request.getURI(), request.getHeaders());
                                            throw new HttpClientErrorException(response.getStatusCode(), response.getBody().toString());
                                        }))
                                        .body(new ParameterizedTypeReference<>() {
                                        });
        return currencyData.getData().get(toCurrency)*units;
    }
}
