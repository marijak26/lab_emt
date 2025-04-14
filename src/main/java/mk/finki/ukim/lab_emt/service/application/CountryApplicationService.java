package mk.finki.ukim.lab_emt.service.application;


import mk.finki.ukim.lab_emt.model.dto.CreateCountryDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayCountryDto;
import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long countryId);
    Optional<DisplayCountryDto> findByName(String name);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long countryId, CreateCountryDto createCountryDto);
}
