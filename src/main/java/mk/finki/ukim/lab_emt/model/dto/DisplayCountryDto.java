package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(Long countryId, String name, String continent) {

    public static DisplayCountryDto from(Country country) {
        return new DisplayCountryDto(country.getId(), country.getName(), country.getContinent());
    }

    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }
}
