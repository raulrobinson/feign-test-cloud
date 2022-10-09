package com.rasysbox.country.service;


import com.rasysbox.country.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> findCountryAll();
    public Country createCountry(Country pais);
    public Country updateCountry(Country pais);
    public Country deleteCountry(Country pais);
    public Country getCountry(Long id);
}
