package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        Long countryId,
        List<DisplayGuestDto> guests
) {

    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getHostId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getCountryId(),
                DisplayGuestDto.from(host.getGuests())
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
