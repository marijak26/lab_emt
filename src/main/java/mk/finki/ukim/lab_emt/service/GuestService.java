package mk.finki.ukim.lab_emt.service;

import mk.finki.ukim.lab_emt.model.Guest;
import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.model.dto.GuestDto;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();
    Optional<Guest> findById(Long guestId);
    Optional<Guest> save(GuestDto guest);
    Optional<Guest> update(Long guestId, GuestDto guest);
}
