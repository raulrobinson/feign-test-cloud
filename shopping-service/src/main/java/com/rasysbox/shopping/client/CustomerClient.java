package com.rasysbox.shopping.client;

import com.rasysbox.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.rasysbox.shopping.config.ConfigurationConstants.CUSTOMER_URL;

@FeignClient(name = "customer", url = CUSTOMER_URL, fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
