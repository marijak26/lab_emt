package mk.finki.ukim.lab_emt.service.impl;

import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.model.dto.HostDto;
import mk.finki.ukim.lab_emt.repository.HostRepository;
import mk.finki.ukim.lab_emt.service.CountryService;
import mk.finki.ukim.lab_emt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
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
    public Optional<Host> save(HostDto host) {
        if (host.getCountry() != null &&
                countryService.findByName(host.getCountry()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(), countryService.findByName(host.getCountry()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long hostId, HostDto host) {
        return hostRepository.findById(hostId).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null && countryService.findByName(host.getCountry()).isPresent()) {
                existingHost.setCountry(countryService.findByName(host.getCountry()).get());
            }
            return hostRepository.save(existingHost);
        });

    }
}
