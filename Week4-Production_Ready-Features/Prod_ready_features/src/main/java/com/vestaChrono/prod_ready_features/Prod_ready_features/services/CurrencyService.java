package com.vestaChrono.prod_ready_features.Prod_ready_features.services;

import com.vestaChrono.prod_ready_features.Prod_ready_features.clients.implementation.EmployeeClientImpl;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Value("${exchangeRatesService.API_KEY}")
    private String API_KEY;

    Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private final RestClient currencyRestClient;
    private final ModelMapper mapper;

    public Double convertCurrency(String fromCurrency,String toCurrency,Double units) {
        try{
            ResponseEntity<String> response = currencyRestClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("apiKey", API_KEY)
                            .queryParam("base_currency", fromCurrency)
                            .queryParam("currencies", toCurrency).build())
                    .retrieve()
                    .toEntity(String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject data = jsonObject.getJSONObject("data");
            log.info("JSON returned from the server {}", String.valueOf(jsonObject));
            log.info("data in JSON {}",String.valueOf(data));
            Map<String, Object> rates = data.toMap();
            String[] currencies =toCurrency.split(",");
            return Double.parseDouble(rates.get(currencies[0]).toString())*units;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
