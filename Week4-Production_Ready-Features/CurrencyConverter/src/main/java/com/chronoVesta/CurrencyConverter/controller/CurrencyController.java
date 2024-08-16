package com.chronoVesta.CurrencyConverter.controller;

import com.chronoVesta.CurrencyConverter.client.Impl.CurrencyConverterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {
//    https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_dWC5Ml9zsI79kAlZrgMhBFCJkE4PMzrCwCA6zQEN

    private final CurrencyConverterImpl currencyConverter;

    @GetMapping("/convertCurrency")
    public ResponseEntity<Double> getRate(@RequestParam String fromCurrency, @RequestParam String toCurrency,@RequestParam Double units) {
        return ResponseEntity.ok(currencyConverter.convertCurrency(fromCurrency, toCurrency, units));
    }

}
