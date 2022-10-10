package com.rasysbox.country.controller;

import com.rasysbox.country.entity.Country;
import com.rasysbox.country.exception.BadRequest;
import com.rasysbox.country.service.CountryService;
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
@RequestMapping("${controller.properties.base-path}" + "/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    @Autowired
    BadRequest badRequest;

    /**
     * GET ALL COUNTRIES.
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Country>> listAllCountries(){
        List<Country> countries = new ArrayList<>();
        countries = countryService.findCountryAll();

        return ResponseEntity.ok(countries);
    }

    /**
     * GET COUNTRY BY ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(
            @PathVariable("id") long id
    ){
        log.info("Fetching Customer with ID {}", id);
        Country country = countryService.getCountry(id);
        if (null == country){
            log.error("Customer with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(country);
    }

    /**
     * CREATE COUNTRY.
     * @param country
     * @param result
     * @return
     */
    @PostMapping
    public ResponseEntity<Country> createCountry(
            @Valid
            @RequestBody Country country, BindingResult result
    ){
        log.info("Creating Customer: {}", country);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, badRequest.formatMessage(result));
        }
        Country customerDB = countryService.createCountry(country);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    /**
     * UPDATE COUNTRY BY ID.
     * @param id
     * @param country
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(
            @PathVariable("id") long id,
            @RequestBody Country country
    ){
        log.info("Updating Customer with ID: {}", id);
        Country customerDB = countryService.getCountry(id);
        if (null == customerDB){
            log.error("Unable to update. Customer with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        country.setId(id);
        customerDB = countryService.updateCountry(country);

        return ResponseEntity.ok(customerDB);
    }

    /**
     * DELETE COUNTRY BY ID.
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Country> deleteCountry(
            @PathVariable("id") long id
    ){
        log.info("Fetching and Deleting Customer with ID {}", id);
        Country country = countryService.getCountry(id);
        if (null == country){
            log.error("Unable to delete. Customer with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        country = countryService.deleteCountry(country);

        return ResponseEntity.ok(country);
    }

}
