package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.lab_emt.dto.ReservationDto;
import mk.finki.ukim.lab_emt.service.application.ReservationApplicationService;
import mk.finki.ukim.lab_emt.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {
    private final ReservationService reservationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Optional<ReservationDto> findById(Long id) {
        return reservationService.findById(id).map(ReservationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInReservation(Long reservationId) {
        return DisplayAccommodationDto.from(reservationService.listAllAccommodationsInReservation(reservationId));
    }

    @Override
    public Optional<ReservationDto> getActiveReservation(String username) {
        return reservationService.getActiveReservation(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId) {
        return reservationService.addAccommodationToReservation(username, accommodationId).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> confirmReservation(String username) {
        return reservationService.confirmReservation(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> cancelReservation(String username) {
        return reservationService.cancelReservation(username).map(ReservationDto::from);
    }
}
