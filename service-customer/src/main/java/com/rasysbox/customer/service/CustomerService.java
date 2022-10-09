package com.rasysbox.customer.service;


import com.rasysbox.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findCustomerAll();
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer getCustomer(Long id);
}
