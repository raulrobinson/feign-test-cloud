package com.rasysbox.customer.controller;

import com.rasysbox.customer.entity.Customer;
import com.rasysbox.customer.exception.BadRequest;
import com.rasysbox.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${controller.properties.base-path}" + "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BadRequest badRequest;

    /**
     * GET ALL CUSTOMERS.
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> listAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customers = customerService.findCustomerAll();

        return ResponseEntity.ok(customers);
    }

    /**
     * GET CUSTOMER BY ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable("id") long id
    ){
        log.info("Fetching Customer with ID {}", id);
        Customer customer = customerService.getCustomer(id);
        if (null == customer){
            log.error("Customer with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    /**
     * CREATE CUSTOMER.
     * @param customer
     * @param result
     * @return
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid
            @RequestBody Customer customer, BindingResult result
    ){
        log.info("Creating Customer: {}", customer);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequest.formatMessage(result));
        }
        Customer customerDB = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    /**
     * UPDATE CUSTOMER BY ID.
     * @param id
     * @param customer
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") long id,
            @RequestBody Customer customer
    ){
        log.info("Updating Customer with ID: {}", id);
        Customer customerDB = customerService.getCustomer(id);
        if (null == customerDB){
            log.error("Unable to update. Customer with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        customerDB = customerService.updateCustomer(customer);

        return ResponseEntity.ok(customerDB);
    }

    /**
     * DELETE CUSTOMER BY ID.
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Customer> deleteCustomer(
            @PathVariable("id") long id
    ){
        log.info("Fetching and Deleting Customer with ID {}", id);
        Customer customerDB = customerService.getCustomer(id);
        if (null == customerDB){
            log.error("Unable to delete. Customer with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customerDB = customerService.deleteCustomer(customerDB);

        return ResponseEntity.ok(customerDB);
    }

}
