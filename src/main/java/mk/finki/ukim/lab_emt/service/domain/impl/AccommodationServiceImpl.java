package mk.finki.ukim.lab_emt.service.domain.impl;

import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.dto.StatisticsDto;
import mk.finki.ukim.lab_emt.model.exceptions.AccommodationIsAlreadyRentedException;
import mk.finki.ukim.lab_emt.model.exceptions.AccommodationNotFoundException;
import mk.finki.ukim.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.lab_emt.repository.AccommodationsByHostViewRepository;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import mk.finki.ukim.lab_emt.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationsByHostViewRepository accommodationsByHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationsByHostViewRepository accommodationsByHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationsByHostViewRepository = accommodationsByHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public List<Accommodation> findFree() {
        return accommodationRepository.findAll().stream().filter(accommodation -> !accommodation.isRented()).collect(Collectors.toList());
    }

    @Override
    public Optional<Accommodation> findById(Long accommodationId) {
        return accommodationRepository.findById(accommodationId);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        Optional<Accommodation> savedAccommodation = Optional.empty();
        if (accommodation.getHost() != null &&
                hostService.findById(accommodation.getHost().getHostId()).isPresent() &&
                accommodation.getCategory() != null) {
            savedAccommodation = Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(),
                            accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getHostId()).get(),
                            accommodation.getNumRooms())));
        }
        return savedAccommodation;

    }

    @Override
    public Optional<Accommodation> update(Long accommodationId, Accommodation accommodation) {
        return accommodationRepository.findById(accommodationId)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getHostId()).isPresent()) {
                        existingAccommodation.setHost(hostService.findById(accommodation.getHost().getHostId()).get());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return accommodationRepository.save(existingAccommodation);
                });

    }

    @Override
    public void deleteById(Long accommodationId) {
        accommodationRepository.deleteById(accommodationId);
    }

    @Override
    public Accommodation markAccommodationAsRented(Long accommodationId) {
        Accommodation accommodationToBeRented = accommodationRepository.findById(accommodationId).orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        if(accommodationToBeRented.isRented()) {
            throw new AccommodationIsAlreadyRentedException(accommodationId);

        }
        else{
            accommodationToBeRented.setRented(true);
            return accommodationRepository.save(accommodationToBeRented);
        }
    }

    @Override
    public List<StatisticsDto> countAccommodationsByCategory() {
        return accommodationRepository.findAll().stream()
                .collect(Collectors.groupingBy(Accommodation::getCategory, Collectors.summingInt(accommodation -> 1)))
                .entrySet()
                .stream()
                .map(entry -> new StatisticsDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void refreshMaterializedView() {
         accommodationsByHostViewRepository.refreshMaterializedView();
    }
}
