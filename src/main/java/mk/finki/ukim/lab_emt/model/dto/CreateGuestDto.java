package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.model.domain.Guest;

import java.util.List;
import java.util.stream.Collectors;

public record CreateGuestDto(
        String name,
        String surname,
        Long country
) {
    public static CreateGuestDto from(Guest guest) {
        return new CreateGuestDto(
                guest.getName(),
                guest.getSurname(),
                guest.getCountry().getId()
        );
    }

    public static List<CreateGuestDto> from(List<Guest> guests) {
        return guests.stream().map(CreateGuestDto::from).collect(Collectors.toList());
    }

    public Guest toGuest(Country country) {
        return new Guest(name, surname, country);
    }

}
