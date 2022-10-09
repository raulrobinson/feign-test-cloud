package com.rasysbox.country.repository;

import com.rasysbox.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public Country findByCountry(String NameCountry);
}
