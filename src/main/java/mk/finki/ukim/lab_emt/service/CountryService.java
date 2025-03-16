package mk.finki.ukim.lab_emt.service;

import mk.finki.ukim.lab_emt.model.Country;
import mk.finki.ukim.lab_emt.model.dto.CountryDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long countryId);
    Optional<Country> save(CountryDto country);
    Optional<Country> update(Long countryId, CountryDto country);
}
