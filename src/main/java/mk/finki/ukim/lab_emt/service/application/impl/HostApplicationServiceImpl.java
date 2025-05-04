package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.dto.DisplayHostDto;
import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.model.projections.HostProjection;
import mk.finki.ukim.lab_emt.model.views.HostsByCountryView;
import mk.finki.ukim.lab_emt.repository.HostsByCountryViewRepository;
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
    private final HostsByCountryViewRepository hostsByCountryViewRepository;
    private final CountryService countryService;
    private final GuestApplicationService guestApplicationService;

    public HostApplicationServiceImpl(HostService hostService, HostsByCountryViewRepository hostsByCountryViewRepository, CountryService countryService, GuestApplicationService guestApplicationService) {
        this.hostService = hostService;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
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
    public void deleteById(Long hostId) {
        hostService.deleteById(hostId);
    }

    @Override
    public List<HostProjection> getHostNamesAndSurnames() {
        return hostService.getNames();
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

    @Override
    public List<HostsByCountryView> findAllHostsByCountry() {
        return hostsByCountryViewRepository.findAll();
    }
}
