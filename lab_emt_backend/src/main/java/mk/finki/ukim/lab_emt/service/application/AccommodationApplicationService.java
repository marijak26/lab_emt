package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.dto.CreateAccommodationDto;
import mk.finki.ukim.lab_emt.dto.DisplayAccommodationByIdDto;
import mk.finki.ukim.lab_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.dto.StatisticsDto;


import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    List<DisplayAccommodationDto> findFree();
    Optional<DisplayAccommodationDto> findById(Long accommodationId);
    Optional<DisplayAccommodationByIdDto> findByIdWithHost(Long accommodationId);
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto);
    Optional<DisplayAccommodationDto> update(Long accommodationId, CreateAccommodationDto createAccommodationDto);
    void deleteById(Long accommodationId);
    DisplayAccommodationDto markAccommodationAsRented(Long accommodationId);
    List<StatisticsDto> countAccommodationsByCategory();
}
