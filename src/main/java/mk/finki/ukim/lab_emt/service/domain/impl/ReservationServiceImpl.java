package mk.finki.ukim.lab_emt.service.domain.impl;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.domain.Stay;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;
import mk.finki.ukim.lab_emt.model.exceptions.ReservationNotFoundException;
import mk.finki.ukim.lab_emt.model.exceptions.StayAlreadyInReservationException;
import mk.finki.ukim.lab_emt.model.exceptions.StayIsAlreadyRentedException;
import mk.finki.ukim.lab_emt.model.exceptions.StayNotFoundException;
import mk.finki.ukim.lab_emt.repository.ReservationRepository;
import mk.finki.ukim.lab_emt.service.domain.ReservationService;
import mk.finki.ukim.lab_emt.service.domain.StayService;
import mk.finki.ukim.lab_emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final StayService stayService;


    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, StayService stayService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.stayService = stayService;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Stay> listAllStaysInReservation(Long reservationId) {
        if(reservationRepository.findById(reservationId).isEmpty())
            throw new ReservationNotFoundException(reservationId);
        return reservationRepository.findById(reservationId).get().getStays();
    }

    @Override
    public Optional<Reservation> getActiveReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseGet(() -> reservationRepository.save(new Reservation(user))));
    }

    @Override
    public Optional<Reservation> addStayToReservation(String username, Long stayId) {
        if(getActiveReservation(username).isPresent()){
            Reservation reservation = getActiveReservation(username).get();

            Stay stay = stayService.findById(stayId)
                    .orElseThrow(() -> new StayNotFoundException(stayId));
            if(!reservation.getStays().stream().filter(s -> s.getId().equals(stayId)).toList().isEmpty())
                throw new StayAlreadyInReservationException(stayId, username);
            if(stay.isRented())
                throw new StayIsAlreadyRentedException(stayId);
            reservation.getStays().add(stay);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        Reservation reservation = reservationRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseThrow(() -> new ReservationNotFoundException(
                        reservationRepository.findByUserAndStatus(user,ReservationStatus.CREATED).get().getId()));

        if(reservation.getStays().isEmpty()){
            throw new IllegalStateException("You cannot confirm an empty reservation");
        }
        reservation.getStays().forEach(stay -> {
            stay.setRented(true);
            stayService.save(stay);
        });
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> cancelReservation(String username) {
        User user = userService.findByUsername(username);

        Reservation reservation = reservationRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseThrow(() -> new ReservationNotFoundException(
                        reservationRepository.findByUserAndStatus(user,ReservationStatus.CREATED).get().getId()));

        if(reservation.getStays().isEmpty()){
            throw new IllegalStateException("You cannot cancel an empty reservation");
        }
        reservation.getStays().forEach(stay -> {
            stay.setRented(false);
            stayService.save(stay);
        });
        reservation.setStatus(ReservationStatus.CANCELED);
        return Optional.of(reservationRepository.save(reservation));
    }
}
