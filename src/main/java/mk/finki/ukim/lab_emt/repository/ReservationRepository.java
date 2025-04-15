package mk.finki.ukim.lab_emt.repository;

import mk.finki.ukim.lab_emt.model.domain.Reservation;
import mk.finki.ukim.lab_emt.model.domain.User;
import mk.finki.ukim.lab_emt.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserAndStatus(User user, ReservationStatus status);
}
