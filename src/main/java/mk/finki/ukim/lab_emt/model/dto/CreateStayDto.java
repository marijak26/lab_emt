package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.enumerations.Category;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.domain.Stay;

import java.util.List;
import java.util.stream.Collectors;

public record CreateStayDto (
        String name,
        Category category,
        Long host,
        Integer numRooms
) {
    public static CreateStayDto from(Stay stay) {
        return new CreateStayDto(
                stay.getName(),
                stay.getCategory(),
                stay.getHost().getHostId(),
                stay.getNumRooms()
        );
    }

    public static List<CreateStayDto> from(List<Stay> stays) {
        return stays.stream().map(CreateStayDto::from).collect(Collectors.toList());
    }

    public Stay toStay(Host host) {
        return new Stay(name, category, host, numRooms);
    }
}
