package mk.finki.ukim.lab_emt.service;

import mk.finki.ukim.lab_emt.model.Host;
import mk.finki.ukim.lab_emt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long hostId);
    Optional<Host> save(HostDto host);
    Optional<Host> update(Long hostId, HostDto host);
}
