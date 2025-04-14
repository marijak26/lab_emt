package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.model.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.model.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long hostId);

    Optional<DisplayHostDto> save(CreateHostDto host);

    Optional<DisplayHostDto> update(Long hostId, CreateHostDto createHostDto);

    List<DisplayGuestDto> findGuestsByHostId(Long hostId);

    Optional<DisplayHostDto> saveGuest(Long hostId, CreateGuestDto createGuestDto);
}
