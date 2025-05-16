package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationByIdDto(
        Long id,
        String name,
        Category category,
        Host host,
        Integer numRooms
) {
    public static DisplayAccommodationByIdDto from(Accommodation accommodation) {
        return new DisplayAccommodationByIdDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost(),
                accommodation.getNumRooms()
        );
    }

    public static List<DisplayAccommodationByIdDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationByIdDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
