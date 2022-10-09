package com.rasysbox.shopping.client;

import com.rasysbox.shopping.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{
    @Override
    public ResponseEntity<Customer> getCustomer(long id) {
        Customer customer = Customer.builder()
                .name("none")
                .state("none")
                .build();

        return ResponseEntity.ok(customer);
    }
}
