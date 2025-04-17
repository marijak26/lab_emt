package mk.finki.ukim.lab_emt.service.domain.impl;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;
import mk.finki.ukim.lab_emt.model.exceptions.ReservationNotFoundException;
import mk.finki.ukim.lab_emt.model.exceptions.AccommodationAlreadyInReservationException;
import mk.finki.ukim.lab_emt.model.exceptions.AccommodationIsAlreadyRentedException;
import mk.finki.ukim.lab_emt.model.exceptions.AccommodationNotFoundException;
import mk.finki.ukim.lab_emt.repository.ReservationRepository;
import mk.finki.ukim.lab_emt.service.domain.ReservationService;
import mk.finki.ukim.lab_emt.service.domain.AccommodationService;
import mk.finki.ukim.lab_emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;


    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, AccommodationService accommodationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservation(Long reservationId) {
        if(reservationRepository.findById(reservationId).isEmpty())
            throw new ReservationNotFoundException(reservationId);
        return reservationRepository.findById(reservationId).get().getAccommodations();
    }

    @Override
    public Optional<Reservation> getActiveReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseGet(() -> reservationRepository.save(new Reservation(user))));
    }

    @Override
    public Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId) {
        if(getActiveReservation(username).isPresent()){
            Reservation reservation = getActiveReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if(!reservation.getAccommodations().stream().filter(a -> a.getId().equals(accommodationId)).toList().isEmpty())
                throw new AccommodationAlreadyInReservationException(accommodationId, username);
            if(accommodation.isRented())
                throw new AccommodationIsAlreadyRentedException(accommodationId);
            reservation.getAccommodations().add(accommodation);
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

        if(reservation.getAccommodations().isEmpty()){
            throw new IllegalStateException("You cannot confirm an empty reservation");
        }
        reservation.getAccommodations().forEach(accommodation -> {
            accommodation.setRented(true);
            accommodationService.save(accommodation);
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

        if(reservation.getAccommodations().isEmpty()){
            throw new IllegalStateException("You cannot cancel an empty reservation");
        }
        reservation.getAccommodations().forEach(accommodation -> {
            accommodation.setRented(false);
            accommodationService.save(accommodation);
        });
        reservation.setStatus(ReservationStatus.CANCELED);
        return Optional.of(reservationRepository.save(reservation));
    }
}
