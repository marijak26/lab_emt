package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    Optional<ReservationDto> findById(Long id);

    List<DisplayAccommodationDto> listAllAccommodationsInReservation(Long reservationId);

    Optional<ReservationDto> getActiveReservation(String username);

    Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId);

    Optional<ReservationDto> confirmReservation(String username);

    Optional<ReservationDto> cancelReservation(String username);

}
