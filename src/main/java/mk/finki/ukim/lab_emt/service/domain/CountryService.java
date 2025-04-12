package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long countryId);
    Optional<Country> findByName(String name);
    Optional<Country> save(Country country);
    Optional<Country> update(Long countryId, Country country);
}
