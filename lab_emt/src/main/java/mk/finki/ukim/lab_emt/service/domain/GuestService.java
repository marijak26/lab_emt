package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Guest;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();
    Optional<Guest> findById(Long guestId);
    Optional<Guest> save(Guest guest);
    Optional<Guest> update(Long guestId, Guest guest);
}
