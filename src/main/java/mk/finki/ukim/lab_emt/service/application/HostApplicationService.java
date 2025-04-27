package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.dto.CreateHostDto;
import mk.finki.ukim.lab_emt.dto.DisplayGuestDto;
import mk.finki.ukim.lab_emt.dto.DisplayHostDto;
import mk.finki.ukim.lab_emt.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long hostId);

    Optional<DisplayHostDto> save(CreateHostDto host);

    Optional<DisplayHostDto> update(Long hostId, CreateHostDto createHostDto);
    void deleteById(Long hostId);
    List<HostProjection> getHostNamesAndSurnames();


    List<DisplayGuestDto> findGuestsByHostId(Long hostId);

    Optional<DisplayHostDto> saveGuest(Long hostId, CreateGuestDto createGuestDto);
}
