package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.*;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayStayDto (
        Long id,
        String name,
        Category category,
        Long host,
        Integer numRooms
) {

    public static DisplayStayDto from(Stay stay) {
        return new DisplayStayDto(
                stay.getId(),
                stay.getName(),
                stay.getCategory(),
                stay.getHost().getHostId(),
                stay.getNumRooms()
        );
    }

    public static List<DisplayStayDto> from(List<Stay> stays) {
        return stays.stream().map(DisplayStayDto::from).collect(Collectors.toList());
    }

    public Stay toStay(Host host) {
        return new Stay(name, category, host, numRooms);
    }
}
