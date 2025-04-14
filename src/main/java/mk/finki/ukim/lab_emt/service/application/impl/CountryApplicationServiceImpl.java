package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.model.dto.CreateCountryDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayCountryDto;
import mk.finki.ukim.lab_emt.service.application.CountryApplicationService;
import mk.finki.ukim.lab_emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long countryId) {
        return countryService.findById(countryId).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> findByName(String name) {
        return countryService.findByName(name).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long countryId, CreateCountryDto createCountryDto) {
        return countryService.update(countryId, createCountryDto.toCountry())
                .map(DisplayCountryDto::from);
    }
}
