package mk.finki.ukim.lab_emt.service.impl;

import mk.finki.ukim.lab_emt.model.Country;
import mk.finki.ukim.lab_emt.model.dto.CountryDto;
import mk.finki.ukim.lab_emt.repository.CountryRepository;
import mk.finki.ukim.lab_emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long countryId) {
        return countryRepository.findById(countryId);
    }

    @Override
    public Optional<Country> save(CountryDto country) {
        return Optional.of(countryRepository.save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public Optional<Country> update(Long countryId, CountryDto country) {
        return countryRepository.findById(countryId).map(existingCountry -> {
            if (country.getName() != null) {
                existingCountry.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCountry);
        });

    }
}
