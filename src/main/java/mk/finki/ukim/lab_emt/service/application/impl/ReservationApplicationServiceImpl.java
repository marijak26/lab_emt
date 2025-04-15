package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.model.dto.ReservationDto;
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
    public List<DisplayStayDto> listAllStaysInReservation(Long reservationId) {
        return DisplayStayDto.from(reservationService.listAllStaysInReservation(reservationId));
    }

    @Override
    public Optional<ReservationDto> getActiveReservation(String username) {
        return reservationService.getActiveReservation(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> addStayToReservation(String username, Long stayId) {
        return reservationService.addStayToReservation(username, stayId).map(ReservationDto::from);
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
