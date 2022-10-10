package com.rasysbox.shopping.controller;

import com.rasysbox.shopping.exception.BadRequest;
import com.rasysbox.shopping.exception.ErrorMessage;
import com.rasysbox.shopping.service.InvoiceService;
import com.rasysbox.shopping.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("${controller.properties.base-path}" + "/invoices")
public class InvoiceRest {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    BadRequest badRequest;

    /**
     * RETRIEVE CUSTOMER BY ID
     * @param id
     * @return
     */
    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") Long id) {
        return invoiceService.getCustomer(id);
    }

    /**
     * RETRIEVE COUNTRY BY ID
     * @param id
     * @return
     */
    @GetMapping("/countries/{id}")
    public String getCountry(@PathVariable("id") Long id) {
        return invoiceService.getCountry(id);
    }

    /**
     * RETRIEVE SUBSCRIBER BY ID
     * @param id
     * @return
     */
    @GetMapping("/subscribers/{id}")
    public String getSubscriber(@PathVariable("id") Long id) {
        return invoiceService.getSubscriber(id);
    }

    /**
     * RETRIEVE ALL INVOICES
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> invoices = invoiceService.findInvoiceAll();
        if (invoices.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(invoices);
    }

    /**
     * RETRIEVE SINGLE INVOICE
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
        log.info("Fetching Invoice with id {}", id);
        Invoice invoice  = invoiceService.getInvoice(id);
        if (null == invoice) {
            log.error("Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(invoice);
    }

    /**
     * CREATE INVOICE BY ID
     * @param invoice
     * @param result
     * @return
     */
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
        log.info("Creating Invoice : {}", invoice);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequest.formatMessage(result));
        }
        Invoice invoiceDB = invoiceService.createInvoice (invoice);

        return  ResponseEntity.status( HttpStatus.CREATED).body(invoiceDB);
    }

    /**
     * UPDATE INVOICE BY ID
     * @param id
     * @param invoice
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        log.info("Updating Invoice with id {}", id);
        invoice.setId(id);
        Invoice currentInvoice=invoiceService.updateInvoice(invoice);
        if (currentInvoice == null) {
            log.error("Unable to update. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(currentInvoice);
    }

    /**
     * DELETE INVOICE BY ID
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Invoice with id {}", id);
        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            log.error("Unable to delete. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        invoice = invoiceService.deleteInvoice(invoice);

        return ResponseEntity.ok(invoice);
    }

}
