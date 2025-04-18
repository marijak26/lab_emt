package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.model.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.model.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayHostDto;
import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.service.application.GuestApplicationService;
import mk.finki.ukim.lab_emt.service.application.HostApplicationService;
import mk.finki.ukim.lab_emt.service.domain.CountryService;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;
    private final GuestApplicationService guestApplicationService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService, GuestApplicationService guestApplicationService) {
        this.hostService = hostService;
        this.countryService = countryService;
        this.guestApplicationService = guestApplicationService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long hostId) {
        return hostService.findById(hostId)
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        if (country.isPresent()) {
            return hostService.save(createHostDto.toHost(country.get()))
                    .map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long hostId, CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        return hostService.update(hostId,
                        createHostDto.toHost(
                                country.orElse(null)
                        )
                )
                .map(DisplayHostDto::from);

    }

    @Override
    public List<DisplayGuestDto> findGuestsByHostId(Long hostId) {
        return hostService.findGuestsByHostId(hostId)
                .stream().map(DisplayGuestDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> saveGuest(Long hostId, CreateGuestDto createGuestDto) {
        Optional<Country> country = countryService.findById(createGuestDto.country());
        guestApplicationService.save(createGuestDto);
        return hostService.saveGuest(hostId,
                        createGuestDto.toGuest(country.orElse(null)
                        )
                )
                .map(DisplayHostDto::from);
    }
}
