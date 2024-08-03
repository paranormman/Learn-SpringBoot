package com.vestaChrono.prod_ready_features.Prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //Get security context
        //Get Authentication
        //Get the principle
        //Get the username
        return Optional.of("Manoj Shivaprakash");
    }
}
