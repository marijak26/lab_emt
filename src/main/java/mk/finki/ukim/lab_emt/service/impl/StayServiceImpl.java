package mk.finki.ukim.lab_emt.service.impl;

import mk.finki.ukim.lab_emt.model.Stay;
import mk.finki.ukim.lab_emt.model.dto.StayDto;
import mk.finki.ukim.lab_emt.model.exceptions.StayIsAlreadyRentedException;
import mk.finki.ukim.lab_emt.repository.StayRepository;
import mk.finki.ukim.lab_emt.service.HostService;
import mk.finki.ukim.lab_emt.service.StayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StayServiceImpl implements StayService {
    private final StayRepository stayRepository;
    private final HostService hostService;
    public StayServiceImpl(StayRepository stayRepository, HostService hostService) {
        this.stayRepository = stayRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Stay> findAll() {
        return stayRepository.findAll();
    }

    @Override
    public List<Stay> findFree() {
        return stayRepository.findAll().stream().filter(stay -> !stay.isRented()).collect(Collectors.toList());
    }

    @Override
    public Optional<Stay> findById(Long stayId) {
        return stayRepository.findById(stayId);
    }

    @Override
    public Optional<Stay> save(StayDto stay) {
        if (stay.getHost() != null &&
                hostService.findById(stay.getHost()).isPresent() &&
                stay.getCategory() != null) {
            return Optional.of(
                    stayRepository.save(new Stay(stay.getName(), stay.getCategory(), hostService.findById(stay.getHost()).get(),
                            stay.getNumRooms())));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Stay> update(Long stayId, StayDto stay) {
        return stayRepository.findById(stayId)
                .map(existingStay -> {
                    if (stay.getName() != null) {
                        existingStay.setName(stay.getName());
                    }
                    if (stay.getCategory() != null) {
                        existingStay.setCategory(stay.getCategory());
                    }
                    if (stay.getHost() != null && hostService.findById(stay.getHost()).isPresent()) {
                        existingStay.setHost(hostService.findById(stay.getHost()).get());
                    }
                    if (stay.getNumRooms() != null) {
                        existingStay.setNumRooms(stay.getNumRooms());
                    }
                    return stayRepository.save(existingStay);
                });

    }

    @Override
    public void deleteById(Long stayId) {
        stayRepository.deleteById(stayId);
    }

    @Override
    public void markStayAsRented(Long stayId) {
        Stay stayToBeRented = stayRepository.findById(stayId).orElseThrow();
        if(!stayToBeRented.isRented()) {
            stayToBeRented.setRented(true);
            stayRepository.save(stayToBeRented);
        }
        else{
            throw new StayIsAlreadyRentedException(stayId);
        }
    }
}
