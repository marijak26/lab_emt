package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.model.domain.Country;
import mk.finki.ukim.lab_emt.service.application.GuestApplicationService;
import mk.finki.ukim.lab_emt.service.domain.CountryService;
import mk.finki.ukim.lab_emt.service.domain.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestApplicationServiceImpl implements GuestApplicationService {
    private final GuestService guestService;
    private final CountryService countryService;

    public GuestApplicationServiceImpl(GuestService guestService, CountryService countryService) {
        this.guestService = guestService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayGuestDto> findAll() {
        return DisplayGuestDto.from(guestService.findAll());
    }

    @Override
    public Optional<DisplayGuestDto> findById(Long guestId) {
        return guestService.findById(guestId)
                .map(DisplayGuestDto::from);
    }

    @Override
    public Optional<DisplayGuestDto> save(CreateGuestDto createGuestDto) {
        Optional<Country> country = countryService.findById(createGuestDto.country());
        if (country.isPresent()) {
            return guestService.save(createGuestDto.toGuest(country.get()))
                    .map(DisplayGuestDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayGuestDto> update(Long guestId, CreateGuestDto createGuestDto) {
        Optional<Country> country = countryService.findById(createGuestDto.country());
        return guestService.update(guestId,
                        createGuestDto.toGuest(
                                country.orElse(null)
                        )
                )
                .map(DisplayGuestDto::from);

    }
}
