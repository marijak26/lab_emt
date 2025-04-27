package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.model.domain.Guest;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayGuestDto(
        Long id,
        String name,
        String surname,
        Long country
) {

    public static DisplayGuestDto from(Guest guest) {
        return new DisplayGuestDto(
                guest.getGuestId(),
                guest.getName(),
                guest.getSurname(),
                guest.getCountry().getCountryId()
        );
    }

    public static List<DisplayGuestDto> from(List<Guest> guests) {
        return guests.stream().map(DisplayGuestDto::from).collect(Collectors.toList());
    }

    public Guest toGuest(Country country) {
        return new Guest(name, surname, country);
    }
}

