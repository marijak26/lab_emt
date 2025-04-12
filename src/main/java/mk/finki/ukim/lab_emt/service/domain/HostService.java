package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Guest;
import mk.finki.ukim.lab_emt.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long hostId);

    Optional<Host> save(Host host);

    Optional<Host> update(Long hostId, Host host);

    List<Guest> findGuestsByHostId(Long hostId);

    Optional<Host> saveGuest(Long hostId, Guest guest);
}
