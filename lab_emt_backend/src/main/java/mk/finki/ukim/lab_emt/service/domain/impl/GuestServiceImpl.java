package mk.finki.ukim.lab_emt.service.domain.impl;

import mk.finki.ukim.lab_emt.model.domain.Guest;
import mk.finki.ukim.lab_emt.repository.GuestRepository;
import mk.finki.ukim.lab_emt.service.domain.CountryService;
import mk.finki.ukim.lab_emt.service.domain.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final CountryService countryService;

    public GuestServiceImpl(GuestRepository guestRepository, CountryService countryService) {
        this.guestRepository = guestRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> findById(Long hostId) {
        return guestRepository.findById(hostId);
    }

    @Override
    public Optional<Guest> save(Guest guest) {
        if (guest.getCountry() != null &&
                countryService.findByName(guest.getCountry().getName()).isPresent()) {
            return Optional.of(
                    guestRepository.save(new Guest(guest.getName(), guest.getSurname(), countryService.findByName(guest.getCountry().getName()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Guest> update(Long guestId, Guest guest) {
        return guestRepository.findById(guestId).map(existingGuest -> {
            if (guest.getName() != null) {
                existingGuest.setName(guest.getName());
            }
            if (guest.getSurname() != null) {
                existingGuest.setSurname(guest.getSurname());
            }
            if (guest.getCountry() != null && countryService.findByName(guest.getCountry().getName()).isPresent()) {
                existingGuest.setCountry(countryService.findByName(guest.getCountry().getName()).get());
            }
            return guestRepository.save(existingGuest);
        });

    }
}
