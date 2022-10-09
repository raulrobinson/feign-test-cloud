package com.rasysbox.shopping.client;

import com.rasysbox.shopping.model.Country;
import com.rasysbox.shopping.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryHystrixFallbackFactory implements CountryClient{
    @Override
    public ResponseEntity<Country> getCountry(long id) {
        Country country = Country.builder()
                .country("none")
                .state("none")
                .build();

        return ResponseEntity.ok(country);
    }
}
