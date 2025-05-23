package mk.finki.ukim.lab_emt.repository;

import mk.finki.ukim.lab_emt.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
