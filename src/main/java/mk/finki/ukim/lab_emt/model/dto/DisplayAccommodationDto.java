package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.*;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto (
        Long id,
        String name,
        Category category,
        Long host,
        Integer numRooms
) {

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getHostId(),
                accommodation.getNumRooms()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
