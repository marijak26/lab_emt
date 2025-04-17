package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.model.dto.CreateAccommodationDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.model.dto.StatisticsDto;


import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    List<DisplayAccommodationDto> findFree();
    Optional<DisplayAccommodationDto> findById(Long accommodationId);
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto);
    Optional<DisplayAccommodationDto> update(Long accommodationId, CreateAccommodationDto createAccommodationDto);
    void deleteById(Long accommodationId);
    DisplayAccommodationDto markAccommodationAsRented(Long accommodationId);
    List<StatisticsDto> countAccommodationsByCategory();
}
