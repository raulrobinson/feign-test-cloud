package com.rasysbox.customer.service;

import com.rasysbox.customer.entity.Customer;
import com.rasysbox.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByName(customer.getName());
        if (null != customerDB){
            return customerDB;
        }
        customer.setState("CREATED");

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (null == customerDB){
            return null;
        }
        customerDB.setName(customer.getName());
        customerDB.setState(customer.getState()); // ("UPDATED")

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (null == customerDB){
            return null;
        }
        customerDB.setState("DELETE");

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomer(Long id) {

        return customerRepository.findById(id).orElse(null);
    }
}
