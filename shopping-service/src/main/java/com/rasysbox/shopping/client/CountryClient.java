package com.rasysbox.shopping.client;

import com.rasysbox.shopping.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.rasysbox.shopping.config.ConfigurationConstants.COUNTRY_URL;

@FeignClient(name = "country", url = COUNTRY_URL, fallback = CountryHystrixFallbackFactory.class)
public interface CountryClient {
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") long id);
}
