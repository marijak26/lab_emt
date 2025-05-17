package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationByIdDto(
        String name,
        Category category,
        Host host,
        Integer numRooms
) {
    public static CreateAccommodationByIdDto from(Accommodation accommodation) {
        return new CreateAccommodationByIdDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost(),
                accommodation.getNumRooms()
        );
    }

    public static List<CreateAccommodationByIdDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationByIdDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
