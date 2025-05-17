package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById(Long id);

    List<Accommodation> listAllAccommodationsInReservation(Long reservationId);

    Optional<Reservation> getActiveReservation(String username);

    Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId);
    Optional<Reservation> removeAccommodationFromReservation(String username, Long accommodationId);

    Optional<Reservation> confirmReservation(String username);

    Optional<Reservation> cancelReservation(String username);

}
