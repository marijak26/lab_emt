package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.model.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    Optional<ReservationDto> findById(Long id);

    List<DisplayStayDto> listAllStaysInReservation(Long reservationId);

    Optional<ReservationDto> getActiveReservation(String username);

    Optional<ReservationDto> addStayToReservation(String username, Long stayId);

    Optional<ReservationDto> confirmReservation(String username);

    Optional<ReservationDto> cancelReservation(String username);

}
