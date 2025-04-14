package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry() {
        return new Country(name, continent);
    }
}
