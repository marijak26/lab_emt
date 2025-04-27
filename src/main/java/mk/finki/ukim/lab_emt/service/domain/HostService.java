package mk.finki.ukim.lab_emt.service.domain;

import mk.finki.ukim.lab_emt.model.domain.Guest;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long hostId);

    Optional<Host> save(Host host);

    Optional<Host> update(Long hostId, Host host);

    List<Guest> findGuestsByHostId(Long hostId);
    List<HostProjection> getNames();

    Optional<Host> saveGuest(Long hostId, Guest guest);
    void deleteById(Long hostId);
    void refreshMaterializedView();
}
