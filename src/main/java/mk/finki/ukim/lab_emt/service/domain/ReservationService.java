package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.domain.Stay;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById(Long id);

    List<Stay> listAllStaysInReservation(Long reservationId);

    Optional<Reservation> getActiveReservation(String username);

    Optional<Reservation> addStayToReservation(String username, Long stayId);

    Optional<Reservation> confirmReservation(String username);

    Optional<Reservation> cancelReservation(String username);

}
