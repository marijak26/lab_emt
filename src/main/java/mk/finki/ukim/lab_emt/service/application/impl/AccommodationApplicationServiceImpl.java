package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.dto.CreateAccommodationDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.model.dto.StatisticsDto;
import mk.finki.ukim.lab_emt.service.application.AccommodationApplicationService;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import mk.finki.ukim.lab_emt.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public List<DisplayAccommodationDto> findFree() {
        return DisplayAccommodationDto.from(accommodationService.findFree());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long accommodationId) {
        return accommodationService.findById(accommodationId)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        if (host.isPresent()) {
            return accommodationService.save(createAccommodationDto.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long accommodationId, CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());
        if (host.isPresent()) {
            return accommodationService.update(accommodationId,
                            createAccommodationDto.toAccommodation(
                                    host.orElse(null))
                    )
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long accommodationId) {
        accommodationService.deleteById(accommodationId);

    }

    @Override
    public DisplayAccommodationDto markAccommodationAsRented(Long accommodationId) {
        return DisplayAccommodationDto.from(accommodationService.markAccommodationAsRented(accommodationId));
    }

    @Override
    public List<StatisticsDto> countAccommodationsByCategory() {
        return accommodationService.countAccommodationsByCategory().stream().map(StatisticsDto::from).toList();
    }
}
