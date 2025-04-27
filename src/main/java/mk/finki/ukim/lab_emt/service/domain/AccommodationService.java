package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.dto.StatisticsDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    List<Accommodation> findFree();
    Optional<Accommodation> findById(Long accommodationId);
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long accommodationId, Accommodation accommodation);
    void deleteById(Long accommodationId);
    Accommodation markAccommodationAsRented(Long accommodationId);
    List<StatisticsDto> countAccommodationsByCategory();
    void refreshMaterializedView();
}
