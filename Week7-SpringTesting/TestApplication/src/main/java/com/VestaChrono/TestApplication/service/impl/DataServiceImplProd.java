package com.VestaChrono.TestApplication.service.impl;

import com.VestaChrono.TestApplication.service.DataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DataServiceImplProd implements DataService {
    @Override
    public String getData() {
        return "Production Data";
    }
}
