package com.rasysbox.customer.repository;

import com.rasysbox.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByName(String nameCustomer);
}
