package com.rasysbox.country.service;

import com.rasysbox.country.entity.Country;
import com.rasysbox.country.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findCountryAll() {

        return countryRepository.findAll();
    }

    @Override
    public Country createCountry(Country country) {
        Country countryDB = countryRepository.findByCountry(country.getCountry());
        if (null != countryDB){
            return countryDB;
        }

        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        Country countryDB = getCountry(country.getId());
        if (null == countryDB){
            return null;
        }
        countryDB.setState(country.getState());

        return countryRepository.save(countryDB);
    }

    @Override
    public Country deleteCountry(Country country) {
        Country countryDB = getCountry(country.getId());
        if (null == countryDB){
            return null;
        }
        countryDB.setState("DELETED");

        return countryRepository.save(countryDB);
    }

    @Override
    public Country getCountry(Long id) {

        return countryRepository.findById(id).orElse(null);
    }
}
