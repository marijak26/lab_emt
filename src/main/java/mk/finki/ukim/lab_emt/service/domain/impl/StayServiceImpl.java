package mk.finki.ukim.lab_emt.service.domain.impl;

import mk.finki.ukim.lab_emt.model.domain.Stay;
import mk.finki.ukim.lab_emt.model.exceptions.StayIsAlreadyRentedException;
import mk.finki.ukim.lab_emt.model.exceptions.StayNotFoundException;
import mk.finki.ukim.lab_emt.repository.StayRepository;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import mk.finki.ukim.lab_emt.service.domain.StayService;
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
    public Optional<Stay> save(Stay stay) {
        if (stay.getHost() != null &&
                hostService.findById(stay.getHost().getHostId()).isPresent() &&
                stay.getCategory() != null) {
            return Optional.of(
                    stayRepository.save(new Stay(stay.getName(), stay.getCategory(), hostService.findById(stay.getHost().getHostId()).get(),
                            stay.getNumRooms())));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Stay> update(Long stayId, Stay stay) {
        return stayRepository.findById(stayId)
                .map(existingStay -> {
                    if (stay.getName() != null) {
                        existingStay.setName(stay.getName());
                    }
                    if (stay.getCategory() != null) {
                        existingStay.setCategory(stay.getCategory());
                    }
                    if (stay.getHost() != null && hostService.findById(stay.getHost().getHostId()).isPresent()) {
                        existingStay.setHost(hostService.findById(stay.getHost().getHostId()).get());
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
    public Stay markStayAsRented(Long stayId) {
        Stay stayToBeRented = stayRepository.findById(stayId).orElseThrow(() -> new StayNotFoundException(stayId));
        if(stayToBeRented.isRented()) {
            throw new StayIsAlreadyRentedException(stayId);

        }
        else{
            stayToBeRented.setRented(true);
            return stayRepository.save(stayToBeRented);
        }
    }
}
