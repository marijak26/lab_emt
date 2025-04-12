package mk.finki.ukim.lab_emt.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.lab_emt.model.domain.Guest;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.exceptions.HostNotFoundException;
import mk.finki.ukim.lab_emt.repository.HostRepository;
import mk.finki.ukim.lab_emt.service.domain.CountryService;
import mk.finki.ukim.lab_emt.service.domain.GuestService;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final GuestService guestService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, GuestService guestService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.guestService = guestService;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long hostId) {
        return hostRepository.findById(hostId);
    }

    @Override
    public Optional<Host> save(Host host) {
        if (host.getCountry() != null &&
                countryService.findByName(host.getCountry().getName()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(), countryService.findByName(host.getCountry().getName()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long hostId, Host host) {
        return hostRepository.findById(hostId).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null && countryService.findByName(host.getCountry().getName()).isPresent()) {
                existingHost.setCountry(countryService.findByName(host.getCountry().getName()).get());
            }
            return hostRepository.save(existingHost);
        });

    }

    @Override
    public List<Guest> findGuestsByHostId(Long hostId) {
        return findById(hostId).orElseThrow(() -> new HostNotFoundException(hostId)).getGuests();
    }


    @Transactional
    @Override
    public Optional<Host> saveGuest(Long hostId, Guest guest) {
        return hostRepository.findById(hostId)
                .map(existingHost -> {
                    if(guest.getCountry() != null && countryService.findByName(guest.getCountry().getName()).isPresent()) {
                        Optional<Guest> newGuest = guestService.save(guest);

                        if (newGuest.isPresent()) {
                            Guest guestToSave = newGuest.get();
                            existingHost.getGuests().add(guestToSave);
                            guestToSave.getHosts().add(existingHost);
                        }
                    }
                    return hostRepository.save(existingHost);
                });
    }

}
