package com.vestaChrono.prod_ready_features.Prod_ready_features.config;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeServiceRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Server error occurred");
                })
                .build();
    }

}
