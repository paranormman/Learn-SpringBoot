package com.chronoVesta.CurrencyConverter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class AppConfig {

//    https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_dWC5Ml9zsI79kAlZrgMhBFCJkE4PMzrCwCA6zQEN&currencies=USD&base_currency=INR
//    http://localhost:8080/convertCurrency?fromCurrency=INR&toCurrency=USD&units=500

//    fca_live_dWC5Ml9zsI79kAlZrgMhBFCJkE4PMzrCwCA6zQEN

    @Value("${currency.app.base.url}")
    private String BASE_URL;

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();

    }

}
