package mk.finki.ukim.lab_emt.service.application;

import mk.finki.ukim.lab_emt.dto.CreateGuestDto;
import mk.finki.ukim.lab_emt.dto.DisplayGuestDto;

import java.util.List;
import java.util.Optional;

public interface GuestApplicationService {
    List<DisplayGuestDto> findAll();
    Optional<DisplayGuestDto> findById(Long guestId);
    Optional<DisplayGuestDto> save(CreateGuestDto createGuestDto);
    Optional<DisplayGuestDto> update(Long guestId, CreateGuestDto createGuestDto);
}
