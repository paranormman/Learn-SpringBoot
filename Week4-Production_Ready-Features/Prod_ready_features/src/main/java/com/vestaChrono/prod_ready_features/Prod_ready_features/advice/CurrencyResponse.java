package com.vestaChrono.prod_ready_features.Prod_ready_features.advice;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class CurrencyResponse {
    private Map<String, Object> rates;
}
